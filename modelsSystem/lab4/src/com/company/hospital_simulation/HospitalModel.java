package com.company.hospital_simulation;


import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HospitalModel {
    public List<Element> elementsList;
    public double tCurrent;
    public double tNext;
    public int event;

    public HospitalModel(List<Element> list) {
        elementsList = list;
        tCurrent = 0.0;
        tNext = 0.0;
    }

    public void Simulate(double timeModeling) {
        int prev = -1;
        while (tCurrent < timeModeling) {
            tNext = Double.MAX_VALUE;

            elementsList.stream().filter(e -> e.gettNext() < tNext).forEach(e -> {
                tNext = e.gettNext();
                event = elementsList.indexOf(e);
            });

            ManageChannels();
            System.out.println("It's time for event in " + elementsList.get(event).getName() +
                    " , time = " + elementsList.get(event).gettNext());
            for (Element e : elementsList) {
                if (e instanceof Doctor) {
                    Doctor d = (Doctor) e;
                    if (d.getStates().size() > 0) {
                        d.DoStatistics(tNext - tCurrent, d.getStates().get(0));
                    } else {
                        d.DoStatistics(tNext - tCurrent, 0);
                    }
                } else {
                    e.countStatistics(tNext - tCurrent);
                }
            }
            tCurrent = tNext;
            elementsList.forEach(e -> e.settCurrent(tCurrent));
            elementsList.get(event).outAct();
            elementsList.stream().filter(e -> e.gettNext() == tCurrent).forEach(Element::outAct);
        }
        printResult();

    }

    public void ManageChannels() {
        List<Channel> channels = new ArrayList<>();
        List<Channel> outChannels = new ArrayList<>();
        if ((elementsList.get(event) instanceof HospitalProcess) && !elementsList.get(event).getName().equals("EXIT")) {
            HospitalProcess p = (HospitalProcess) elementsList.get(event);
            p.InChannel();
        }
        for (Element e : elementsList) {
            if ((e instanceof HospitalProcess) && !e.getName().equals("EXIT")) {
                HospitalProcess p = (HospitalProcess) e;
                outChannels = p.outChannel(tNext);
                channels.addAll(outChannels);
            }
        }
        channels.stream().sorted(new ChannelComparator()).forEach(c ->
                System.out.println(c.getName() + " is free now t = " + c.getTimeOut()));
    }

    public void printInfo() {
        elementsList.forEach(Element::printInfo);
    }

    public void printResult() {
        System.out.println("---------------------RESULTS-----------------------");
        int patients = 0;
        double tWaiting = 0;
        double timeBetweenLab = 0;
        List<Double> types = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0));
        List<Integer> quantities = new ArrayList<>(Arrays.asList(0, 0, 0));
        for (Element e : elementsList) {
            patients += e.getQuantity();
            e.printResult();
            if (e instanceof HospitalCreate) {
                HospitalCreate c = (HospitalCreate) e;
                for (int i = 0; i < c.patientsTypes.size(); i++) {
                    quantities.add(i, c.patientsTypes.get(i).quantity);
                }
            }
            if (e instanceof HospitalProcess) {
                HospitalProcess process = new HospitalProcess();
                process = (HospitalProcess) e;
                patients += process.getQuantity();
                //if (process.Name == "LABORATORY" || process.Name == "DOCTOR" || process.Name == "CHAMBER")
                //{
                tWaiting += process.waitingTime;
                //}
                process.types.forEach(t -> {
                    types.set(t.index - 1, types.get(t.index - 1) + t.waitingTime);
                    quantities.set(t.index - 1, quantities.get(t.index - 1) + t.quantity);
                });

                double average = process.getAverageDelay() / process.gettCurrent();
                double workload = process.averageWorkload / process.gettCurrent();
                System.out.println("name = " + process.getName() + " max parallel = " + process.maxParallel + " quantity = " +
                        process.getQuantity() + "averageQ =  " + average +
                        " workload = " + workload);
            }
            if (e instanceof Doctor) {
                Doctor doctor = (Doctor) e;
                //patients += doctor.Quantity;
                tWaiting += doctor.waitingTime;
                doctor.getdTypes().forEach(t -> {
                    types.set(t.index - 1, types.get(t.index - 1) + t.waitingTime);
                    quantities.set(t.index - 1, quantities.get(t.index - 1) + t.quantity);
                });

                double average = doctor.averageQueue / doctor.gettCurrent();
                double workload = doctor.averageWorkload / doctor.gettCurrent();
                System.out.println("name = " + doctor.getName() + " max parallel = " + doctor.maxParallel + "  quantity = " + doctor.getQuantity() + " averageQ = " + average +
                        " workload = " + workload);
                timeBetweenLab = doctor.delaySum / doctor.toLabAmount;

            }
        }
        double AverageTime = tWaiting / patients;
        for (int i = 0; i < types.size(); i++) {
            types.set(i, types.get(i) / quantities.get(i));
            System.out.println("Average time in the hospital of type " + (i + 1) + " is " + types.get(i));
        }
        System.out.println("Avg trip from doctor to lab duration is " + timeBetweenLab);
    }
}


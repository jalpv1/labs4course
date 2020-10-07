package com.company.hospitaltask;

import com.company.algorithm.Channel;
import com.company.algorithm.Element;
import com.company.hospital.CreateH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ModelHospital {
    public List<Element> ElementsList ;
    public double TCurrent ;
    public double TNext ;
    public int Event ;
    public ModelHospital(List<Element> list)
    {
        ElementsList = list;
        TCurrent = 0.0;
        TNext = 0.0;
    }

    public void Simulate(double timeModeling)
    {
        int prev = -1;
        while (TCurrent < timeModeling)
        {
            TNext = Double.MAX_VALUE;
            for (Element e : ElementsList)
            {
                if (e.getTnext() < TNext)
                {
                    double t = e.getTnext();
                    TNext = e.getTnext();
                    Event = ElementsList.indexOf(e);
                   // e.setTnext(Double.MAX_VALUE);

                }
            }
            System.out.println("\n event in " +
                    ElementsList.get(Event).getName() +
                    ", time =   " + TNext);
            ManageChannels();
//            if (!(prev == 0 && Event == 0)){
//
//            }
                //Console.WriteLine($"It's time for event in {ElementsList[Event].Name} , time = {ElementsList[Event].TNext}");
//            System.out.println("IN FUTURE from " +  ElementsList.get(Event).getName()+ "to" +  ElementsList.get(Event).getNextElement().getName() + "t = " + ElementsList.get(Event).getNextElement().getTnext());

            for (Element e : ElementsList)
            {
                if (e instanceof Doctor)
                {
                    Doctor d = (Doctor)e;
                    if (d.getStates().size() > 0)
                    {
                        d.DoStatistics(TNext - TCurrent, d.getStates().get(0));
                    }
                    else
                    {
                        d.DoStatistics(TNext - TCurrent, 0);
                    }
                }
                else
                {//kkkkkkk
                    e.doStatistics(TNext - TCurrent);
                }
            }
            TCurrent = TNext;
            for (Element e : ElementsList)
            {
                e.setTcurr(TCurrent);
            }
            //Get(Event).OutAct();
            ElementsList.get(Event).outAct();
            for (Element e : ElementsList)
            {
                if (e.getTnext() == TCurrent)
                {
                    e.outAct();
                }
            }
            //PrintInfo();
        }
        PrintResult();

    }

    public void ManageChannels()
    {
        List<Channel> channels = new ArrayList<>();
        List<Channel> outChannels = new ArrayList<>();
        if ((ElementsList.get(Event) instanceof ProcessHospital) && !ElementsList.get(Event).getName().equals("EXIT"))
        {
            ProcessHospital p = (ProcessHospital)ElementsList.get(Event);
            p.InChannel();
        }
        for (Element e : ElementsList)
        {
            if ((e instanceof ProcessHospital) && !ElementsList.get(Event).getName().equals("EXIT"))
            {
                ProcessHospital p = (ProcessHospital)e;
                outChannels = p.OutChannel(TNext);
                channels.addAll(outChannels);

            }
        }
        channels = channels.stream().sorted(Comparator.comparingDouble(Channel::getTimeOut)).collect(Collectors.toList());
        for (Channel c : outChannels)
        {
//            Console.WriteLine();
//            Console.WriteLine($"{c.Name} is free now t = {c.TimeOut}");
//            Console.WriteLine();
        }
    }
    public void PrintInfo()
    {
        for (Element e : ElementsList)
        {
            e.printInfo();
        }
    }

    public void PrintResult()
    {
      //  Console.WriteLine("---------------------RESULTS-----------------------");
        int patients = 0;
        double tWaiting = 0;
        double timeBetweenLab = 0;
        List<Double> types = new ArrayList<Double>(Arrays.asList( 0.0, 0.0, 0.0)) ;
       // List<int> quantities = new List<int> { 0, 0, 0 };
        List<Integer> quantities = new ArrayList<>(Arrays.asList( 0, 0, 0));

        for (Element e : ElementsList)
        {
            patients += e.getQuantity();
            //if (e.GetType() == typeof(HospitalCreate))
            //{
            //    patients += e.Quantity;
            //}
            e.printResult();
            if (e instanceof CreateHospital)
            {
                CreateHospital c = (CreateHospital)e;
                for (int i = 0; i < c.patientsTypes.size(); i++)
                {

                    quantities.set(i, c.patientsTypes.get(i).getQuantity());

                }

            }
            if (e instanceof ProcessHospital)
            {
                ProcessHospital process = new ProcessHospital();
                process = (ProcessHospital)e;
                patients += process.getQuantity();
                //if (process.Name == "LABORATORY" || process.Name == "DOCTOR" || process.Name == "CHAMBER")
                //{
                tWaiting += process.waitingTime;
                //}
                for (var t : process.getTypes())
                {
                    types.set(t.getIndex() - 1,t.getWaitingTime()+types.get(t.getIndex() - 1)) ;
                    //quantities[t.Index - 1] += process.Quantity;
                    quantities.set(t.getIndex() - 1,quantities.get(t.getIndex() - 1)+t.getQuantity());
                }
                double average = process.getAverageQueue() / process.getTcurr();
                double workload = process.averageWorkload / process.getTcurr();
//                Console.WriteLine($"name = {process.Name} max parallel = {process.MaxParallel} quantity = {process.Quantity} averageQ = {average} " +
//                        $" workload = {workload}");
            }
            if (e instanceof Doctor)
            {
                Doctor doctor = (Doctor)e;
                //patients += doctor.Quantity;
                tWaiting += doctor.waitingTime;
                for (var t : doctor.types)
                {
                    types.set(t.getIndex() - 1, types.get(t.getIndex() - 1) + t.getWaitingTime());
                    //quantities[t.Index - 1] += doctor.Quantity;
                    quantities.set(t.getIndex() - 1, quantities.get(t.getIndex() - 1) + t.getQuantity());

                }
                double average = doctor.getAverageQueue() / doctor.getTcurr();
                double workload = doctor.averageWorkload / doctor.getTcurr();
//                Console.WriteLine($"name = {doctor.Name} max parallel = {doctor.MaxParallel} quantity = {doctor.Quantity} averageQ = {average} " +
//                        $" workload = {workload}");
                timeBetweenLab = doctor.delaySum / doctor.labAmount;

            }
        }
        double AverageTime = tWaiting / patients;
        for (int i = 0; i < types.size(); i++)
        {
            types.set(i, types.get(i)/quantities.get(i));

            //Console.WriteLine($"Average time in the hospital of type {i + 1} is {types[i]}");
        }
        //Console.WriteLine($"Average time in the hospital is {AverageTime}");
       // Console.WriteLine($"Avg trip from doctor to lab duration is {timeBetweenLab}");
    }
}

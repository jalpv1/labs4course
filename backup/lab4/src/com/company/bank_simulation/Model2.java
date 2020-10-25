package com.company.bank_simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model2 {
    public List<Element> list ;
    public double tNext;
    public double tCurrent;
    int event;

    public List<Element> sortedList = new ArrayList<>();

    public Model2(List<Element> elements) {
        list = elements;
        tNext = 0.00;
        event = 0;
        tCurrent = tNext;
        //list = new ArrayList<Element>();
    }

    public void Simulate(double timeModeling) {
        int prev = -1;

        while (tCurrent < timeModeling) {
            tNext = Double.MAX_VALUE;

            for (Element e : list) {
                if (e.gettNext() < tNext) {
                    tNext = e.gettNext();
                    event = list.indexOf(e);
                }
            }
            ManageChannels();
            if (!(prev == 0 && event == 0))
                System.out.println("Event in:" + list.get(event).getName() + "time= "
                        + tNext);


            for (Element e : list) {
                e.countStatistics(tNext - tCurrent);
            }
            tCurrent = tNext;

            for (Element e : list) {
                e.settCurrent(tCurrent);
            }
            list.get(event).outAct();

            for (Element e : list) {
                if (e.gettNext() == tCurrent) {
                    e.outAct();
                }
            }
            //PrintInfo();
            prev = event;
        }
        PrintResult();
    }

    public void ManageChannels() {
        List<Channel> channels = new ArrayList<>();
        List<Channel> outChannels = new ArrayList<>();
        if (list.get(event) instanceof Process && !list.get(event).getName().equals("DISPOSE")) {
            Process p = (Process) list.get(event);
            p.inChannel();
        }
        for (Element e : list) {
            if (e instanceof Process && e.getName().equals("DISPOSE")) {
                Process p = (Process) e;
                outChannels = p.outChannel(tNext);
                channels.addAll(outChannels);

            }
        }
        // channels = channels.OrderBy(x => x.TimeOut).ToList();
        channels = channels.stream().sorted(new ChannelComparator2()).collect(Collectors.toList());
        for (Channel c : channels) {
            System.out.println(c.getName() + " is free now t = " + c.getTimeOut());
        }
    }

    public void PrintInfo() {
        for (Element e : list) {
            e.printInfo();
        }
    }

    public void PrintResult() {
        System.out.println("\n-------------RESULTS-------------");
        for (Element e : list) {
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            e.printResult();
            if (e instanceof Process && !e.getName().equals("DISPOSE")) {

                Process p = (Process) e;
                p.setAverageQueueTime(p.getAverageQueue() / p.getQuantity());
                p.setAverageQueue(p.getAverageQueue() / tCurrent);
                p.setAverageProcessingTime(p.getAverageProcessingTime() / p.getQuantity());
                p.setProbabilityFailure(p.getFailure() / (double) (p.getQuantity() + p.getFailure()));
                p.setAverageWorkload(p.getAverageWorkload() / tCurrent);
                //Console.WriteLine($"Delay = {p.AverageDelay} QLength = {p.MaxQueueObserved} MaxParallel = {p.MaxParallel} AvgQLength = {p.AverageQueue} " +
                //$"MaxQLength = {p.MaxQueueObserved} AvgWorkload = {p.AverageWorkload} MaxWorkload = {p.MaxWorkload} AvgProcTime = {p.AverageProcessingTime}" +
                //$" Failure = {p.Failure} PFailure = {p.ProbabilityFailure}    AvgQTime = {p.AverageQueueTime}");

                System.out.println("mean length of queue = " + p.getAverageQueue());
                System.out.println("max observed queue length = " + p.getMaxQueueObserved());
                System.out.println("failure probability = " + p.getProbabilityFailure());
                System.out.println("average time in queue =" + p.getAverageQueueTime());
                System.out.println("average processing time = " + p.getAverageProcessingTime());
                System.out.println("max workload = " + p.getMaxWorkload());
                System.out.println("average workload = " + p.getAverageWorkload());
            }
        }
    }

    public void ReturnResult() {
        double average, workload, waiting, outWindow, avgBank = 0, actAndQueueQuantity = 0;
        int totalQuantity = 0, failure = 0;
        for (Element e : list) {
            e.printResult();
            if (e instanceof Process) {
                Process p = (Process) e;
                average = p.getAverageQueue();
                workload = p.getAverageWorkload();
                waiting = p.getAverageQueue() / (p.getQuantity() + p.getFailure());
                outWindow = tCurrent / p.getQuantity();
                totalQuantity += p.getQuantity();
                actAndQueueQuantity += p.getAverageAmount();
                avgBank += average;
                failure += p.getFailure();
                if (!e.getName().equals("element GO OUT")) {
                    System.out.println("avgLength = " + average + " transfers = " + p.TransferedCount + " failure = " + failure
                            +
                            " avgTimeInBank = " + waiting + " workload = " + workload + "inProgress = " + p.getState() +
                            " inQueue = " + p.getQueueLength() + "avgInWindow = " + outWindow);
                    System.out.println("In action and in queue on process " + p.getAverageAmount() / tCurrent);
                }
            }
        }
        System.out.println("In action and in queue on system " + actAndQueueQuantity / tCurrent);
        System.out.println("avgInBank = " + avgBank);
    }


}

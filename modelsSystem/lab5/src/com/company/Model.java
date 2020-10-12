package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    public List<Element> list;
    public double tNext;
    public double tCurrent;
    int event;

    public List<Element> sortedList = new ArrayList<>();

    public Model(List<Element> elements) {
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
                if (e.getTnext() < tNext) {
                    tNext = e.getTnext();
                    event = list.indexOf(e);
                }
            }
            ManageChannels();
            if (!(prev == 0 && event == 0))
//                System.out.println("Event in:" + list.get(event).getName() + "time= "
//                        + tNext);
//

            for (Element e : list) {
                // e.co(tNext - tCurrent);
            }
            tCurrent = tNext;

            for (Element e : list) {
                e.setTcurr(tCurrent);
            }
            list.get(event).outAct();

            for (Element e : list) {
                if (e.getTnext() == tCurrent) {
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
        channels = channels.stream().sorted(new ChannelComparator()).collect(Collectors.toList());
        for (Channel c : channels) {
            //System.out.println(c.getName() + " is free now t = " + c.getTimeOut());
        }
    }

    public void PrintInfo() {
        for (Element e : list) {
            e.printInfo();
        }
    }

    public void PrintResult() {
      //  System.out.println("\n-------------RESULTS-------------");
        for (Element e : list) {
          // System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            e.printResult();
            if (e instanceof Process && !e.getName().equals("DISPOSE")) {

                Process p = (Process) e;
                p.setAverageQueueTime(p.getAverageQueue() / p.getQuantity());
                p.setAverageQueue(p.getAverageQueue() / tCurrent);
                p.setAverageProcessingTime(p.getAverageProcessingTime() / p.getQuantity());
                p.setProbabilityFailure(p.getFailure() / (double) (p.getQuantity() + p.getFailure()));
                p.setAverageWorkload(p.getAverageWorkload() / tCurrent);

//                System.out.println("mean length of queue = " +(double) p.getAverageQueue());
//                System.out.println("max observed queue length = " +(double) p.getMaxQueueObserved());
//                System.out.println("failure probability = " +(double) p.getProbabilityFailure());
//                System.out.println("average time in queue =" +(double) p.getAverageQueueTime());
//                System.out.println("average processing time = " +(double)p.getAverageProcessingTime());
//                System.out.println("max workload = " + (double)p.getMaxWorkload());
//                System.out.println("average workload = " + (double)p.getAverageWorkload());
            }
        }
    }

    public  List<Result>  ReturnResult() {
        double average, workload = 0, actAndQueueQuantity = 0;
        int totalQuantity = 0, failure = 0;
        List<Result> result = new ArrayList<>();

        for (Element e : list) {
            e.printResult();
            if (e instanceof Process) {
                Process p = (Process) e;
                average = p.getAverageQueue();
                workload = p.getAverageWorkload();
                result.add(new Result(p.getName(),average,workload));

            }
        }
        return result;
    }

}

package com.company;

import java.util.ArrayList;

public class Model {

    private ArrayList<Element> list = new ArrayList<>();
    double tnext, tcurr;
    int event;

    public Model(ArrayList<Element> elements) {
        list = elements;
        tnext = 0.0;
        event = 0;
        tcurr = tnext;
    }


    public void simulate(double time) {
     Statistic statistic = new Statistic();
     String name = " ";
        while (tcurr < time) {
            tnext = Double.MAX_VALUE;
            for (Element e : list) {
                if (e.getTnext() < tnext) {
                    tnext = e.getTnext();
                    event = e.getId();
                    name = e.getName();

                }
            }
            System.out.println("\n event in " +
                    name +
                    ", time =   " + tnext);

           for (Element e : list) {
                e.doStatistics(tnext - tcurr);
            }
            tcurr = tnext;
            for (Element e : list) {
                e.setTcurr(tcurr);
            }
           // if(event < list.size()) {
                list.get(event).outAct(1);
           // }
            for (Element e : list) {
                if (e.getTnext() == tcurr) {
                    e.outAct(1);
                }
            }
          //  printInfo();
        }
        statistic.doStatistics(list);
    }

    public void printInfo() {
        for (Element e : list) {
          //  e.printInfo();
        }
    }

//    public void printResult() {
//
//        System.out.println("\n-------------RESULTS-------------");
//
//        for (Element e : list) {
//            e.printResult();
//            if (e instanceof Process) {
//                Process p = (Process) e;
//                p.doResultsStatistics();
//                System.out.println("mean length of queue = " +
//                        p.getMeanQueue() / tcurr
//                        + "\nfailure probability  = " +
//                        p.getProbabilityFailure() + " \nMax Queue " + p.getMaxQueueInSimulation()
//                        + " Mean Queue " + p.getMeanQueue());
//            }
//        }
//    }


}

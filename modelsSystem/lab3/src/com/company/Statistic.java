package com.company;

import java.util.ArrayList;

public class Statistic {
    public void doStatistics(ArrayList<Element> list) {
        System.out.println("id | " + " delay |" + " max parallel |" + " max queue |" + "Mean Queue |" + " Max queue was|" +
                " Mean PayLoad |" + " Max Payload |" + " Failed prop |");

        double meanQueue = 0;
        double propFailure = 0;
        double meanPayLoad = 0;
        for (Element e : list) {
            e.printResult();
            if (e instanceof Process) {
                meanQueue = ((Process) e).getMeanQueue() / e.getTcurr();
                propFailure = (double) ((Process) e).getFailure() / e.getQuantity();
                meanPayLoad = ((Process) e).getMeanLoad() / e.getTcurr();
                System.out.println(e.getId() + " | " + e.getDelay() + " | " + ((Process) e).getMaxParallel() + "   |   " + ((Process) e).getMaxqueue() + "  |   " + meanQueue + "  |  " +
                        ((Process) e).getMaxQueueInSimulation() + " | " + meanPayLoad + "  |  " + ((Process) e).getMaxLoad() + " | " + propFailure);

            }

        }
        System.out.println("---------------------");

    }
}

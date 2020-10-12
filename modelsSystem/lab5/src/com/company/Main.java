package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Create c = new Create(2.0, "CREATOR", "exp", 0);
//        Process p1 = new Process(0.6, "PROCESSOR1", "exp", 2, 1, 0);
//        Process p2 = new Process(0.3, "PROCESSOR2", "Exponential", 2, 1, 0);
//        Process p3 = new Process(0.4, "PROCESSOR3", "Exponential", 2, 1, 0);
//        Process p4 = new Process(0.1, "PROCESSOR4", "Exponential", 2, 2, 0);
//        Process d = new Process(0, "DISPOSE", "Exponential", 0, 1, 0);
//
//        c.setNextElement(p1);
//        p1.setNextProcesses(new ArrayList<>(Arrays.asList(p2, p3, p4)));
//        p1.setPobabilities(new ArrayList<>(Arrays.asList(new Probability(0.15), new Probability(0.13), new Probability(0.3))));
//        p2.setPobabilities(new ArrayList<>(Arrays.asList(new Probability(0.15), new Probability(0.13), new Probability(0.3))));
//        p3.setPobabilities(new ArrayList<>(Arrays.asList(new Probability(0.15), new Probability(0.13), new Probability(0.3))));
//        p4.setPobabilities(new ArrayList<>(Arrays.asList(new Probability(0.15), new Probability(0.13), new Probability(0.3))));
//
//        p1.setPriority(new ArrayList<>(Arrays.asList(2, 3, 1)));
//        p2.setPriority(new ArrayList<>(Arrays.asList(3, 2, 1)));
//        p3.setPriority(new ArrayList<>(Arrays.asList(1, 3, 2)));
//        p4.setPriority(new ArrayList<>(Arrays.asList(1, 2, 3)));
//        p2.setNextProcesses(Arrays.asList(p1));
//        p3.setNextProcesses(Arrays.asList(p1));
//        p4.setNextProcesses(Arrays.asList(p1));
//        List<Element> elementsList = new ArrayList<Element>(Arrays.asList(c, p1, p2, p3, p4));
//        Model model = new Model(elementsList);
//        model.Simulate(1000.0);
//        List<Result> res = model.ReturnResult();
//
//        double[] theoreticalAverageQueue = new double[]{1.786, 0.003, 0.004, 0.00001};
//        double[] theoreticalWorkload = new double[]{0.714, 0.054, 0.062, 0.036};
//        double[] queueAccuracy = new double[res.size()];
//        double[] workloadAccuracy = new double[res.size()];
//
//        for (int i = 0; i < theoreticalAverageQueue.length; i++) {
//            queueAccuracy[i] = Math.abs(res.get(i).average - theoreticalAverageQueue[i]) / theoreticalAverageQueue[i];
//            workloadAccuracy[i] = Math.abs(res.get(i).workload - theoreticalWorkload[i]) / theoreticalWorkload[i];
//        }
//        System.out.println("Theoretical Queue Accuracy");
//        Arrays.stream(theoreticalAverageQueue).forEach(System.out::println);
//        System.out.println(" Theoretical workload Accuracy");
//
//        Arrays.stream(theoreticalWorkload).forEach(System.out::println);
//        System.out.println("Queue Accuracy");
//        Arrays.stream(queueAccuracy).forEach(System.out::println);
//        System.out.println("workload Accuracy");
//
//        Arrays.stream(workloadAccuracy).forEach(System.out::println);

//        long m = System.currentTimeMillis();
//        NSystemCreate nSystemCreate = new NSystemCreate();
//        nSystemCreate.createNSystem(10);
//        System.out.println((double) (System.currentTimeMillis() - m));

        for (int i = 10; i < 100; i = i + 10){
            long m = System.currentTimeMillis();
            NSystemCreate nSystemCreate = new NSystemCreate();
            nSystemCreate.createNSystem(i);
            System.out.println("Number " + i +" time =" + (double) (System.currentTimeMillis() - m));
        }

        Create c = new Create(2.0, "CREATOR", "exp", 0);
        Process p1 = new Process(0.6, "PROCESSOR1", "exp", 2, 1, 0);
        Process p2 = new Process(0.3, "PROCESSOR2", "Exponential", 2, 1, 0);
        Process p3 = new Process(0.4, "PROCESSOR3", "Exponential", 2, 1, 0);
        Process p4 = new Process(0.1, "PROCESSOR4", "Exponential", 2, 2, 0);
        Process d = new Process(0, "DISPOSE", "Exponential", 0, 1, 0);

        c.setNextElement(p1);
        p1.setNextProcesses(new ArrayList<>(Arrays.asList(p2, p3, p4)));
        p1.setPobabilities(new ArrayList<>(Arrays.asList(new Probability(0.15), new Probability(0.13), new Probability(0.3))));
        p2.setPobabilities(new ArrayList<>(Arrays.asList(new Probability(0.15), new Probability(0.13), new Probability(0.3))));
        p3.setPobabilities(new ArrayList<>(Arrays.asList(new Probability(0.15), new Probability(0.13), new Probability(0.3))));
        p4.setPobabilities(new ArrayList<>(Arrays.asList(new Probability(0.15), new Probability(0.13), new Probability(0.3))));

        p1.setPriority(new ArrayList<>(Arrays.asList(2, 3, 1)));
        p2.setPriority(new ArrayList<>(Arrays.asList(3, 2, 1)));
        p3.setPriority(new ArrayList<>(Arrays.asList(1, 3, 2)));
        ;
        p4.setPriority(new ArrayList<>(Arrays.asList(1, 2, 3)));

        p2.setNextProcesses(Arrays.asList(p1,p2));
        p3.setNextProcesses(Arrays.asList(p1,p4));
        p4.setNextProcesses(Arrays.asList(p1));
        List<Element> elementsList = new ArrayList<Element>(Arrays.asList(c, p1, p2, p3, p4));
        Model model = new Model(elementsList);
        model.Simulate(1000.0);
    }
}

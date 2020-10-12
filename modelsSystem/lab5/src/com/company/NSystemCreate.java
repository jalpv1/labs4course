package com.company;

import java.util.ArrayList;
import java.util.Random;

public class NSystemCreate {
    Random random = new Random();

    public void createNSystem(int systemCount) {
        ArrayList<Process> processes = new ArrayList<>();
        Create create = new Create(random.nextDouble(), "CREATOR", "exp", 0);
        for (int i = 0; i < systemCount; i++) {
            Process process = new Process(random.nextDouble(), "PROCESSOR " + i, "exp", random.nextInt(4), random.nextInt(3), 0);
            processes.add(process);
           // process = createNexts(process,processes);
        }
        for (Process process: processes) {
             process = createNexts(process,processes);
             process = createProbabilities(process);
             process = createPriority(process);

        }
        create.setNextElement(processes.get(0));
        ArrayList<Element> elementArrayList = new ArrayList<>();
        elementArrayList.add(create);
        elementArrayList.addAll(processes);
        Model model = new Model(elementArrayList);
        model.Simulate(1000.0);
    }

    public Process createNexts(Process process, ArrayList<Process> processes) {
        int countNexts = random.nextInt(processes.size() - 1);
        ArrayList<Process> nexts = new ArrayList<>();

        for (int i = 0; i < countNexts; i++) {
            nexts.add(processes.get(random.nextInt(processes.size() - 1)));
        }
        process.setNextProcesses(nexts);
        return process;
    }

    private Process createProbabilities(Process process) {
        int size = process.getNextProcesses().size();
        ArrayList<Probability> probabilities = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Probability probability = new Probability(random.nextDouble());
            probabilities.add(probability);
        }
        process.setPobabilities(probabilities);
        return process;
    }
    private Process createPriority(Process process) {
        int size = process.getNextProcesses().size();
        ArrayList<Integer> priority = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            //Probability probability = new Probability(random.nextDouble());
            priority.add(random.nextInt(10));
        }
        process.setPriority(priority);
        return process;
    }
}

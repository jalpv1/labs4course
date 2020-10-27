package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    public List<Position> positions = new ArrayList<>();
    public List<Transition> transitions = new ArrayList<Transition>();
    public List<Transition> nextTransitions = new ArrayList<Transition>();
    Random random = new Random();

    public Model(List<Position> positions, List<Transition> transitions) {
        this.positions = positions;
        this.transitions = transitions;
    }

    public void simulate(int iterations, boolean ver) {
        int iterator = 0;
        while (iterator < iterations) {
            if (ver) {
                System.out.println();
                System.out.println("iteration " + (iterator + 1));
                System.out.println("-----------------------------------------");
            }
            for (var p : positions) {
                p.markersStatistic();
            }
            for (var t : transitions) {
                if (t.transitionPossibility(positions)) {
                    nextTransitions.add(t);
                }
            }
            for (var t : nextTransitions) {
                t.choiceProbability = (double) 1 / nextTransitions.size();
            }
            double r = random.nextDouble();
            for (int i = 0; i < nextTransitions.size(); i++) {
                if (r < nextTransitions.get(i).choiceProbability) {
                    positions = nextTransitions.get(i).performTransaction(positions, ver);
                    break;
                } else
                    r -= nextTransitions.get(i).choiceProbability;
            }
            nextTransitions.clear();
            iterator++;
        }
        if (ver)
            System.out.println("------------------------Verification-----------------------");
        printStatistics(iterations);
    }

    public void printStatistics(int iterations) {
        System.out.println();
        System.out.println("--------------------Statistics--------------------");
        System.out.println(" name    " + " min " + " max " + " average ");
        for (var p : positions) {
            p.markersAvarage =(float) p.markersAvarage / iterations;
            System.out.println(" " + p.name + " " + p.markersMin + " " + p.markersMax + " " + p.markersAvarage);
        }
    }

}

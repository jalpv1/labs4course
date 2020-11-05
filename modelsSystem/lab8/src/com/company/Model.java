package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    public List<Place> places ;
    public List<Transition> transitions;
    public List<Transition> nextTransitions = new ArrayList<Transition>();
    Random random = new Random();

    public Model(List<Place> places, List<Transition> transitions) {
        this.places = places;
        this.transitions = transitions;
    }

    public void simulate(int iterationsAmount, boolean doVerification) {
        int iteration = 0;
        while (iteration < iterationsAmount) {
            if (doVerification) {
                System.out.println();
                System.out.println("iteration " + (iteration + 1));
                System.out.println("-----------------------------------------");
            }
            for (var place : places) {
                place.markersStatistic();
            }
            for (var transition : transitions) {
                if (transition.canTransit(places)) {
                    nextTransitions.add(transition);
                }
            }
            if (nextTransitions.size() == 0) {
                break;
            }
            for (var transition : nextTransitions) {
                transition.choiceProbability = (double) 1 / nextTransitions.size();
            }
            double randomValue = random.nextDouble();
            for (Transition nextTransition : nextTransitions) {
                if (randomValue < nextTransition.choiceProbability) {
                    places = nextTransition.doTransition(places, doVerification);
                    break;
                } else
                    randomValue -= nextTransition.choiceProbability;
            }
            nextTransitions.clear();
            iteration++;
        }
        printStatistics(iterationsAmount);
    }

    public void printStatistics(int iterations) {
        System.out.println("--------------------Statistics--------------------");
        for (var place : places) {
            place.markersAvarage = (float) place.markersAvarage / iterations;
            System.out.println(" name " + place.name + "      min = " + place.markersMin + " max = " + place.markersMax + " average  = " + place.markersAvarage);
        }
    }

}

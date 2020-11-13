package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    public List<Place> places;
    public List<Transition> transitions;
    public List<Transition> nextTransitions = new ArrayList<>();
    Random rnd = new Random();

    public Model(List<Place> places, List<Transition> transitions) {
        this.places = places;
        this.transitions = transitions;
    }

    public void simulate(int iterationsAmount) {
        int iteration = 0;
        while (iteration < iterationsAmount) {
                System.out.println("iteration " + (iteration + 1));
                System.out.println("-----------------------------------------");

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
            double randomValue = rnd.nextDouble();

            transitions.forEach(transition -> transition.choiceProbability = (double) (1 / nextTransitions.size()));

            for (Transition next : nextTransitions) {

                if (next.choiceProbability > randomValue) {
                    places = next.doTransition(places);
                    break;
                }
                else
                    randomValue -= next.choiceProbability;
            }
            nextTransitions.clear();
            iteration++;
        }
        doStatistics(iterationsAmount);
    }

    public void doStatistics(int iterationsAmount) {
        System.out.println("--------------------Statistics--------------------");
        for (var place : places) {
            place.markersAvg = (float) place.markersAvg / iterationsAmount;
            System.out.println(" name " + place.title + "      min = " + place.markersMin + " max = " + place.markersMax + " average  = " + place.markersAvg);
        }
    }

}

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Transition {
    public List<Arc> arcsInComming = new ArrayList<>();
    public List<Arc> arcsOutComming = new ArrayList<>();
    public String name = "";
    public double choiceProbability = 0;

    public Transition(String name) {
        this.name = name;
    }

    public boolean canTransit(List<Place> places) {
        boolean canTransit = true;
        List<String> fromPlaceNames = arcsInComming.stream()
                .map(x -> x.placeFrom.name)
                .collect(Collectors.toList());
        List<Place> connectedPlaces = places.stream()
                .filter(x -> fromPlaceNames.contains(x.name))
                .collect(Collectors.toList());
        for (int i = 0; i < connectedPlaces.size(); i++) {
            if (connectedPlaces.get(i).markersCount < arcsInComming.get(i).multiplicity) {
                canTransit = false;
                break;
            }
        }
//        List<Place> canTransitPlaces =connectedPlaces.stream()
//                .filter(x-> x.markersCount>arcsInComming.get(arcsInComming.indexOf(x)).multiplicity).collect(Collectors.toList());
//
         return canTransit;
    }

    public List<Place> doTransition(List<Place> places, boolean doVerification) {
        if (doVerification) {
            System.out.println("Transition " + name + " :");
            System.out.println("Takes markers from: ");
        }

        for (Arc arc : arcsInComming) {
            places.stream()
                    .filter(x -> x.name.equals(arc.placeFrom.name))
                    .collect(Collectors.toList())
                    .get(0)
                    .markersCount -= arc.multiplicity;
            if (doVerification)
                System.out.print(arc.placeFrom.name);
        }

        if (doVerification)
            System.out.println("Sends markers to: ");

        for (Arc arc : arcsOutComming) {
            places.stream()
                    .filter(x -> x.name.equals(arc.placeTo.name))
                    .collect(Collectors.toList())
                    .get(0)
                    .markersCount += arc.multiplicity;
            if (doVerification)
                System.out.print(arc.placeTo.name);
        }

        if (doVerification)
            System.out.println();
        return places;
    }
}

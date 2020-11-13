package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Place> connectedPlaces = findConnected(places);
        for (int i = 0; i < connectedPlaces.size(); i++) {
            if (connectedPlaces.get(i).markersCount < arcsInComming.get(i).multiplicity) {
                canTransit = false;
                break;
            }
        }
        return canTransit;
    }

    List<Place> findConnected(List<Place> places) {
        List<String> fromPlaceNames = arcsInComming.stream()
                .map(x -> x.placeFrom.title)
                .collect(Collectors.toList());
        return places.stream()
                .filter(x -> fromPlaceNames.contains(x.title))
                .collect(Collectors.toList());
    }

    public List<Place> doTransition(List<Place> places) {
        for (Arc arc : arcsInComming) {
            places.stream()
                    .filter(x -> x.title.equals(arc.placeFrom.title))
                    .collect(Collectors.toList())
                    .get(0)
                    .markersCount -= arc.multiplicity;
            System.out.print("arc place from : " + arc.placeFrom.title);
        }

         System.out.println("Sends markers to: ");

        for (Arc arc : arcsOutComming) {
            places.stream()
                    .filter(x -> x.title.equals(arc.placeTo.title))
                    .collect(Collectors.toList())
                    .get(0)
                    .markersCount += arc.multiplicity;

             System.out.print("arc place to : " + arc.placeTo.title);
        }
        return places;
    }
}

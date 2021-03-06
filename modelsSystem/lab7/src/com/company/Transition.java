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
        return canTransit;
    }
//
//    public List<Place> doTransaction(List<Place> places, boolean ver) {
//        if (ver) {
//            System.out.println("Transition : " + name);
//            System.out.println("Takes markers from: ");
//        }
//        for (Arc arc : arcsIn) {
//            Place place = places.get(0);
//
//            for (Place po : places){
//                if (arc.placeFrom != null) {
//                    if (po.name.equals(arc.placeFrom.name)) {
//                        place = po;
//                        break;
//                    }
//                }
//            }
//            place.markersCount -= arc.multiplicity;
//            if (ver)
//                System.out.println("from name " + arc.placeFrom.name);
//        }
//        if (ver) {
//            System.out.println("Sends markers to: ");
//        }
//        for (Arc arc : arcsOut) {
//            Place place = places.get(0);
//            for (Place po : places){
//                if (arc.placeFrom != null) {
//                    if (po.name.equals(arc.placeFrom.name)) {
//                        place = po;
//                        break;
//                    }
//                }
//            }
//            place.markersCount +=  arc.multiplicity;
//            if (ver)
//                System.out.println("place to name " + arc.placeTo.name);
//        }
//        return places;
//
//    }

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

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transition {
    public List<Arc> arcsInComming = new ArrayList<>();
    public List<Arc> arcsOutComming = new ArrayList<>();
    public String name = "";
    public double choiceProbability = 0.0;

    public Transition(String name) {
        this.name = name;
    }


    public boolean canTransit(List<Place> places) {
        boolean canTransit = true;
        List<Place> connectedPlaces = findConnected(places);
        for (int i = 0; i < connectedPlaces.size(); i++) {
            if (connectedPlaces.get(i).markers.size() < arcsInComming.get(i).multiplicity) {
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

    Place transitMarkerFrom(Place place, int count) {
        for (int i = 0; i < count; i++) {
            place.markers.remove(i);
        }
        return place;
    }

    List<Marker> getMarkers(Place place, int count) {
        List<Marker> m = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            m.add(place.markers.get(i));
        }
        return m;
    }

    Place transitMarkerTo(Place place, List<Marker> m) {
        place.markers.addAll(m);
        return place;
    }

    List<Marker> getSomeMarkers(List<Marker> m2, int count) {
        List<Marker> m = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            m.add(m2.get(i));
        }
        return m;
    }

    public List<Place> doTransition(List<Place> places) {
        ArrayList<Marker> markers = new ArrayList<>();
        for (Arc arc : arcsInComming) {
            Place place = places.stream()
                    .filter(x -> x.title.equals(arc.placeFrom.title))
                    .collect(Collectors.toList())
                    .get(0);

            place.markersCount -= arc.multiplicity;
            System.out.println("arc place from : " + arc.placeFrom.title);
            markers.addAll(getMarkers(place, arc.multiplicity));
           transitMarkerFrom(place, arc.multiplicity);

        }

        System.out.println("Sends markers to: ");

        for (Arc arc : arcsOutComming) {
            Place place = places.stream()
                    .filter(x -> x.title.equals(arc.placeTo.title))
                    .collect(Collectors.toList())
                    .get(0);
            place.markersCount += arc.multiplicity;
            place.markers.addAll(getSomeMarkers(markers, arc.multiplicity));
            System.out.println("arc place to : " + arc.placeTo.title);
        }
        return places;
    }
}

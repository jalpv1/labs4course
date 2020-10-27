package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transition {
    public List<Arc> arcsIn = new ArrayList<>();
    public List<Arc> arcsOut = new ArrayList<Arc>();
    public String name = "";
    public double choiceProbability = 0;

    public Transition(String name) {
        this.name = name;
    }

    public boolean transitionPossibility(List<Position> positions) {
        boolean f = true;
        List<String> fromPositionsNames = arcsIn.stream().map(x -> x.plaseFrom.name).collect(Collectors.toList());
        List<Position> connectedPositions = positions.stream().filter(x -> fromPositionsNames.contains(x.name)).collect(Collectors.toList());
        for (int i = 0; i < connectedPositions.size(); i++) {
            if (connectedPositions.get(i).markersCount < arcsIn.get(i).n) {
                f = false;
                break;
            }
        }
        return f;
    }

    public List<Position> performTransaction(List<Position> positions, boolean ver) {
        if (ver) {
            //Console.WriteLine();
            System.out.println("Transition : " + name);
            System.out.println("Takes markers from: ");
        }
        for (Arc a : arcsIn) {
            Position position = positions.get(0);//= positions.stream().filter(x -> x.name.equals(a.plaseFrom.name)).findFirst().get();

            for (Position po : positions){
                if (a.plaseFrom != null) {
                    if (po.name.equals(a.plaseFrom.name)) {
                        position = po;
                        break;
                    }
                }
            }
            position.markersCount += 1;
            if (ver)
                System.out.println("from name " + a.plaseFrom.name);
        }
        if (ver) {
            // Console.WriteLine();
            System.out.println("Sends markers to: ");
        }
        for (Arc a : arcsOut) {
            //positions.Where(x = > x.name == a.placeTo.name).FirstOrDefault().markersCount += 1;
            Position position = positions.get(0);//= positions.stream().filter(x -> x.name.equals(a.plaseFrom.name)).findFirst().get();

            for (Position po : positions){
                if (a.plaseFrom != null) {
                    if (po.name.equals(a.plaseFrom.name)) {
                        position = po;
                        break;
                    }
                }
            }
            position.markersCount += 1;
            if (ver)
                System.out.println("place to name " + a.placeTo.name);
        }
        if (ver) {
           //  Console.WriteLine();

        }
        return positions;

    }


}

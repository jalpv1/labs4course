package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        {
            for (int i = 1; i < 5; i++) {
                Place place1 = new Place("p1 - enter", 1);
                Place place2 = new Place("p2 - resource queue", i);
                Place place3 = new Place("p3 - resource is free", 1);
                Place place4 = new Place("p4 - took resource", 0);
                Place place5 = new Place("p5 - exit queue", 0);
                Place place6 = new Place("p6 -  exit1", 0);
                Place place7 = new Place("p7 -  exit2", 0);
                Place place8 = new Place("p8 -  exit3", 0);

                Transition transition1 = new Transition("-income-");
                Transition transition2 = new Transition("-take resource-");
                Transition transition3 = new Transition("-free resource-");
                Transition transition4 = new Transition("-exit1-");
                Transition transition5 = new Transition("-exit2-");
                Transition transition6 = new Transition("-exit3-");

                Arc a1 = new Arc(".in income", place1, transition1, 1);
                Arc a2 = new Arc(".out income(place 1)", place1, 1);
                Arc a3 = new Arc(".out income(place 2))", place2, 1);
                Arc a4 = new Arc(" in take resourse(place 2) ", place2, transition2, 1);
                Arc a5 = new Arc(" in take resourse(place 3) ", place3, transition2, 1);
                Arc a6 = new Arc(" out take resourse ", place4, 1);
                Arc a7 = new Arc(" in free resourse ", place4, transition3, 1);
                Arc a8 = new Arc(" out free resource(place 5) ", place5, 1);
                Arc a9 = new Arc(" out free resource(place 3) ", place3, 1);
                Arc a10 = new Arc(" in exit1 ", place5, transition4, 1);
                Arc a11 = new Arc(" in exit2 ", place5, transition5, 1);
                Arc a112 = new Arc(" in exit3 ", place5, transition6, 1);

                Arc a12 = new Arc(" out exit1", place6, 1);
                Arc a13 = new Arc(" out exit2 ", place7, 1);
                Arc a14 = new Arc(" out exit3 ", place8, 1);

                transition1.arcsInComming.add(a1);
                transition1.arcsOutComming.add(a2);
                transition1.arcsOutComming.add(a3);
                transition2.arcsInComming.add(a4);
                transition2.arcsInComming.add(a5);
                transition2.arcsOutComming.add(a6);
                transition3.arcsInComming.add(a7);
                transition3.arcsOutComming.add(a8);
                transition3.arcsOutComming.add(a9);
                transition4.arcsInComming.add(a10);
                transition4.arcsOutComming.add(a12);
                transition5.arcsInComming.add(a11);
                transition5.arcsOutComming.add(a13);
                transition6.arcsInComming.add(a112);
                transition6.arcsOutComming.add(a14);

                List<Place> places = new ArrayList<>(Arrays.asList(place1, place2, place3, place4, place5, place6, place7));
                for (var place : places) {
                    System.out.println("  markers in  " + place.name + " initial amount " + place.markersCount);
                }
                List<Transition> transactions = new ArrayList<>(Arrays.asList(transition1, transition2, transition3, transition4, transition5));
                Model model = new Model(places, transactions);
                model.simulate(100, true);

            }
        }
    }
}

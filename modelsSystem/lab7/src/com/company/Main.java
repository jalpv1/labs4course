package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        {
            for (int i = 0; i < 5; i++) {
                Position position1 = new Position("[p1 - enter]", 1);
                Position position2 = new Position("[p2 - resource queue]", i);
                Position position3 = new Position("[p3 - resource is free]", 1);
                Position position4 = new Position("[p4 - took resource]", 0);
                Position position5 = new Position("[p5 - exit queue]", 0);
                Position position6 = new Position("[p6 - amount exit1]", 0);
                Position position7 = new Position("[p7 - amount exit2]", 0);
                Position position8 = new Position("[p8 - amount exit3]", 0);

                Transition transition1 = new Transition("[income]");
                Transition transition2 = new Transition("[take resource]");
                Transition transition3 = new Transition("[free resource]");
                Transition transition4 = new Transition("[exit1]");
                Transition transition5 = new Transition("[exit2]");
                Transition transition6 = new Transition("[exit3]");

                Arc a1 = new Arc("[in income]", position1, transition1, 1);
                Arc a2 = new Arc("[out income(pos 1)]", position1, 1);
                Arc a3 = new Arc("[out income(pos 2))]", position2, 1);
                Arc a4 = new Arc("[in take resourse(pos 2)]", position2, transition2, 1);
                Arc a5 = new Arc("[in take resourse(pos 3)]", position3, transition2, 1);
                Arc a6 = new Arc("[out take resourse]", position4, 1);
                Arc a7 = new Arc("[in free resourse]", position4, transition3, 1);
                Arc a8 = new Arc("[out free resource(pos 5)]", position5, 1);
                Arc a9 = new Arc("[out free resource(pos 3)]", position3, 1);
                Arc a10 = new Arc("[in exit1]", position5, transition4, 1);
                Arc a11 = new Arc("[in exit2]", position5, transition5, 1);
                Arc a112 = new Arc("[in exit3]", position5, transition6, 1);

                Arc a12 = new Arc("[out exit1]", position6, 1);
                Arc a13 = new Arc("[out exit2]", position7, 1);
                Arc a14 = new Arc("[out exit3]", position8, 1);

                transition1.arcsIn.add(a1);
                transition1.arcsOut.add(a2);
                transition1.arcsOut.add(a3);
                transition2.arcsIn.add(a4);
                transition2.arcsIn.add(a5);
                transition2.arcsOut.add(a6);
                transition3.arcsIn.add(a7);
                transition3.arcsOut.add(a8);
                transition3.arcsOut.add(a9);
                transition4.arcsIn.add(a10);
                transition4.arcsOut.add(a12);
                transition5.arcsIn.add(a11);
                transition5.arcsOut.add(a13);

                transition6.arcsIn.add(a112);
                transition6.arcsOut.add(a14);

                List<Position> positions = new ArrayList<>(Arrays.asList(position1, position2, position3, position4, position5, position6, position7));
                for (var p : positions) {
                    System.out.println("  markers in  " + p.name + " initial amount " + p.markersCount);
                }
                List<Transition> transactions = new ArrayList<>(Arrays.asList(transition1, transition2, transition3, transition4, transition5));
                Model model = new Model(positions, transactions);
                if (i == 0)
                    continue;
                    //    model.simulate(100, true);
                else
                    model.simulate(100, false);
                System.out.println("-----------------------------------------------------------");

            }
        }
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Transition incommingT = new Transition("incomming");
        Transition goToOven = new Transition("go to oven");
        Transition bakingT = new Transition("baking");

        Place incomming1 = new Place("incomming", 2);
        incomming1.markers = new ArrayList<>(Arrays.asList(new Marker("1"),new Marker("2")));
        Place queue = new Place("queue", 0);
        Place oven = new Place("oven", 0);
        Place baking = new Place("baking", 3);
        baking.markers = new ArrayList<>(Arrays.asList(new Marker("3"),new Marker("4"),new Marker("5")));

        Place exit = new Place("exit", 0);


        Arc incommingInc = new Arc("incommingInc", incomming1, incommingT, 1);
        Arc incomming = new Arc("incomming", incomming1, 1);
        Arc incommingQueue = new Arc("incomming transition to Queue", queue, 3);

        Arc queueToOvenTransition = new Arc("from queue to oven transition ", queue, goToOven, 3);
        Arc fromOvenTransitionToOven = new Arc("from oven transition to oven transition ", oven, 1);

        Arc fromBakingTtoBaking = new Arc("from baking transition to baking transition ", baking, 1);
        Arc fromBakingtoBakingT = new Arc("from baking transition to baking transition ", baking, bakingT,1);


        Arc ovenToExit = new Arc(" from oven to exit transition", oven, bakingT, 1);
        Arc toExit = new Arc(" to exit ", exit,1);

        incommingT.arcsInComming.add(incommingInc);
        incommingT.arcsOutComming.add(incommingQueue);
        incommingT.arcsOutComming.add(incomming);

        goToOven.arcsInComming.add(queueToOvenTransition);
        goToOven.arcsOutComming.add(fromOvenTransitionToOven);

        bakingT.arcsInComming.add(ovenToExit);
        bakingT.arcsInComming.add(fromBakingtoBakingT);
        bakingT.arcsOutComming.add(toExit);
        bakingT.arcsOutComming.add(fromBakingTtoBaking);


        List<Place> places = new ArrayList<>(Arrays.asList(incomming1, queue, oven,baking, exit));
        List<Transition> transitions = new ArrayList<>(Arrays.asList(incommingT, goToOven,bakingT));

        Model model = new Model(places, transitions);
        model.simulate(100);
    }
}

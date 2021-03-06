package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class ModelCreate {
    public Model createModel(int q,int parallel,double d){
        Create c = new Create(2.0);
        Process p1 = new Process(d, parallel);
        Process p2 = new Process(d, parallel);
        Process p3 = new Process(d, parallel);
        Process p4 = new Process(d, parallel);
        c.setNextElement(p1);
        p1.setMaxqueue(q);
        p2.setMaxqueue(q);
        p3.setMaxqueue(q);
        p4.setMaxqueue(q);
        c.setName("c");
        p1.setName("p1");
        p2.setName("p2");
        p3.setName("p3");
        p4.setName("p4");
        p3.setName("p3");
        p4.setName("p4");
        c.setDistribution("exp");
        p1.setDistribution("exp");
        p2.setDistribution("exp");
        p3.setDistribution("exp");
        p4.setDistribution("exp");

        p1.setNextElements(new ArrayList(Arrays.asList(new Process[]{p2, p3})));
        p3.setNextElements(new ArrayList(Arrays.asList(new Process[]{p4})));
        ArrayList<Element> list = new ArrayList<>();
        list.add(c);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        Model model1 = new Model(list);
        model1.simulate(100.0,c);
     return null;
    }
}

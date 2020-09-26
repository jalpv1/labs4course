package com.company;

import java.util.ArrayList;

public class Main {

//    public static void main(String[] args) {
//        // Task 5
//        Create c = new Create(2.0);
//        Process p1 = new Process(1.0,2);
//        Process p2 = new Process(4.0,1);
//        Process p3 = new Process(1.2,1);
//        Process p4 = new Process(1.2,1);
//        Despose despose = new Despose();
//        Despose despose2 = new Despose();
//
//        //   System.out.println("id0 = " + c.getId() + "   id1=" + p1.getId());
//        c.setNextElement(p1);
//        p1.setMaxqueue(5);
//        p2.setMaxqueue(3);
////        p3.setMaxqueue(2);
////        p4.setMaxqueue(2);
//        c.setName("c");
//        p1.setName("p1");
//        p2.setName("p2");
////        p3.setName("p3");
////        p3.setName("p4");
//        c.setDistribution("exp");
//        p1.setDistribution("exp");
//        p2.setDistribution("exp");
//      //  p3.setDistribution("exp");
//        p1.setNextElements(new Process[]{p2});
////        p3.setNextElements(new Process[]{p4});
////        p4.setNextElements(new Element[]{p1,despose});
//        p2.setNextElements(new Despose[]{despose2});
//
//        ArrayList<Element> list = new ArrayList<>();
//        list.add(c);
//        list.add(p1);
//        list.add(p2);
////        list.add(p3);
////        list.add(p4);
//
//        Model model2 = new Model(list);
//           model2.simulate(1000.0);
        //Task 6
//            ModelCreate modelCreate = new ModelCreate();
//            for (int i = 1; i < 5; i++){
//                    try {
//                            Model m = modelCreate.createModel(1, 1,i);
//                            m.simulate(1000);
//                    }
//                    catch (Exception e){
//                            e.printStackTrace();
//                    }
//
//            }
//            Create c2 = new Create(2.0);
//            Process p12 = new Process(1.0, 1);
//            Process p22 = new Process(1.0, 1);
//            Process p32 = new Process(1.2, 1);
//            Process p42 = new Process(1.2, 1);
//
//            //  System.out.println("id0 = " + c.getId() + "   id1=" + p1.getId());
//            c2.setNextElement(p1);
//            p12.setMaxqueue(4);
//            p22.setMaxqueue(4);
//            p32.setMaxqueue(4);
//            p42.setMaxqueue(4);
//
//            c2.setName("c");
//            p12.setName("p1");
//            p22.setName("p2");
//            p32.setName("p3");
//
//            p32.setName("p4");
//
//            c2.setDistribution("exp");
//            p12.setDistribution("exp");
//            p22.setDistribution("exp");
//            p32.setDistribution("exp");
//            p12.setNextElements(new Process[]{p2, p3});
//            p32.setNextElements(new Process[]{p4});
//
//            ArrayList<Element> list2 = new ArrayList<>();
//            list2.add(c2);
//            list2.add(p12);
//            list2.add(p22);
//            list2.add(p32);
//            list2.add(p42);
//
//
//            Model model2 = new Model(list2);
//            model2.simulate(100.0);
//
//            Create c3 = new Create(2.0);
//            Process p13 = new Process(1.0, 1);
//            Process p23 = new Process(1.0, 1);
//            Process p33 = new Process(1.2, 1);
//            Process p43 = new Process(1.2, 1);
//
//            //  System.out.println("id0 = " + c.getId() + "   id1=" + p1.getId());
//            c3.setNextElement(p1);
//            p13.setMaxqueue(4);
//            p23.setMaxqueue(4);
//            p33.setMaxqueue(4);
//            p43.setMaxqueue(4);
//
//            c2.setName("c");
//            p13.setName("p1");
//            p23.setName("p2");
//            p33.setName("p3");
//
//            p33.setName("p4");
//
//            c3.setDistribution("exp");
//            p13.setDistribution("exp");
//            p23.setDistribution("exp");
//            p33.setDistribution("exp");
//            p13.setNextElements(new Process[]{p2, p3});
//            p33.setNextElements(new Process[]{p4});
//
//            ArrayList<Element> list3 = new ArrayList<>();
//            list3.add(c2);
//            list3.add(p12);
//            list3.add(p22);
//            list3.add(p32);
//            list3.add(p42);
//
//
//            Model model3 = new Model(list3);
//            model3.simulate(100.0);
        public static void main(String[] args) {
            // Task 5
            Create c = new Create(2.0);
            Process p1 = new Process(1.0,2);
            Process p2 = new Process(4.0,1);
            c.setNextElement(p1);
            p1.setMaxqueue(5);
            p2.setMaxqueue(3);
            c.setName("c");
            p1.setName("p1");
            p2.setName("p2");
            c.setDistribution("exp");
            p1.setDistribution("exp");
            p2.setDistribution("exp");
            p1.setNextElements(new Process[]{p2});

            ArrayList<Element> list = new ArrayList<>();
            list.add(c);
            list.add(p1);
            list.add(p2);
            Model model2 = new Model(list);
            model2.simulate(10.0);
    }
}

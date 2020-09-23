package com.company;

public class Main {

    public static void main(String[] args) {
        Generator1 g1 = new Generator1();
        Generator2 g2 = new Generator2();
        Generator3 g3 = new Generator3();
        g1.generate();
        g2.generate();
        g3.generate();

        GrGis gr = new GrGis ();
        gr.setVisible(true);
    }
}

package com.company;

import java.util.ArrayList;

public class Generator1 {
    public ArrayList<Double> xArr;
    //rivnomir
    public void generate(){
        xArr = new ArrayList<>();
        double n =10000.0;
        double m = n/2;
        System.out.println("___________________FIRST GENERATOR________________");
        double sumAv = 0;
        double sumDis = 0;
         double mPrf = 0.4987551736746871;
        for (int i = 0; i < n; i++){
            double a = Math.random();
            double x = formula(a,1/mPrf);
            System.out.print(" "+x+" ");
            sumAv = sumAv +x;
            sumDis =sumDis+Math.pow(x -m,2) ;
            xArr.add(x);
        }
        System.out.println(" ");
        System.out.println("M = " + sumAv/n);
        System.out.println("D  = " + sumDis/(n-1));
        System.out.println("___________________FIRST GENERATOR END________________");


    }
    private double formula(double a, double lambda){
        return ((-1.0/lambda)*Math.log(a));
    }
}

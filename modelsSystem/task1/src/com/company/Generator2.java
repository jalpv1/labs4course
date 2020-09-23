package com.company;

public class Generator2 {
    public void generate(){
        System.out.println("___________________SECOND GENERATOR________________");
        double n =10000.0;
        double m = n/2;
        double sumAv = 0;
        double sumDis = 0;
        for (int i = 0; i < 10000; i++) {
            double a = Math.random();
            double sumA = 0.0;
            for (int j =0 ; j< 12;j++){
                sumA = sumA+a;
            }
            double x = formula2(sumA,3,4);
            System.out.print(" "+x+" ");
            sumAv = sumAv +x;
            sumDis =sumDis+Math.pow(x -m,2) ;
        }
        System.out.println(" ");
        System.out.println("M = " + sumAv/n);
        System.out.println("D  = " + sumDis/(n-1));
        System.out.println("______________SECOND GENERATOR END_______________");


    }
    private double formula2(double a, double sigma,double alpha){

        return a*sigma + alpha;
    }
    private double formula1(double a){
        return a - 6;
    }
}

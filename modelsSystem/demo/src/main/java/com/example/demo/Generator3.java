package com.example.demo;

public class Generator3 {
    public void generate(){
        double a = Math.pow(5,2);
        double c = Math.pow(2,11);
        System.out.println("___________________THIRD GENERATOR________________");
        double n =10000.0;
        double m = n/2;
        double sumAv = 0;
        double sumDis = 0;
        double zprev =  Math.random();;
        for (int i = 1; i < 10000; i++){
            double z =  a * zprev % c;
            double x = z/c;
            zprev = z;
            System.out.print(" "+x+" ");
            sumAv = sumAv +x;
            sumDis =sumDis+Math.pow(x -m,2) ;
        }
        System.out.println(" ");
        System.out.println("M = " + sumAv/n);
        System.out.println("D  = " + sumDis/(n-1));
        System.out.println("______________THIRD GENERATOR END_______________");

    }
    private double formula(double a, int lambda){
        return ((-1.0/lambda)*Math.log(a));
    }
}

package com.company;
public class TimeGenerator {
    public static double generate() {
        double n = 10000.0;
        double mPrf = 0.4987551736746871;
        double a = Math.random();
        return formula(a, 1 / mPrf);
    }
    public  static double endTime(){
        return generate();
    }
    public static double createTime(){
        return generate();
    }

    private static double formula(double a, double lambda) {
        return ((-1.0 / lambda) * Math.log(a));
    }
}

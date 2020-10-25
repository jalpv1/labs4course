package com.company.hospital_simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FunRand {
    /**
     * Generates a random value according to an exponential
     distribution
     *
     * @param timeMean mean value

     * @return a random value according to an exponential
    distribution
     */
    public static double Exp(double timeMean) {
        double a = 0;
        while (a == 0) {
            a = Math.random();
        }
        a = -timeMean * Math.log(a);
        return a;
    }
    /**
     * Generates a random value according to a uniform
     distribution
     *
     * @param timeMin
     * @param timeMax
     * @return a random value according to a uniform distribution
     */
    public static double Unif(double timeMin, double timeMax) {
        double a = 0;
        while (a == 0) {
            a = Math.random();
        }
        a = timeMin + a * (timeMax - timeMin);
        return a;
    }
    /**
     * Generates a random value according to a normal (Gauss)
     distribution
     *
     * @param timeMean
     * @param timeDeviation
     * @return a random value according to a normal (Gauss)
    distribution
     */
    public static double Norm(double timeMean, double
            timeDeviation) {
        double a;
        Random r = new Random();
        a = timeMean + timeDeviation * r.nextGaussian();
        return a;
    }

    public static double Erlang(double timeMean, double timeDeviation)
    {
        double a = -1 / timeDeviation;
        double[] R = new double[] { 0.43, 0.80, 0.29, 0.67, 0.19, 0.96, 0.02, 0.73, 0.50, 0.33, 0.14, 0.71 };
        double r = 1;
        for (int i = 0; i < (int)timeMean; i++)
        {
            r *= R[i];
        }
        a *= Math.log(r);
        return a;
    }

    public static  int randomProbability(List<Probability> probabilities)
    {
        //int x = 0;
        //int a = random.Next(0, 100);
        //for (int i = 0; i < probabilities.Count; i++)
        //{
        //    if (a >= probabilities[i].MinProbability && a <= probabilities[i].MaxProbability)
        //    {
        //        x = i;
        //    }
        //}
        List<Double> vers = new ArrayList<>();
        for (int i = 0; i < probabilities.size(); i++) {
            vers.add(0.0);
        }
        double sum = probabilities.stream().mapToDouble(z -> z.maxProbability).sum();
        vers.set(0,probabilities.get(0).maxProbability / sum);
        for (int i = 1; i < probabilities.size() - 1; i++)
        {
            vers.set(i,probabilities.get(i).maxProbability / sum + vers.get(i - 1));
        }
        vers.set(vers.size() -1,1.0);
        double rnd = Math.random();
        for (int i = 0; i < probabilities.size(); i++)
        {
            if (vers.get(i) > rnd)
                return i;
        }
        return probabilities.size() - 1;
    }
}

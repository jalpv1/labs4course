package com.company.hospital_simulation;


import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PatientType {
    public int index;
    public double frequency;
    public double avgTimeOfRegistration;
    public int quantity;
    public double waitingTime;

    public PatientType(int id, double frequency, double avgTimeOfRegistration)
    {
        index = id;
        this.frequency = frequency;
        this.avgTimeOfRegistration = avgTimeOfRegistration;
        quantity = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getAvgTimeOfRegistration() {
        return avgTimeOfRegistration;
    }

    public void setAvgTimeOfRegistration(double avgTimeOfRegistration) {
        this.avgTimeOfRegistration = avgTimeOfRegistration;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int ChooseProbability(List<PatientType> probabilities)
    {
        int x = 0;
        double a = Math.random();
        probabilities = probabilities.stream().sorted(new PatientComparator()).collect(Collectors.toList());
        double sum = probabilities.stream().mapToDouble(PatientType::getFrequency).sum();
        for (int i = 0; i < probabilities.size(); i++)
        {
            if (a < sum)
            {
                x = probabilities.get(i).getIndex();
            }
            sum -= probabilities.get(i).getFrequency();
        }
        return x;
    }

    public void incrementQuanity(){
        this.quantity++;
    }
}


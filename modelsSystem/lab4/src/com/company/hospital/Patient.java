package com.company.hospital;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Patient {
    private int index;
    private int frequency;
    private double averageTimeOfReg;
    private int quantity;
    private double waitingTime;

    public Patient(int index, int frequency, double averageTimeOfReg) {
        this.index = index;
        this.frequency = frequency;
        this.averageTimeOfReg = averageTimeOfReg;
    }
    public int ChooseProbability(List<Patient> probabilities){
        Random random = new Random();
        int x = 0;
        double a = random.nextDouble();
        probabilities = probabilities.stream().sorted((x1,x2) -> {
            if(x1.frequency > x2.frequency){
                return 1;
            }
            return 0;
        } ).collect(Collectors.toList());
        double sum = probabilities.stream().mapToInt(el-> el.frequency).sum();
        for (Patient probability : probabilities) {
            if (a < sum) {
                x = probability.index;
            }
            sum -= probability.frequency;
        }
       return x;
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double getAverageTimeOfReg() {
        return averageTimeOfReg;
    }

    public void setAverageTimeOfReg(double averageTimeOfReg) {
        this.averageTimeOfReg = averageTimeOfReg;
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
}

package com.company.hospitaltask;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PatientType {
    private int index;
    private double frequency;
    private double averageTimeOfReg;
    private int quantity;
    private double waitingTime;

    public PatientType(int index, double frequency, double averageTimeOfReg) {
        this.index = index;
        this.frequency = frequency;
        this.averageTimeOfReg = averageTimeOfReg;
    }
    public int ChooseProbability(List<PatientType> probabilities){
        Random random = new Random();
        int x = 0;
        double a = random.nextDouble();
        probabilities = probabilities.stream().sorted(Comparator
                .comparingDouble(PatientType::getFrequency).reversed())
                .collect(Collectors.toList());
        double sum = probabilities.stream().mapToDouble(el-> el.frequency).sum();
        for (PatientType probability : probabilities) {
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

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
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

package com.company.hospital;

import com.company.algorithm.Element;
import com.company.randomer.FunRand;

import java.util.ArrayList;
import java.util.List;

public class ElementH {
    private String name;
    private double tnext;
    private double delayMean, delayDev;
    private String distribution;
    private int quantity;
    private double tcurr;
    private List<ElementH> state;
    private ElementH nextElement;
    private static int nextId = 0;
    private int id;
    private List<ElementH> nextElements;
    private int queue;
    private int transferedCount;
    public List<ElementH> getNextElements() {
        return nextElements;
    }

    public int getTransferedCount() {
        return transferedCount;
    }

    public void setTransferedCount(int transferedCount) {
        this.transferedCount = transferedCount;
    }

    public void setNextElements(List<ElementH> nextElements) {
        this.nextElements = nextElements;
    }

    public ElementH() {

        tnext = 0.1;
        delayMean = 1.0;
        distribution = "exp";
        tcurr = tnext;
        state = new ArrayList<>();
        nextElement = null;
        id = nextId;
        nextId++;
        name = "element" + id;
    }

    public ElementH(double delay) {
        name = "anonymus";
        tnext = 0.1;
        delayMean = delay;
        distribution = "";
        tcurr = tnext;
        state = new ArrayList<>();
        nextElement = null;
        id = nextId;
        nextId++;
        name = "element" + id;
    }

    public ElementH(String nameOfElement, double delay) {
        name = nameOfElement;
        tnext = 0.1;
        delayMean = delay;
        distribution = "exp";
        tcurr = tnext;
        state = new ArrayList<>();
        nextElement = null;
        id = nextId;
        nextId++;
        name = "element" + id;
    }

    public double getDelay() {
        double delay = getDelayMean();
        if ("exp".equalsIgnoreCase(getDistribution())) {
            delay = FunRand.Exp(getDelayMean());
        } else {
            if ("norm".equalsIgnoreCase(getDistribution())) {
                delay = FunRand.Norm(getDelayMean(),
                        getDelayDev());
            } else {
                if ("unif".equalsIgnoreCase(getDistribution())) {
                    delay = FunRand.Unif(getDelayMean(),
                            getDelayDev());
                } else {
                    if ("".equalsIgnoreCase(getDistribution()))
                        delay = getDelayMean();
                }
            }
        }

        return delay;
    }


    public double getDelayDev() {
        return delayDev;
    }

    public void setDelayDev(double delayDev) {
        this.delayDev = delayDev;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }


    public int getQuantity() {
        return quantity;
    }

    public double getTcurr() {
        return tcurr;
    }

    public void setTcurr(double tcurr) {
        this.tcurr = tcurr;
    }

    public List<ElementH> getState() {
        return state;
    }

    public void setState(List<ElementH> state) {
        this.state = state;
    }

    public ElementH getNextElement() {
        return nextElement;
    }

    public void setNextElement(ElementH nextElement) {
        this.nextElement = nextElement;
    }

    public void inAct(int patientType) {

    }

//    public int getQueue() {
//        return queue;
//    }
//
//    public void setQueue(int queue) {
//        this.queue = queue;
//    }

    public void outAct(int count) {
        quantity = quantity + count;
    }

    public double getTnext() {
        return tnext;
    }


    public void setTnext(double tnext) {
        this.tnext = tnext;
    }

    public double getDelayMean() {
        return delayMean;
    }

    public void setDelayMean(double delayMean) {
        this.delayMean = delayMean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void printResult() {
        // System.out.println(getName() + "  quantity = " + quantity);
    }

    public void printInfo() {
//        System.out.println(getName() + " state= " + state +
//                " quantity = " + quantity +
//                " tnext= " + tnext);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void doStatistics(double delta) {

    }
}

package com.company.algorithm;

import com.company.randomer.FunRand;

import java.util.ArrayList;
import java.util.List;

public class Element {
    private String name;
    private double tnext;
    private double delayMean, delayDev;
    private String distribution;
    private int quantity;
    private double tcurr;
    private int state;
    private Element nextElement;
    private static int nextId = 0;
    private int id;
    private List<Element> nextElements;
    private int queue;
    private int transferedCount;
    public List<Element> getNextElements() {
        return nextElements;
    }

    public int getTransferedCount() {
        return transferedCount;
    }

    public void setTransferedCount(int transferedCount) {
        this.transferedCount = transferedCount;
    }

    public void setNextElements(List<Element> nextElements) {
        this.nextElements = nextElements;
    }

    public Element() {

        tnext = 0.1;
        delayMean = 1.0;
        distribution = "exp";
        tcurr = tnext;
        state = 0;
        nextElement = null;
        id = nextId;
        nextId++;
        name = "element" + id;
    }

    public Element(double delay) {
        name = "anonymus";
        tnext = 0.1;
        delayMean = delay;
        distribution = "";
        tcurr = tnext;
        state = 0;
        nextElement = null;
        id = nextId;
        nextId++;
        name = "element" + id;
    }

    public Element(String name, double delayMean, double delayDev, String distribution) {
        this.name = name;
        this.delayMean = delayMean;
        this.delayDev = delayDev;
        this.distribution = distribution;
    }

    public Element(String nameOfElement, double delay) {
        name = nameOfElement;
        tnext = 0.1;
        delayMean = delay;
        distribution = "exp";
        tcurr = tnext;
        state = 0;
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
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTcurr() {
        return tcurr;
    }

    public void setTcurr(double tcurr) {
        this.tcurr = tcurr;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Element getNextElement() {
        return nextElement;
    }

    public void setNextElement(Element nextElement) {
        this.nextElement = nextElement;
    }

    public void inAct(int c) {

    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public void outAct(int count) {
        quantity = quantity + count;
    }
    public void outAct() {
        quantity = quantity + 1;
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

package com.company.hospital_simulation;


import java.util.ArrayList;
import java.util.List;

public class Element {

    private String name;
    private double tNext;
    private double averageDelay;
    protected double devDelay;
    private String distribution;
    private int quantity;
    private double tCurrent;
    private int state;
    private Element nextElement;
    private List<Element> nextElements;

    public boolean isNext;
    private int nextId;
    public int id;

    public Element() {
        tNext = 0.0;
        averageDelay = 1.0;
        distribution = "exp";
        tCurrent = tNext;
        state = 0;
        id = nextId;
        nextId++;
        name = "element " + name;
    }

    public Element(double delay, String name, String distribution, double devDelay) {
        this.name = name;
        tNext = 0.0;
        averageDelay = delay;
        this.devDelay = devDelay;
        this.distribution = distribution;
        tCurrent = tNext;
        state = 0;
        id = nextId;
        nextId++;
        isNext = true;
        nextElements = new ArrayList<>();
        //NextProcesses = new List<Process>();
        this.name = "element " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double gettNext() {
        return tNext;
    }

    public void settNext(double tNext) {
        this.tNext = tNext;
    }

    public double getAverageDelay() {
        return averageDelay;
    }

    public void setAverageDelay(double averageDelay) {
        this.averageDelay = averageDelay;
    }

    public double getDevDelay() {
        return devDelay;
    }

    public void setDevDelay(double devDelay) {
        this.devDelay = devDelay;
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

    public double gettCurrent() {
        return tCurrent;
    }

    public void settCurrent(double tCurrent) {
        this.tCurrent = tCurrent;
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

    public List<Element> getNextElements() {
        return nextElements;
    }

    public void setNextElements(List<Element> nextElements) {
        this.nextElements = nextElements;
    }

    public boolean isNext() {
        return isNext;
    }

    public void setNext(boolean next) {
        isNext = next;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDelay() {
        double delay = 0.0;
        if ("exp".equalsIgnoreCase(getDistribution())) {
            delay = FunRand.Exp(averageDelay);
        } else {
            if ("norm".equalsIgnoreCase(distribution)) {
                delay = FunRand.Norm(averageDelay, devDelay);
            } else {
                if ("unif".equalsIgnoreCase(getDistribution())) {
                    delay = FunRand.Unif(averageDelay, devDelay);
                } else {
                    if ("erlang".equalsIgnoreCase(getDistribution())) {
                        delay = FunRand.Erlang(averageDelay, devDelay);
                    } else {
                        if ("".equalsIgnoreCase(distribution))
                            delay = devDelay;
                    }
                }
            }
        }
        return delay;
    }

    public void inAct(int i) {

    }

    public void outAct() {
        quantity++;
    }

    public void printResult() {
        System.out.println("Name:" + name + "; quantity =" + quantity);
    }

    public void printInfo() {
        System.out.println("Name:" + name + "; state = " + state + "; quantity =" + quantity + "; tnext = " + tNext);
    }

    public void countStatistics(double delta) {
    }

    public void incrementQuantity(){
        this.quantity++;
    }
}

package com.company.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public  class Process extends Element {

    private int queue, maxqueue, failure;
    private  int maxParallel;

    private double meanQueue;
    private double meanLoad;
    private double probabilityFailure;
    private int maxQueueInSimulation = 0;
    private double maxLoad = 0;
    private Element otherProcess;
    private double delays= 0;
    private ArrayList<Element>  nextElements;

    public ArrayList<Element> getNextElements() {
        return nextElements;
    }

    public Element getOtherProcess() {
        return otherProcess;
    }

    public Process() {
    }

    public void setOtherProcess(Element otherProcess) {
        this.otherProcess = otherProcess;
    }

    public double getDelays() {
        return delays;
    }

    public void setDelays(double delays) {
        this.delays = delays;
    }

    public void setNextElements(ArrayList<Element> nextElements) {
        this.nextElements = nextElements;
    }

    public Process(double delay,double delays, int maxParallel) {
        super(delay);
        queue = 0;
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
        this.maxParallel = maxParallel;
        this.delays = delays;
    }
    public Process(double delay,double delays,String name, int maxParallel) {
        super(delay);
        queue = 0;
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
        this.maxParallel = maxParallel;
        this.delays = delays;
        this.setName(name);
    }
    public Process(double delay,double delays,String name, String distr) {
        super(delay);
        queue = 0;
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
        setDistribution(distr);
        this.delays = delays;
        this.setName(name);
    }
    @Override
    public void inAct(int count) {
        double delta = maxParallel - getState();
        if (count < delta && count >0) {
            setState(count + getState());
            count = 0;
        } else {
            count = (int) (count - delta);
            setState(maxParallel);

        }
        super.setTnext(super.getTcurr() + super.getDelay());
        if (count > 0) {
            delta = maxqueue - queue;
            if (delta > count) {
                queue = queue + count;
                count = 0;
            } else {
                count = count - (int) delta;
                queue = maxqueue;
            }
            if (count > 0) {
                failure = failure + count;
            }
        }
//        if (super.getState() == 0) {
//            super.setState(1);
//            super.setTnext(super.getTcurr() + super.getDelay());
//        } else {
//            if (getQueue() < getMaxqueue()) {
//                setQueue(getQueue() + 1);
//            } else {
//                failure++;
//            }
//        }
    }
    @Override
    public void outAct() {
        Random rnd = new Random();
        super.outAct();
        super.setTnext(Double.MAX_VALUE);
        setState(getState() - 1);
        if (getQueue() - otherProcess.getQueue() >=2) {
            setQueue(getQueue() - 1);
           otherProcess.setQueue(otherProcess.getQueue()-1);
           setTransferedCount(getTransferedCount()+1);

            //setState(getState() + 1);
           // super.setTnext(super.getTcurr() + super.getDelay());
        }
        if(getQueue() > 0){
            setQueue(getQueue() - 1);
            setState(getState() + 1);
            super.setTnext(super.getTcurr() + super.getDelay());
        }
        if (nextElements != null && nextElements.size() > 0) {
            nextElements.get(rnd.nextInt(nextElements.size())).inAct(1);
        }
    }
//    @Override
//    public void outAct(int c) {
//        Random rnd= new Random();
//        super.outAct(1);
//        super.setTnext(Double.MAX_VALUE);
//        setState(getState() - 1 );
//        if(getQueue() > 0){
//            setQueue(getQueue() - 1);
//            setState(getState() + 1);
//            super.setTnext(super.getTcurr() + super.getDelay());
//        }
//        if(nextElements != null && nextElements.size()>0){
//            nextElements.get(rnd.nextInt(nextElements.size())).inAct(1);
//        }

//        super.outAct();
//        super.setTnext(Double.MAX_VALUE);
//        super.setState(0);
//
//        if (getQueue() > 0) {
//            setQueue(getQueue() - 1);
//            super.setState(1);
//            super.setTnext(super.getTcurr() + super.getDelay());
//        }

    public double getMeanLoad() {
        return meanLoad;
    }

    public void setMeanLoad(double meanLoad) {
        this.meanLoad = meanLoad;
    }


    public int getFailure() {
        return failure;
    }

    public int getQueue() {
        return queue;
    }


    public void setQueue(int queue) {
        this.queue = queue;
    }


    public int getMaxqueue() {
        return maxqueue;
    }

    public double getProbabilityFailure() {
        return probabilityFailure;
    }

    public void setProbabilityFailure(double probabilityFailure) {
        this.probabilityFailure = probabilityFailure;
    }

    public int getMaxQueueInSimulation() {
        return maxQueueInSimulation;
    }

    public void setMeanQueue(double meanQueue) {
        this.meanQueue = meanQueue;
    }

    public void setMaxQueueInSimulation(int maxQueueInSimulation) {
        this.maxQueueInSimulation = maxQueueInSimulation;
    }

    public void setMaxqueue(int maxqueue) {
        this.maxqueue = maxqueue;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("failure = " + this.getFailure());
    }

    @Override
    public void doStatistics(double delta) {
        meanQueue = getMeanQueue() + queue * delta;
        meanLoad = meanLoad + (getState() * delta);
        if (maxQueueInSimulation < queue) {
            maxQueueInSimulation = queue;
        }
        if(maxLoad < meanLoad){
            maxLoad = meanLoad;
        }


    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public void doResultsStatistics() {
//        if (failure != 0 && getQuantity() != 0) {
//
//            probabilityFailure = (double) failure / getQuantity();
//            meanQueue = meanQueue / getTcurr();
//        }
    }

    public int getMaxParallel() {
        return maxParallel;
    }

    public void setMaxParallel(int maxParallel) {
        this.maxParallel = maxParallel;
    }

    public double getMeanQueue() {
        return meanQueue;
    }
}

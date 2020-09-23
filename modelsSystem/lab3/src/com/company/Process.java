package com.company;

public class Process extends Element {

    private int queue, maxqueue, failure;
    private double meanQueue;
    private double meanLoad;

    private double imVidmov;
    private int maxQueueInSimulation;

    public Process(double delay) {
        super(delay);
        queue = 0;
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;
    }

    @Override
    public void inAct() {
        if (super.getState() == 0) {
            super.setState(1);
            super.setTnext(super.getTcurr() + super.getDelay());
        } else {
            if (getQueue() < getMaxqueue()) {
                setQueue(getQueue() + 1);
            } else {
                failure++;
            }
        }
    }

    @Override
    public void outAct() {
        super.outAct();
        super.setTnext(Double.MAX_VALUE);
        super.setState(0);

        if (getQueue() > 0) {
            setQueue(getQueue() - 1);
            super.setState(1);
            super.setTnext(super.getTcurr() + super.getDelay());
        }
    }

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

    public double getImVidmov() {
        return imVidmov;
    }

    public void setImVidmov(double imVidmov) {
        this.imVidmov = imVidmov;
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
        meanLoad = meanLoad + (getState()*delta);
        if(maxQueueInSimulation < queue){
            maxQueueInSimulation = queue ;
        }


    }
    public void doResultsStatistics(){
        if(failure != 0 && getQuantity() != 0) {

            imVidmov = (double) failure / getQuantity();
            meanQueue = meanQueue/getTcurr();
        }
    }

    public double getMeanQueue() {
        return meanQueue;
    }
}

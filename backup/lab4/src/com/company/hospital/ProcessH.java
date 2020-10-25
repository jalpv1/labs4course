package com.company.hospital;

import com.company.algorithm.Element;

import java.util.List;
import java.util.Random;

public class ProcessH extends ElementH {
    private List<ElementH> queue;
    private List<ElementH> state;
    private List<Integer> state2;
    private List<Integer> queue2;

    private double delay_std;
    private int maxParallel;
    private int failure;
    private int maxQueue;
    private int meanQueue;
    private double workload;
    private double in_wait;
    private double time_wait;
    private double delay_sum;
    private List<ElementH> nextElements;

    @Override
    public void inAct(int patientType) {
        if(state.size()<maxParallel){
            state2.add(patientType);
        }
        else if(queue2.size()<maxQueue){
            queue2.add(patientType);
        }
        super.setTnext(super.getTcurr() + super.getDelay());

    }

    @Override
    public void outAct(int count) {
      //  super.outAct(count);
        Random rnd = new Random();
        super.setTnext(Double.MAX_VALUE);
        int patintType = -3;
        if(state2.size()>0){
            patintType = state2.get(0);
            state2.remove(0);
        }
        if(queue2.size()>0){
            int patient_type_queue = queue2.get(0);
            queue2.remove(0);
            state2.add(patient_type_queue);
        }
        if(getNextElements().size()>0 && patintType !=-3){
            ElementH e = nextElements.get(rnd.nextInt(getNextElements().size()));
            e.outAct(patintType);
            setTnext(super.getTcurr() + super.getDelay());

        }
    }

    public List<ElementH> getQueue() {
        return queue;
    }

    public void setQueue(List<ElementH> queue) {
        this.queue = queue;
    }

    @Override
    public List<ElementH> getState() {
        return state;
    }

    public void setState(List<ElementH> state) {
        this.state = state;
    }

    public double getDelay_std() {
        return delay_std;
    }

    public void setDelay_std(double delay_std) {
        this.delay_std = delay_std;
    }

    public int getMaxParallel() {
        return maxParallel;
    }

    public void setMaxParallel(int maxParallel) {
        this.maxParallel = maxParallel;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getMaxQueue() {
        return maxQueue;
    }

    public void setMaxQueue(int maxQueue) {
        this.maxQueue = maxQueue;
    }

    public int getMeanQueue() {
        return meanQueue;
    }

    public void setMeanQueue(int meanQueue) {
        this.meanQueue = meanQueue;
    }

    public double getWorkload() {
        return workload;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public double getIn_wait() {
        return in_wait;
    }

    public void setIn_wait(double in_wait) {
        this.in_wait = in_wait;
    }

    public double getTime_wait() {
        return time_wait;
    }

    public void setTime_wait(double time_wait) {
        this.time_wait = time_wait;
    }

    public double getDelay_sum() {
        return delay_sum;
    }

    public void setDelay_sum(double delay_sum) {
        this.delay_sum = delay_sum;
    }

    @Override
    public List<ElementH> getNextElements() {
        return nextElements;
    }

    public void setNextElements(List<ElementH> nextElements) {
        this.nextElements = nextElements;
    }

    public List<Integer> getState2() {
        return state2;
    }

    public void setState2(List<Integer> state2) {
        this.state2 = state2;
    }

    public List<Integer> getQueue2() {
        return queue2;
    }

    public void setQueue2(List<Integer> queue2) {
        this.queue2 = queue2;
    }
}

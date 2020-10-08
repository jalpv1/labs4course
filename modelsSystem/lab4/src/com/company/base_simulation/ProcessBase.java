package com.company.base_simulation;

import com.company.bank_simulation.Channel;
import com.company.bank_simulation.ChannelComparator2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProcessBase extends ElementBase {

    private int queueLength;
    private int maxQueueLength;
    private int failure;
    private double probabilityFailure;
    private double averageQueue;
    private int maxQueueObserved;
    private double averageQueueTime;
    private double averageProcessingTime;
    private double averageWorkload;
    private double averageAmount;
    private double maxWorkload;
    private int maxParallel;
    private List<ProcessBase> NextProcesses;
    private ArrayList<PobabilityBase> pobabilities;

    public Random random = new Random();
    public List<Channel> channels;
    public int NumberOfTasks;
    public int TransferedCount;
    public ProcessBase OtherProcess;

    public ProcessBase() {
        NextProcesses = new ArrayList<>();
        queueLength = 0;
        averageQueue = 0.0;
        maxQueueObserved = 0;
        averageWorkload = 0;
    }

    public ProcessBase(double delay, String name, String distribution, int maxQueueLength, int maxParallel, double devDelay) {
        super( name, delay, devDelay , distribution);
        NextProcesses = new ArrayList<>();
        queueLength = 0;
        this.maxQueueLength = maxQueueLength;
        averageQueue = 0.0;
        maxQueueObserved = 0;
        averageWorkload = 0;
        this.maxParallel = maxParallel;
        channels = new ArrayList<>();
        NumberOfTasks = 1;
        OtherProcess = new ProcessBase();
        for (int i = 0; i < maxParallel; i++) {
            channels.add(new Channel("Name->Channel " + i + 1, 0.0, true));
        }
    }

    @Override
    public void inAct(int numberOfTasks) {
        int numberOfFreeDevices = maxParallel - getState();
        NumberOfTasks = numberOfTasks;
        if (numberOfTasks <= numberOfFreeDevices && numberOfTasks > 0) {
            setState(numberOfTasks + getState());
            numberOfTasks = 0;
        } else if (numberOfTasks > numberOfFreeDevices) {
            numberOfTasks -= numberOfFreeDevices;
            setState(maxParallel);
        }
        setTnext(getTcurr() + getDelay());

        if (numberOfTasks > 0) {
            int numberOfFreePlaces = maxQueueLength - queueLength;
            if (numberOfTasks < numberOfFreePlaces) {
                queueLength += numberOfTasks;
                numberOfTasks = 0;
            } else {
                numberOfTasks -= numberOfFreePlaces;
                queueLength = maxQueueLength;
            }
        }

        if (numberOfTasks > 0) {
            failure += numberOfTasks;
        }
    }

    public List<Channel> outChannel(double t) {
        List<Channel> outChannels = new ArrayList<>();
        channels.stream().sorted(new ChannelComparator2()).filter(c -> c.getTimeOut() < t && !c.isFree()).forEach(c -> {
            c.setFree(true);
            outChannels.add(c);
        });
        return outChannels;
    }

    public void inChannel() {
        int count = 0;
        int numberOfFreeDevices = maxParallel - getState();
        if (NumberOfTasks <= numberOfFreeDevices && NumberOfTasks > 0) {

            for (int i = 0; i < channels.size(); i++) {
                if (channels.get(i).isFree) {
                    channels.get(i).timeOut = getTcurr() + getDelay();
                    channels.get(i).isFree = false;
                    System.out.println(channels.get(i).name + " is busy and will be free in t = " + channels.get(i).timeOut);
                    count++;
                }
                if (count == NumberOfTasks) {
                    break;
                }
            }
            NumberOfTasks = 0;
        } else if (NumberOfTasks > numberOfFreeDevices) {
            for (int i = 0; i < channels.size(); i++) {
                if (channels.get(i).isFree) {
                    channels.get(i).timeOut = getTcurr() + getDelay();
                    channels.get(i).isFree = false;
                    System.out.println(channels.get(i).name + " is busy and will be free in t = " + channels.get(i).timeOut);
                    count++;
                }
            }
            NumberOfTasks -= numberOfFreeDevices;
        }
    }

    @Override
    public void outAct() {
        super.outAct();

        setTnext(Double.MAX_VALUE);
        setState(getState() - 1);
        if (queueLength  >= 0) {
            queueLength++;
            setState(getState() + 1);
            setTnext(getTcurr() + getDelay());
        }


        if (NextProcesses.size() != 0 && getState() != -1)
        {

            if (pobabilities.size() > 1)
            {
                int index = RandomBase.RandomProbability(pobabilities);
                if (index < NextProcesses.size()){
                    ProcessBase nextProcess = NextProcesses.get(index);

                }
                else {
                    ProcessBase nextProcess = NextProcesses.get(0);
                    index = 0;

                }
                ProcessBase nextProcess = NextProcesses.get(index);
                if(nextProcess != null)
                    nextProcess.inAct(1);
               System.out.println("going to "+ getName()+ " to " + nextProcess. getName() + " t = "+ nextProcess.getTnext());
            }
        }


        if (queueLength > 0) {
            queueLength--;
            setState(getState() - 1);
            setTnext(getTcurr() + getDelay());
        }
        if (NextProcesses.size() != 0 && getState() != -1) {
            int index = random.nextInt(NextProcesses.size());
            ProcessBase nextProcess = NextProcesses.get(index);
            if (nextProcess != null)
                nextProcess.inAct(1);
            System.out.println("going to " + getName() + "to " + nextProcess.getName() + "t = " + nextProcess.getTnext());
        }
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("failure = " + failure);
    }

    public void countStatistics(double delta) {
        averageQueue += queueLength * delta;
        averageProcessingTime += delta;
        averageWorkload += delta * getState();
        averageAmount += (delta * (getState() + queueLength));
        if (queueLength > maxQueueObserved) {
            maxQueueObserved = queueLength;
        }
        if (maxWorkload < getState()) {
            maxWorkload = getState();
        }
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public int getMaxQueueLength() {
        return maxQueueLength;
    }

    public void setMaxQueueLength(int maxQueueLength) {
        this.maxQueueLength = maxQueueLength;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public double getProbabilityFailure() {
        return probabilityFailure;
    }

    public void setProbabilityFailure(double probabilityFailure) {
        this.probabilityFailure = probabilityFailure;
    }

    public double getAverageQueue() {
        return averageQueue;
    }

    public void setAverageQueue(double averageQueue) {
        this.averageQueue = averageQueue;
    }

    public int getMaxQueueObserved() {
        return maxQueueObserved;
    }

    public void setMaxQueueObserved(int maxQueueObserved) {
        this.maxQueueObserved = maxQueueObserved;
    }

    public double getAverageQueueTime() {
        return averageQueueTime;
    }

    public void setAverageQueueTime(double averageQueueTime) {
        this.averageQueueTime = averageQueueTime;
    }

    public double getAverageProcessingTime() {
        return averageProcessingTime;
    }

    public void setAverageProcessingTime(double averageProcessingTime) {
        this.averageProcessingTime = averageProcessingTime;
    }

    public double getAverageWorkload() {
        return averageWorkload;
    }

    public void setAverageWorkload(double averageWorkload) {
        this.averageWorkload = averageWorkload;
    }

    public double getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(double averageAmount) {
        this.averageAmount = averageAmount;
    }

    public double getMaxWorkload() {
        return maxWorkload;
    }

    public void setMaxWorkload(double maxWorkload) {
        this.maxWorkload = maxWorkload;
    }

    public int getMaxParallel() {
        return maxParallel;
    }

    public void setMaxParallel(int maxParallel) {
        this.maxParallel = maxParallel;
    }

    public List<ProcessBase> getNextProcesses() {
        return NextProcesses;
    }

    public void setNextProcesses(List<ProcessBase> nextProcesses) {
        NextProcesses = nextProcesses;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public int getNumberOfTasks() {
        return NumberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        NumberOfTasks = numberOfTasks;
    }

    public int getTransferedCount() {
        return TransferedCount;
    }

    public void setTransferedCount(int transferedCount) {
        TransferedCount = transferedCount;
    }

    public ProcessBase getOtherProcess() {
        return OtherProcess;
    }

    public void setOtherProcess(ProcessBase otherProcess) {
        OtherProcess = otherProcess;
    }

    public ArrayList<PobabilityBase> getPobabilities() {
        return pobabilities;
    }

    public void setPobabilities(ArrayList<PobabilityBase> pobabilities) {
        this.pobabilities = pobabilities;
    }
}

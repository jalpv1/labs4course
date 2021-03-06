package com.company.bank_simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Process extends Element {
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
    private List<Process> NextProcesses;

    public Random random = new Random();
    public List<Channel> channels;
    public int NumberOfTasks;
    public int TransferedCount;
    public Process OtherProcess;

    public Process() {
        NextProcesses = new ArrayList<>();
        queueLength = 0;
        averageQueue = 0.0;
        maxQueueObserved = 0;
        averageWorkload = 0;
    }

    public Process(double delay, String name, String distribution, int maxQueueLength, int maxParallel, double devDelay) {
        super(delay, name, distribution, devDelay);
        NextProcesses = new ArrayList<>();
        queueLength = 0;
        this.maxQueueLength = maxQueueLength;
        averageQueue = 0.0;
        maxQueueObserved = 0;
        averageWorkload = 0;
        this.maxParallel = maxParallel;
        channels = new ArrayList<>();
        NumberOfTasks = 1;
        OtherProcess = new Process();
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
        settNext(gettCurrent() + getDelay());

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
                    channels.get(i).timeOut = gettCurrent() + getDelay();
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
                    channels.get(i).timeOut = gettCurrent() + getDelay();
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
        settNext(Double.MAX_VALUE);
        setState(getState() - 1);
        if (queueLength - OtherProcess.queueLength >= 2) {
            queueLength--;
            OtherProcess.queueLength++;
            TransferedCount += 1;
        }
        if (queueLength > 0) {
            queueLength--;
            setState(getState() - 1);
            settNext(gettCurrent() + getDelay());
        }
        if (NextProcesses.size() != 0 && getState() != -1) {
            int index = random.nextInt(NextProcesses.size());
            Process nextProcess = NextProcesses.get(index);
            if (nextProcess != null)
                nextProcess.inAct(1);
            System.out.println("going to " + getName() + "to " + nextProcess.getName() + "t = " + nextProcess.gettNext());
        }
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("failure = " + failure);
    }

    @Override
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

    public List<Process> getNextProcesses() {
        return NextProcesses;
    }

    public void setNextProcesses(List<Process> nextProcesses) {
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

    public Process getOtherProcess() {
        return OtherProcess;
    }

    public void setOtherProcess(Process otherProcess) {
        OtherProcess = otherProcess;
    }
}


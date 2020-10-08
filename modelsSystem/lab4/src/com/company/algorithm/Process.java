package com.company.algorithm;

import com.company.bank_simulation.Channel;
import com.company.bank_simulation.ChannelComparator2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


    public class Process extends com.company.bank_simulation.Element {
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
        private List<com.company.bank_simulation.Process> NextProcesses;

        public Random random = new Random();
        public List<com.company.bank_simulation.Channel> channels;
        public int NumberOfTasks;
        public int TransferedCount;
        public com.company.bank_simulation.Process OtherProcess;

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
            OtherProcess = new com.company.bank_simulation.Process();
            for (int i = 0; i < maxParallel; i++) {
                channels.add(new com.company.bank_simulation.Channel("Name->Channel " + i + 1, 0.0, true));
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

        public List<com.company.bank_simulation.Channel> outChannel(double t) {
            List<com.company.bank_simulation.Channel> outChannels = new ArrayList<>();
            channels.stream().sorted(new ChannelComparator2()).filter(c -> c.getTimeOut() < t && !c.isFree()).forEach(c -> {
                c.setFree(true);
                outChannels.add(c);
            });
//        for (var c : channels) {
//            if (channels.size() != 0) {
//                if (c.timeOut < t && !c.isFree) {
//                    c.isFree = true;
//                    outChannels.add(c);
//                }
//            }
//        }
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
            if (queueLength - OtherProcess.getQueueLength() >= 2) {
                queueLength--;
              // OtherProcess.queueLength++;
                setQueueLength(getQueueLength()+1);
                TransferedCount += 1;
            }
            if (queueLength > 0) {
                queueLength--;
                setState(getState() - 1);
                settNext(gettCurrent() + getDelay());
            }
            if (NextProcesses.size() != 0 && getState() != -1) {
                int index = random.nextInt(NextProcesses.size());
                com.company.bank_simulation.Process nextProcess = NextProcesses.get(index);
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

        public List<com.company.bank_simulation.Process> getNextProcesses() {
            return NextProcesses;
        }

        public void setNextProcesses(List<com.company.bank_simulation.Process> nextProcesses) {
            NextProcesses = nextProcesses;
        }

        public Random getRandom() {
            return random;
        }

        public void setRandom(Random random) {
            this.random = random;
        }

        public List<com.company.bank_simulation.Channel> getChannels() {
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

        public com.company.bank_simulation.Process getOtherProcess() {
            return OtherProcess;
        }

        public void setOtherProcess(com.company.bank_simulation.Process otherProcess) {
            OtherProcess = otherProcess;
        }
//    private int queue, maxqueue, failure;
//    private  int maxParallel;
//
//    private double meanQueue;
//    private double meanLoad;
//    private double probabilityFailure;
//    private int maxQueueInSimulation = 0;
//    private double maxLoad = 0;
//    private Element otherProcess;
//    private double delays= 0;
//    private ArrayList<Element>  nextElements;
//
//    public ArrayList<Element> getNextElements() {
//        return nextElements;
//    }
//
//    public Element getOtherProcess() {
//        return otherProcess;
//    }
//
//    public Process() {
//    }
//
//    public void setOtherProcess(Element otherProcess) {
//        this.otherProcess = otherProcess;
//    }
//
//    public double getDelays() {
//        return delays;
//    }
//
//    public void setDelays(double delays) {
//        this.delays = delays;
//    }
//
//    public void setNextElements(ArrayList<Element> nextElements) {
//        this.nextElements = nextElements;
//    }
//
//    public Process(double delay,double delays, int maxParallel) {
//        super(delay);
//        queue = 0;
//        maxqueue = Integer.MAX_VALUE;
//        meanQueue = 0.0;
//        this.maxParallel = maxParallel;
//        this.delays = delays;
//    }
//    public Process(double delay,double delays,String name, int maxParallel) {
//        super(delay);
//        queue = 0;
//        maxqueue = Integer.MAX_VALUE;
//        meanQueue = 0.0;
//        this.maxParallel = maxParallel;
//        this.delays = delays;
//        this.setName(name);
//    }
//    public Process(double delay,double delays,String name, String distr) {
//        super(delay);
//        queue = 0;
//        maxqueue = Integer.MAX_VALUE;
//        meanQueue = 0.0;
//        setDistribution(distr);
//        this.delays = delays;
//        this.setName(name);
//    }
//    @Override
//    public void inAct(int count) {
//        double delta = maxParallel - getState();
//        if (count < delta && count >0) {
//            setState(count + getState());
//            count = 0;
//        } else {
//            count = (int) (count - delta);
//            setState(maxParallel);
//
//        }
//        super.setTnext(super.getTcurr() + super.getDelay());
//        if (count > 0) {
//            delta = maxqueue - queue;
//            if (delta > count) {
//                queue = queue + count;
//                count = 0;
//            } else {
//                count = count - (int) delta;
//                queue = maxqueue;
//            }
//            if (count > 0) {
//                failure = failure + count;
//            }
//        }
////        if (super.getState() == 0) {
////            super.setState(1);
////            super.setTnext(super.getTcurr() + super.getDelay());
////        } else {
////            if (getQueue() < getMaxqueue()) {
////                setQueue(getQueue() + 1);
////            } else {
////                failure++;
////            }
////        }
//    }
//    @Override
//    public void outAct() {
//        Random rnd = new Random();
//        super.outAct();
//        super.setTnext(Double.MAX_VALUE);
//        setState(getState() - 1);
//        if (getQueue() - otherProcess.getQueue() >=2) {
//            setQueue(getQueue() - 1);
//           otherProcess.setQueue(otherProcess.getQueue()-1);
//           setTransferedCount(getTransferedCount()+1);
//
//            //setState(getState() + 1);
//           // super.setTnext(super.getTcurr() + super.getDelay());
//        }
//        if(getQueue() > 0){
//            setQueue(getQueue() - 1);
//            setState(getState() + 1);
//            super.setTnext(super.getTcurr() + super.getDelay());
//        }
//        if (nextElements != null && nextElements.size() > 0) {
//            nextElements.get(rnd.nextInt(nextElements.size())).inAct(1);
//        }
//    }
////    @Override
////    public void outAct(int c) {
////        Random rnd= new Random();
////        super.outAct(1);
////        super.setTnext(Double.MAX_VALUE);
////        setState(getState() - 1 );
////        if(getQueue() > 0){
////            setQueue(getQueue() - 1);
////            setState(getState() + 1);
////            super.setTnext(super.getTcurr() + super.getDelay());
////        }
////        if(nextElements != null && nextElements.size()>0){
////            nextElements.get(rnd.nextInt(nextElements.size())).inAct(1);
////        }
//
////        super.outAct();
////        super.setTnext(Double.MAX_VALUE);
////        super.setState(0);
////
////        if (getQueue() > 0) {
////            setQueue(getQueue() - 1);
////            super.setState(1);
////            super.setTnext(super.getTcurr() + super.getDelay());
////        }
//
//    public double getMeanLoad() {
//        return meanLoad;
//    }
//
//    public void setMeanLoad(double meanLoad) {
//        this.meanLoad = meanLoad;
//    }
//
//
//    public int getFailure() {
//        return failure;
//    }
//
//    public int getQueue() {
//        return queue;
//    }
//
//
//    public void setQueue(int queue) {
//        this.queue = queue;
//    }
//
//
//    public int getMaxqueue() {
//        return maxqueue;
//    }
//
//    public double getProbabilityFailure() {
//        return probabilityFailure;
//    }
//
//    public void setProbabilityFailure(double probabilityFailure) {
//        this.probabilityFailure = probabilityFailure;
//    }
//
//    public int getMaxQueueInSimulation() {
//        return maxQueueInSimulation;
//    }
//
//    public void setMeanQueue(double meanQueue) {
//        this.meanQueue = meanQueue;
//    }
//
//    public void setMaxQueueInSimulation(int maxQueueInSimulation) {
//        this.maxQueueInSimulation = maxQueueInSimulation;
//    }
//
//    public void setMaxqueue(int maxqueue) {
//        this.maxqueue = maxqueue;
//    }
//
//    @Override
//    public void printInfo() {
//        super.printInfo();
//        System.out.println("failure = " + this.getFailure());
//    }
//
//    @Override
//    public void doStatistics(double delta) {
//        meanQueue = getMeanQueue() + queue * delta;
//        meanLoad = meanLoad + (getState() * delta);
//        if (maxQueueInSimulation < queue) {
//            maxQueueInSimulation = queue;
//        }
//        if(maxLoad < meanLoad){
//            maxLoad = meanLoad;
//        }
//
//
//    }
//
//    public double getMaxLoad() {
//        return maxLoad;
//    }
//
//    public void setMaxLoad(double maxLoad) {
//        this.maxLoad = maxLoad;
//    }
//
//    public void doResultsStatistics() {
////        if (failure != 0 && getQuantity() != 0) {
////
////            probabilityFailure = (double) failure / getQuantity();
////            meanQueue = meanQueue / getTcurr();
////        }
//    }
//
//    public int getMaxParallel() {
//        return maxParallel;
//    }
//
//    public void setMaxParallel(int maxParallel) {
//        this.maxParallel = maxParallel;
//    }
//
//    public double getMeanQueue() {
//        return meanQueue;
//    }
}

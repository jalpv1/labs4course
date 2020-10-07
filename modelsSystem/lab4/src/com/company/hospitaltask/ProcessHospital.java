package com.company.hospitaltask;

import com.company.algorithm.Channel;
import com.company.algorithm.Process;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessHospital extends Process {
    private List<Integer> queues;
    private List<Integer> states;
    private int maxQueueLength;
    private int failure;
    private double probabilityFailure;
    private double averageQueue;
    private int maxQueueObserved;
    private int averageQueueTime;
    private double averageProcessTime;
    public double averageWorkload ;
    public double maxWorkload ;
    public int maxParallel ;
    public List<ProcessHospital> nextProcesses ;
    public double waitingTime ;
    public double inWaiting;
    public double delaySum ;
    public double startDelay ;

    public Random random = new Random();
   public List<Channel> channels ;
    public int numberOfTasks ;

    public List<PatientType> types ;
    public int patientType ;
    public ProcessHospital()
    {
        super();
        nextProcesses = new ArrayList<>();
        averageQueue = 0;
        maxQueueObserved = 0;
        averageWorkload = 0;
        states = new ArrayList<>();
       queues = new ArrayList<>() ;
    }
    public ProcessHospital(double delay, double devDelay, String name,
                           String distribution, int maxParallel) {
        super(delay,devDelay,name,distribution);
        nextProcesses = new ArrayList<>();
        maxQueueLength = 10000;
        averageQueue = 0;
        maxQueueObserved = 0;
        averageWorkload = 0;
        this.maxParallel = maxParallel;
        //   this.devDelay = devDelay;
        states = new ArrayList<>();
        queues = new ArrayList<>();
        numberOfTasks = 1;
        channels = new ArrayList<>();
        ;
        for (int i = 0; i < maxParallel; i++) {
              channels.add(new Channel(true, 0.0, "{Name}->Channel{i + 1}"));
        }
        types = new ArrayList<>(Arrays.asList(
                new PatientType(1, 0.5, 1.0 / 15.0),
                new PatientType(2, 0.1, 1.0 / 40.0),
                new PatientType(3, 0.4, 1.0 / 30.0)));
    }
//    @Override
//    public  void InAct(int patientType)
//    {
//        if (states.size() < maxParallel)
//        {
//            states.add(patientType);
//        }
//        else if (queues.size() < maxQueueLength)
//        {
//            queues.add(patientType);
//        }
//       setTnext( getTcurr() + getDelay())  ;
//    }
//    @Override
//    public  void OutAct()
//    {
//        setQuantity(getQuantity()+1);
//        setTnext(Double.MAX_VALUE);
//        int patientType = 0;
//        if (states.size() > 0)
//        {
//            patientType = states.get(0);
//            states.remove(0);
//        }
//
//        if (queues.size() > 0)
//        {
//            int patientTypeQueue = queues.get(0);
//            queues.remove(0);
//            states.add(patientTypeQueue);
//        }
//        this.patientType = patientType;
//        if(patientType != 0)
//            types.get(patientType - 1).setQuantity(getQuantity()+1);
//        if (nextProcesses.size() != 0 && patientType != 0)
//        {
//            int index = random.nextInt( nextProcesses.size());
//            ProcessHospital nextProcess = nextProcesses.get(index);
//            nextProcess.InAct(patientType);
//            setTnext( getTcurr() + getDelay())  ;
//           // Console.WriteLine($"IN FUTURE from {Name} to {nextProcess.Name} t = {nextProcess.TNext}");
//        }
//    }

    @Override
    public void inAct(int count) {
     //  super.inAct(count);
        if (states.size() < maxParallel)
        {
            states.add(patientType);
        }
        else if (queues.size() < maxQueueLength)
        {
            queues.add(patientType);
        }
        setTnext( getTcurr() + getDelay())  ;
    }

    @Override
    public void outAct() {
     //  super.outAct();

        setQuantity(getQuantity()+1);
        setTnext(Double.MAX_VALUE);
        int patientType = 0;
        if (states.size() > 0)
        {
            patientType = states.get(0);
            states.remove(0);
        }

        if (queues.size() > 0)
        {
            int patientTypeQueue = queues.get(0);
            queues.remove(0);
            states.add(patientTypeQueue);
        }
        this.patientType = patientType;
        if(patientType != 0)
            types.get(patientType - 1).setQuantity(getQuantity()+1);
        if (nextProcesses.size() != 0 && patientType != 0)
        {
            int index = random.nextInt( nextProcesses.size());
            ProcessHospital nextProcess = nextProcesses.get(index);
            nextProcess.inAct(patientType);
            setTnext( getTcurr() + getDelay())  ;
            System.out.println("IN FUTURE from " + getName()+ "to" +  nextProcess.getName() + "t = " + nextProcess.getTnext());
        }
    }

    public List<Channel> OutChannel(double t)
    {
        List<Channel> outChannels = new ArrayList<>();
        channels = channels.stream().sorted(Comparator.comparingDouble(Channel::getTimeOut))
                .collect(Collectors.toList());
        for (Channel c : channels)
        {
            if (channels.size() != 0)
            {
                if (c.getTimeOut() < t && !c.isFree())
                {
                    c.setFree(true);
                    outChannels.add(c);
                }
            }
        }
        return outChannels;
    }
    public void InChannel()
    {
        int count = 0;
        int numberOfFreeDevices = maxParallel - getState();
        if (numberOfTasks <= numberOfFreeDevices && numberOfTasks > 0)
        {

            for (int i = 0; i < channels.size(); i++)
            {
                if (channels.get(i).isFree())
                {
                    channels.get(i).setTimeOut( getTcurr() + getDelay());
                    channels.get(i).setFree(false);
//                    Console.WriteLine();
//                    Console.WriteLine($"{Channels[i].Name} is busy and will be free in t = {Channels[i].TimeOut}");
//                    Console.WriteLine();
                    System.out.println("Channels" + channels.get(i).getName()+ "is busy and will be free in t =" +  channels.get(i).getTimeOut());

                    count++;
                }
                if (count == numberOfTasks)
                {
                    break;
                }
            }
            numberOfTasks = 0;
        }
        else if (numberOfTasks > numberOfFreeDevices)
        {
            for (int i = 0; i < channels.size(); i++)
            {
                if (channels.get(i).isFree())
                {
                    channels.get(i).setTimeOut( getTcurr() + getDelay());
                    channels.get(i).setFree(false);
//                    Console.WriteLine();
//                    Console.WriteLine($"{Channels[i].Name} is busy and will be free in t = {Channels[i].TimeOut}");
                    System.out.println("Channels" + channels.get(i).getName()+ "is busy and will be free in t =" +  channels.get(i).getTimeOut());

//                    Console.WriteLine();
                    count++;
                }
            }
            numberOfTasks -= numberOfFreeDevices;
        }
    }
    public  void PrintInfo()
    {
        super.printInfo();
       // Console.WriteLine($"failure = {Failure}");
    }
    public  void CountStatistics(double delta)
    {
        averageQueue += (queues.size() * delta);
        averageProcessTime += delta;
        averageWorkload += delta * states.size();
        if(patientType != 0)
            types.get(patientType - 1).setWaitingTime( ((queues.size() + states.size()) * delta)+ types.get(patientType - 1).getWaitingTime());;
        waitingTime += (queues.size() + states.size()) * delta;
        delaySum += delta;
        inWaiting += queues.size() + states.size();
        if (queues.size() > maxQueueObserved)
        {
            maxQueueObserved = queues.size();
        }
        if (maxWorkload < states.size())
        {
            maxWorkload = states.size();
        }
    }

    public List<Integer> getStates() {
        return states;
    }

    public void setStates(List<Integer> states) {
        this.states = states;
    }

    public List<Integer> getQueues() {
        return queues;
    }

    public void setQueues(List<Integer> queues) {
        this.queues = queues;
    }

    public int getMaxQueueLength() {
        return maxQueueLength;
    }

    public void setMaxQueueLength(int maxQueueLength) {
        this.maxQueueLength = maxQueueLength;
    }

    @Override
    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    @Override
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

    public int getAverageQueueTime() {
        return averageQueueTime;
    }

    public void setAverageQueueTime(int averageQueueTime) {
        this.averageQueueTime = averageQueueTime;
    }

    public double getAverageProcessTime() {
        return averageProcessTime;
    }

    public void setAverageProcessTime(double averageProcessTime) {
        this.averageProcessTime = averageProcessTime;
    }

    public double getAverageWorkload() {
        return averageWorkload;
    }

    public void setAverageWorkload(double averageWorkload) {
        this.averageWorkload = averageWorkload;
    }

    public double getMaxWorkload() {
        return maxWorkload;
    }

    public void setMaxWorkload(double maxWorkload) {
        this.maxWorkload = maxWorkload;
    }

    @Override
    public int getMaxParallel() {
        return maxParallel;
    }

    @Override
    public void setMaxParallel(int maxParallel) {
        this.maxParallel = maxParallel;
    }

    public List<ProcessHospital> getNextProcesses() {
        return nextProcesses;
    }

    public void setNextProcesses(List<ProcessHospital> nextProcesses) {
        this.nextProcesses = nextProcesses;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getInWaiting() {
        return inWaiting;
    }

    public void setInWaiting(double inWaiting) {
        this.inWaiting = inWaiting;
    }

    public double getDelaySum() {
        return delaySum;
    }

    public void setDelaySum(double delaySum) {
        this.delaySum = delaySum;
    }

    public double getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(double startDelay) {
        this.startDelay = startDelay;
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
        return numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public List<PatientType> getTypes() {
        return types;
    }

    public void setTypes(List<PatientType> types) {
        this.types = types;
    }

    public int getPatientType() {
        return patientType;
    }

    public void setPatientType(int patientType) {
        this.patientType = patientType;
    }
}



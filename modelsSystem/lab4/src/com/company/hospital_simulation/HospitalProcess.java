package com.company.hospital_simulation;


import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HospitalProcess extends Element {
    public List<Integer> queue;
    public List<Integer> states;
    public int maxQueueLength;
    public int failure;
    public double probabilityFailure;
    public double averageQueue;
    public int maxQueueObserved;
    public double averageQueueTime;
    public double averageProcessingTime;
    public double averageWorkload;
    public double maxWorkload;
    public int maxParallel;
    public List<HospitalProcess> nextProcesses;
    public double waitingTime;
    public double inWaiting;
    public double delaySum;
    public double startDelay;

    public List<Channel> channels;
    public int numberOfTasks;

    public List<PatientType> types;
    public int patientType;
    public HospitalProcess()
    {
        nextProcesses = new ArrayList<>();
        averageQueue = 0.0;
        maxQueueObserved = 0;
        averageWorkload = 0;
        states = new ArrayList<>();
        queue = new ArrayList<>();
    }
    public HospitalProcess(double delay, double devDelay, String name, String distribution, int maxParallel)
    {
        super(delay, name, distribution, devDelay);
        nextProcesses = new ArrayList<>();
        maxQueueLength = 10000;
        averageQueue = 0.0;
        maxQueueObserved = 0;
        averageWorkload = 0;
        this.maxParallel = maxParallel;
        this.devDelay = devDelay;
        states = new ArrayList<>();
        queue = new ArrayList<>();
        numberOfTasks = 1;
        channels = new ArrayList<>();
        for (int i = 0; i < maxParallel; i++)
        {
            channels.add(new Channel(name+"->Channel" + (i+1), 0.0, true));
        }
        types = Arrays.asList(
                new PatientType(1, 0.5, ((double)1)/15),
                new PatientType(2, 0.1, ((double)1)/40),
                new PatientType(3, 0.4, ((double)1)/30)
        );
    }

    public List<Integer> getStates() {
        return states;
    }

    public void setStates(List<Integer> states) {
        this.states = states;
    }

    public List<HospitalProcess> getNextProcesses() {
        return nextProcesses;
    }

    public void setNextProcesses(List<HospitalProcess> nextProcesses) {
        this.nextProcesses = nextProcesses;
    }

    public  void inAct(int patientType)
    {
        if (states.size() < maxParallel)
        {
            states.add(patientType);
        }
        else if (queue.size() < maxQueueLength)
        {
            queue.add(patientType);
        }
        settNext(gettCurrent() + getDelay());
    }
    public  void outAct()
    {
        setQuantity(getQuantity()+1);
        settNext(Double.MAX_VALUE);
        int patientType = 0;
        if (states.size() > 0)
        {
            patientType = states.get(0);
            states.remove(0);
        }

        if (queue.size() > 0)
        {
            int patientTypeQueue = queue.get(0);
            queue.remove(0);
            states.add(patientTypeQueue);
        }
        this.patientType = patientType;
        if(patientType != 0)
            types.get(patientType - 1).incrementQuanity();
        if (nextProcesses.size() != 0 && patientType != 0)
        {
            int index = (int)(Math.random()* nextProcesses.size());
            HospitalProcess nextProcess = nextProcesses.get(index);
            nextProcess.inAct(patientType);
            settNext(gettCurrent() + getDelay());
            System.out.println("going to "+getName()+" to" + nextProcess.getName() + " t = " + nextProcess.gettNext());
        }
    }
    public List<Channel> outChannel(double t)
    {
        List<Channel> outChannels =  new ArrayList<>();
        channels.stream().sorted(new ChannelComparator()).filter(c->c.getTimeOut() < t && !c.isFree()).forEach(c -> { c.setFree( true);
            outChannels.add(c);});
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
                    channels.get(i).setTimeOut(gettCurrent() + getDelay());
                    channels.get(i).setFree(false);

                    System.out.println(channels.get(i).getName()  + " is busy and will be free in t =" + channels.get(i).getTimeOut() + "\n");
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
                    channels.get(i).setTimeOut(gettCurrent() + getDelay());
                    channels.get(i).setFree(false);
                    System.out.println(channels.get(i).getName()  + " is busy and will be free in t =" + channels.get(i).getTimeOut() + "\n");
                    count++;
                }
            }
            numberOfTasks -= numberOfFreeDevices;
        }
    }
    public  void printInfo()
    {
        super.printInfo();
        System.out.println("failure =" + failure);
    }
    public  void CountStatistics(double delta)
    {
        averageQueue += (queue.size() * delta);
        averageProcessingTime += delta;
        averageWorkload += delta * states.size();
        if(patientType != 0)
            types.get(patientType - 1).setWaitingTime(types.get(patientType - 1).getWaitingTime()+ (queue.size() + states.size()) * delta);
        waitingTime += (queue.size() + states.size()) * delta;
        delaySum += delta;
        inWaiting += queue.size() + states.size();
        if (queue.size() > maxQueueObserved)
        {
            maxQueueObserved = queue.size();
        }
        if (maxWorkload < states.size())
        {
            maxWorkload = states.size();
        }
    }
}

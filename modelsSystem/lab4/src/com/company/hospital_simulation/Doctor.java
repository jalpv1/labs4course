package com.company.hospital_simulation;


import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doctor extends HospitalProcess{
    public int toLabAmount;
    public List<PatientType> dTypes;
    public int dPatientType;
    public Doctor(double delay, double startDelay, String name, String distribution, int maxParallel)
    {
        super(delay, startDelay, name, distribution, maxParallel);
        toLabAmount = 0;
        dTypes = new ArrayList<>();
        dTypes = Arrays.asList(
                new PatientType(1, 0.5, ((double)1)/15),
                new PatientType(2, 0.1, ((double)1)/40),
                new PatientType(3, 0.4, ((double)1)/30));

    }

    public List<PatientType> getdTypes() {
        return dTypes;
    }

    public void setdTypes(List<PatientType> dTypes) {
        this.dTypes = dTypes;
    }

    public  void outAct()
    {
        incrementQuantity();
        settNext(Double.MAX_VALUE);
        int patientType = 0;
        if (states.size() > 0)
        {
            for (int i = 0; i < states.size(); i++)
            {
                //1 type
                if (states.get(i) == 1)
                {
                    patientType = states.get(i);
                    states.remove(i);
                    break;
                }
            }
            //2 3 type

            if (patientType == 0)
            {
                patientType = states.get(0);
                states.remove(0);
            }
        }
        if (queue.size() != 0)
        {
            patientType = queue.get(0);
            queue.remove(0);
            states.add(patientType);
        }

        dPatientType = patientType;
        if (patientType != 0)
            dTypes.get(patientType - 1).incrementQuanity();
        if (patientType != 0)
        {
            HospitalProcess nextProcess;
            if (patientType == 1)
            {
                nextProcess = nextProcesses.get(0);
            }
            else
            {
                nextProcess = nextProcesses.get(1);
                toLabAmount += 1;
            }
            nextProcess.inAct(patientType);
            settNext(gettNext()+ getDelay());
            System.out.println("going to" + getName() + " to " + nextProcess.getName() + " t = " + nextProcess.gettNext());
        }
    }

    public void DoStatistics(double delta, int state)
    {
        averageQueue += queue.size() * delta;
        averageProcessingTime += delta;
        averageWorkload += delta * states.size();
        if (dPatientType != 0)
            types.get(dPatientType - 1).setWaitingTime( types.get(dPatientType - 1).getWaitingTime() + (queue.size() + states.size()) * delta);
        waitingTime += (queue.size() + states.size()) * delta;

        inWaiting += queue.size() + states.size();
        if (queue.size() > maxQueueObserved)
        {
            maxQueueObserved = queue.size();
        }
        if (maxWorkload < states.size())
        {
            maxWorkload = states.size();
        }
        if (state == 2 || state == 3)
        {
            delaySum += delta;
        }
    }}

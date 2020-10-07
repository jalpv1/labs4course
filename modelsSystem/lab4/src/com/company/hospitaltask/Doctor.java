package com.company.hospitaltask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doctor extends ProcessHospital {
    public int labAmount;
    private List<PatientType> dTypes;
    private int dPatientType;

    public Doctor(double delay, double startDelay, String name, String distribution, int maxParallel) {
        super(delay, startDelay, name, distribution, maxParallel);
        labAmount = 0;
        dTypes = new ArrayList<>(Arrays.asList(
                new PatientType(1, 0.5, 1.0 / 15.0),
                new PatientType(2, 0.1, 1.0 / 40.0),
                new PatientType(3, 0.4, 1.0 / 30.0)));
        ;
    }

    @Override
    public void outAct() {
        //super.outAct();

        setQuantity(getQuantity() + 1);
        setTnext(Double.MAX_VALUE);
        int patientType = 0;
        if (getStates().size() > 0) {
            for (int i = 0; i < getStates().size(); i++) {
                if (getStates().get(i) == 1) {
                    patientType = getStates().get(i);
                    getStates().remove(i);
                    break;
                }
            }
            if (patientType == 0) {
                patientType = getStates().get(0);
                getStates().remove(0);
            }
        }
        if (getQueues().size() != 0) {
            patientType = getQueues().get(0);
            getQueues().remove(0);
            getStates().add(patientType);
        }

        dPatientType = patientType;
        if (patientType != 0)
            dTypes.get(patientType - 1).setQuantity(getQuantity() + 1);
        if (patientType != 0) {
            ProcessHospital nextProcess;
            if (patientType == 1) {
                nextProcess = nextProcesses.get(0);
            } else {
                nextProcess = nextProcesses.get(1);
                labAmount += 1;
            }
            nextProcess.inAct(patientType);
            setTnext(getTcurr() + getDelay());
             System.out.println("IN FUTURE from " + getName()+ "to" +  nextProcess.getName() + "t = " + nextProcess.getTnext());
        }
    }

    public void DoStatistics(double delta, int State) {
        setAverageQueue(getAverageQueue() + getQueues().size() * delta);
        ;
        setAverageProcessTime((getAverageProcessTime() + delta));
        setAverageWorkload(getAverageWorkload() + delta * getStates().size());
        if (dPatientType != 0) {
            types.get(dPatientType - 1).setWaitingTime(getWaitingTime() + (getQueues().size() + getStates().size()) * delta);
        }
        setWaitingTime(getWaitingTime()+ (getQueues().size() + getStates().size()) * delta);

        inWaiting += getQueues().size() + getStates().size();
        if (getQueues().size() > getMaxQueueObserved()) {
            setMaxQueueObserved(getQueues().size()) ;
        }
        if (maxWorkload < getStates().size()) {
            maxWorkload = getStates().size() ;
        }
        if (getState() != 0 && (getState() == 2 || getState() == 3)) {
            delaySum += delta;
        }
    }
}


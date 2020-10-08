package com.company.hospital_simulation;

public class Laboratory extends HospitalProcess {
    public Laboratory(double delay, double startDelay, String name, String distribution, int maxParallel) {
        super(delay, startDelay, name, distribution, maxParallel);
    }

    public void outAct() {
        incrementQuantity();
        settNext(Double.MAX_VALUE);
        int patientType = 0;
        if (getStates().size() != 0) {
            patientType = getStates().get(0);
            states.remove(0);
        }
        if (queue.size() != 0) {
            patientType = queue.get(0);
            queue.remove(0);
            states.add(patientType);
        }
        if (patientType != 0) {
            HospitalProcess nextProcess;
            if (patientType == 2) {
                nextProcess = getNextProcesses().get(0);
                nextProcess.inAct(patientType);
                System.out.println("going to" + getName() + " to " + nextProcess.getName() + " t = " + nextProcess.gettNext());
            } else if (patientType == 3) {
                nextProcess = getNextProcesses().get(1);
                nextProcess.inAct(patientType);
                System.out.println("going to " + getName() + " to " + nextProcess.getName() + " t = " + nextProcess.gettNext());
            }
            settNext(gettCurrent() + getDelay());
        }
    }

}


package com.company.hospitaltask;

public class Laboratory extends ProcessHospital {

    public Laboratory(double delay, double startDelay, String name, String distribution, int maxParallel)
    {
        super(delay, startDelay, name, distribution, maxParallel);
    }
    @Override
    public void outAct() {
        //super.outAct();
        setQuantity(getQuantity() + 1);
        setTnext(Double.MAX_VALUE);
        int patientType = 0;
        if (getStates().size() != 0) {
            patientType = getStates().get(0);
            getStates().remove(0);
        }
        if (getQueues().size() != 0) {
            patientType = getQueues().get(0);
            getQueues().remove(0);
            getStates().add(patientType);
        }
        if (patientType != 0) {
            ProcessHospital nextProcess;
            if (patientType == 2) {
                nextProcess = nextProcesses.get(0);
                nextProcess.inAct(patientType);
                // Console.WriteLine($"IN FUTURE from {Name} to {nextProcess.Name} t = {nextProcess.TNext}");
                System.out.println("IN FUTURE from " + getName()+ "to" +  nextProcess.getName() + "t = " + nextProcess.getTnext());

            } else if (patientType == 3) {
                nextProcess = nextProcesses.get(1);
                nextProcess.inAct(patientType);
                //Console.WriteLine($"IN FUTURE from {Name} to {nextProcess.Name} t = {nextProcess.TNext}");
                System.out.println("IN FUTURE from " + getName()+ "to" +  nextProcess.getName() + "t = " + nextProcess.getTnext());

            }
            setTnext(getTcurr() + getDelay());
        }
    }


}

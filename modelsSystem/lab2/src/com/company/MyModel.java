package com.company;

public class MyModel {
    private double next;
    private double current;
    private double t0;
    private double t1;
    private double timeToCreate;
    private double timeToProcess;
    private double amountOfCreated;
    private double amountOfProcessed;
    private double amountOfFailed;

    private int stateNumber;
    private int maxQueue;
    private int queue;

    private int nextEvent;
    private double averageWait;
    private double averageProcess;
    private double tGenerateCur;
    public double T;
    public double N;
    public double Q;
    private double averageQueuelength;
    private double imovOtkaza;


    public MyModel(double timeToCreate, double timeToProcess, int maxQueue) {
        this.timeToCreate = timeToCreate;
        this.timeToProcess = timeToProcess;
        this.maxQueue = maxQueue;
    }

    public void simulate(double timeModeling) {
        double tAvarageI = 0;
        double deltaTi = 0;
        double QI = 0;
        double timeCurrentPref = current;
        while (current < timeModeling) {
            timeCurrentPref = current;
            next = t0;
            nextEvent = 0;

            if (t1 < next) {
                next = t1;
                nextEvent = 1;
            }
            current = next;
            if(nextEvent == 1){
                doEvent1();

            }
            if(nextEvent == 0){
                doEvent0();

            }
            tAvarageI = tAvarageI + (queue * (current - timeCurrentPref));
            deltaTi = deltaTi + (current - timeCurrentPref);
            QI = QI + (current - timeCurrentPref) * queue;
          ;

        }
        //-------Time------
        averageWait = tAvarageI / (amountOfProcessed);
        //-------Payload------
        Q = QI / amountOfProcessed;
        double lambd = amountOfProcessed / timeModeling;
        T = deltaTi / amountOfProcessed;
        double ro = lambd * T;
        double Nw = Q * lambd;
        double Ns1 = (ro - Math.pow(ro, amountOfProcessed + 1));
        double Ns2 = (1 - Math.pow(ro, amountOfProcessed + 1));

        double Ns = Ns1 / Ns2;
        N = Ns;
        //---- P otkaza
        double Potk = amountOfFailed/amountOfCreated;
        imovOtkaza = Potk;
        //---L ququ
        double L = tAvarageI/timeModeling;
        averageQueuelength = L;
        printStatistic();
    }

    private void doEvent0() {
        //delay
        t0 = current + TimeGenerator.createTime();
        amountOfCreated++;
        if (stateNumber == 0) {
            stateNumber = 1;
            //process
            t1 = current + TimeGenerator.endTime();
        } else {
            if (queue < maxQueue)
                queue++;
            else
                amountOfFailed++;
        }
    }

    private void doEvent1() {
        t1 = Double.MAX_VALUE;
        stateNumber = 0;
        if (queue > 0) {
            queue--;
            stateNumber = 1;
            t1 = current + TimeGenerator.endTime();
        }
        amountOfProcessed++;

    }

    public void printStatistic() {
        System.out.println("Input  "+" timeToCreate= " + timeToCreate + " timeToProcess = " + timeToProcess + " queue = "
                + maxQueue);
        System.out.println("Output  "+" amountOfCreated= " + amountOfCreated + " amountOfProcessed = " + amountOfProcessed + " amountOfFailed = "
                + amountOfFailed + " averageWait " + averageWait + " average payload " + N);
    }

    public void printInfo() {
        System.out.println(" current = " + current + " stateNumber = " + stateNumber + " queue = " + queue);
    }

}

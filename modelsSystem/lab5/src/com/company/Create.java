package com.company;

public class Create extends Element {


    public Create(double delay, String name, String distribution, double devDelay)
    {
        super(name,delay, devDelay, distribution);
    }
    @Override
    public  void outAct()
    {
        super.outAct();
        setTnext(getTcurr()+ getDelay());;
        getNextElement().inAct(1);
    }



}

package com.company.base_simulation;

public class CreateBase extends ElementBase {


        public CreateBase(double delay, String name, String distribution, double devDelay)
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

package com.company;

public class Create extends Element {

    public Create(double delay) {
        super(delay);
    }

    @Override
    public void outAct(int c) {
        super.outAct(1);
        super.setTnext(super.getTcurr() + super.getDelay());
        super.getNextElement().inAct(1);
    }
}

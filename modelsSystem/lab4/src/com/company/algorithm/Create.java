package com.company.algorithm;


import java.util.ArrayList;

public class Create extends Element {

    public Create(double delay) {
        super(delay);
    }

    @Override
    public void outAct(int c) {
        super.outAct(1);
        super.setTnext(super.getTcurr() + super.getDelay());
        getNextElement().inAct(1);
    }
    private int queuesCheck(ArrayList<Element> elements){

        for (Element e: elements){

        }
    }
    @Override
    public Element getNextElement() {
        ArrayList<Element> queues = getNextElements();
        if(getQueue() == 0){
           return getNextElements().get(0);
        }
        else {
          return   getNextElements().stream().min((el1,el2) -> {
                if (el1.getQueue()<el2.getQueue()){
                    return 0;
                }
                else {
                    return 0;
                }
            }).get();
        }
    }

}

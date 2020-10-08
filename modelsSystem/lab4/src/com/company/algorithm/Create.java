package com.company.algorithm;


import java.util.ArrayList;
import java.util.List;

public class Create extends Element {
    private ArrayList<Element> nextElements;

    public Create(double delay) {
        super(delay);
    }
    public Create(double delay, String name, String distribution, double devDelay)
    {
        super(name,delay , devDelay,distribution);
    }
    @Override
    public void outAct(int c) {
        super.outAct(1);
        super.setTnext(super.getTcurr() + super.getDelay());
        getNextElement().inAct(1);
    }
    private int queuesCheck(List<Element> elements){
        int queue = elements.get(0).getQueue();
        for (Element e: elements){
         if(e.getQueue() != queue){
             return 0;
         }

        }
        return 1;
    }
    private int queuesCheckEmpty(List<Element> elements){
        int sum = 0;
        for (Element e: elements){
            sum = sum+ e.getQueue();

        }
        return sum;
    }
    private Element getMinEl(List <Element> els){
        Element elementMin = els.get(0);
        for (Element e: els){
            if(e.getQueue()<elementMin.getQueue()){
                elementMin =e;
            }
        }
        return elementMin;
    }
    @Override
    public Element getNextElement() {
        List<Element> queues = getNextElements();
        if(queuesCheckEmpty(queues) == 0 || queuesCheck(queues) == 1){
           return getNextElements().get(0);
        }
        else {
          return  getMinEl(queues);
        }
    }

}

package com.company.bank_simulation;

import java.util.ArrayList;
import java.util.List;

public class Create extends Element {
    private List<Process> nextElements;

    public Create(double delay, String name, String distribution, double devDelay) {
        super(delay, name, distribution, devDelay);
        //nextElements = new ArrayList<Process>();
    }

    @Override
    public void outAct() {
        super.outAct();
        //  TNext = TCurrent + GetDelay();
        settNext(gettCurrent() + getDelay());
       // getNextElements().get(0).inAct(1);
        getNextElement().inAct(1);
    }
    public void setNextElement(List<Process> nextElements) {
        this.nextElements = nextElements;
    }

    public Process getNextElement() {
        ArrayList<Integer> queue = new ArrayList<>();
       // nextElements =  getNextElements());
        List<Element> next = getNextElements();
        //nextElements = (List<Process>) next.stream().filter(x -> x instanceof Process);
        for (var e : nextElements) {
            //if(e instanceof Process) {
                queue.add( ((Process) e).getQueueLength());
          //  }
        }
        int count = 0;
        int minIndex = 0;
        int min = 10000;
        for (int i = 0; i < queue.size() - 1; i++) {
            if (queue.get(i).equals(queue.get(0))) {
                count++;
            }
        }
        for (int i = 1; i < queue.size() - 1; i++) {
            if (queue.get(i) < min) {
                minIndex = 0;
            }
        }
        if (queue.stream().mapToInt(x -> x).sum() == 0 || count == queue.size()) {
            return nextElements.get(0);
        } else {
            return nextElements.get(minIndex);
        }

    }
}

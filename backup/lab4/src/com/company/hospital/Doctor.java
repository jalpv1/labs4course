package com.company.hospital;

import com.company.algorithm.Element;
import com.company.algorithm.Process;

import java.util.List;

public class Doctor extends ProcessH {
    private int labAmount;
    private int quantity;


    @Override
    public void outAct(int c) {
        // super.outAct(c);
        quantity++;
        setTnext(Double.MAX_VALUE);
        int p_type = -3;
        if (getState2().size() > 0) {
            for (int i = 0; i < getState2().size(); i++) {
                if (getState2().get(i) == 1) {
                    p_type = getState2().get(i);
                    getState2().remove(i);
                    break;
                }
            }
            if (p_type == -3) {
                p_type = getState2().get(0);
            }
        }
        ElementH e;
        if (getQueue2().size() > 0) {
            int p_type_q = getQueue2().get(0);
            getQueue2().remove(0);
            getState2().add(p_type_q);
            if(p_type == 1){
                 e = getNextElements().get(0);
            }
            else {
                e = getNextElements().get(1);
                 labAmount++;
            }
        }
        setTnext(super.getTcurr() + super.getDelay());

    }

    @Override
    public void doStatistics(double delta) {
        super.doStatistics(delta);
    }
}

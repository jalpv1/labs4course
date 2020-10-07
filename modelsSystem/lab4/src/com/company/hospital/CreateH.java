package com.company.hospital;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CreateH extends ElementH {
    private Map<Integer, Integer> delays;

    public CreateH(double delay) {
        super(delay);
        this.delays = new HashMap<>();
        delays.put(1, 1 / 15);
        delays.put(2, 1 / 40);
        delays.put(3, 1 / 10);

    }

    @Override
    public void outAct(int count) {
        super.outAct(count);
        Random rnd = new Random();
        int patientType = rnd.nextInt(3);
        super.setTnext(super.getTcurr() + super.getDelay());
        getNextElement().setDelayMean(delays.get(patientType));
        getNextElement().inAct(patientType);

    }
}

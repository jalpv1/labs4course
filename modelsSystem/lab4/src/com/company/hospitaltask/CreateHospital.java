package com.company.hospitaltask;

import com.company.algorithm.Element;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateHospital extends Element {
    Random random = new Random();
    public List<PatientType> patientsTypes;
    public PatientType patientType;

    public CreateHospital(double delay, String name, String distribution, double devDelay) {
         super(name, delay, devDelay ,distribution);
        patientsTypes = new ArrayList<>();
        patientType = new PatientType(0, 0, 0);
    }

    @Override
    public void outAct() {
        super.outAct();

        int index = 0;
        if (patientsTypes.size() > 0) {
            index = patientType.ChooseProbability(patientsTypes) - 1;
            for (PatientType t : patientsTypes) {
                if (index + 1 == t.getIndex()) {
                    t.setQuantity(getQuantity() + 1);
                }
            }
        }

        setTnext(getTcurr() + getDelay());

        getNextElement().setDelayMean(patientsTypes.get(index).getAverageTimeOfReg());
        getNextElement().inAct(patientsTypes.get(index).getIndex());
    }
}


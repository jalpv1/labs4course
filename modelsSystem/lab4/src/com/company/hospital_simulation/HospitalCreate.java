package com.company.hospital_simulation;


import java.util.ArrayList;
import java.util.List;

public class HospitalCreate extends Element {
    public List<PatientType> patientsTypes;
    public PatientType patientType;

    public HospitalCreate(double delay, String name, String distribution, double devDelay) {
        super(delay, name, distribution, devDelay);
        patientsTypes = new ArrayList<>();
        patientType = new PatientType(0, 0, 0);
    }

    public void outAct() {
        super.outAct();
        int index = 0;
        if (patientsTypes.size() > 0) {
            index = patientType.ChooseProbability(patientsTypes) - 1;
            for (PatientType t : patientsTypes) {

                if (index + 1 == t.index) {
                    t.incrementQuanity();
                }
            }
        }

        settNext(gettCurrent() + getDelay());

        getNextElement().setAverageDelay(patientsTypes.get(index).getAvgTimeOfRegistration());
        getNextElement().inAct(patientsTypes.get(index).index);
    }
}


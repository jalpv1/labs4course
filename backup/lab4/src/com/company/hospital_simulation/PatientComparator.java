package com.company.hospital_simulation;

import java.util.Comparator;

public class PatientComparator implements Comparator<PatientType> {

    public int compare(PatientType a, PatientType b) {
        if (b.getFrequency() < a.getFrequency()) return -1;
        if (b.getFrequency() > a.getFrequency()) return 1;
        return 0;
    }
}


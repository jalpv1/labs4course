package com.company;
////                         HOSPITAL
//
//import com.company.algorithm.Create;
//import com.company.hospital_simulation.Element;
//import com.company.algorithm.Model;
//import com.company.algorithm.Process;
//import com.company.hospital_simulation.HospitalCreate;
//import com.company.hospital_simulation.HospitalModel;
//import com.company.hospital_simulation.HospitalProcess;
//import com.company.hospital_simulation.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;

// BANK

import com.company.bank_simulation.Create;
import com.company.bank_simulation.Element;
import com.company.bank_simulation.Model2;
import com.company.bank_simulation.Process;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

//import com.company.algorithm.Create;
import com.company.algorithm.Create2;
//import com.company.algorithm.Process;
import com.company.base_simulation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Process p11 = new Process(0.3, 0.3, 1);
//        Process p12 = new Process(0.3, 0.3, 1);
//        p11.setOtherProcess(p12);
//        p12.setOtherProcess(p11);
//        Create create = new Create(0.5);
//        create.setNextElements(Arrays.asList(p11,p12));
//        p11.setMaxqueue(3);
//        p12.setMaxqueue(3);
//        p11.setState(1);
//        p12.setState(1);
//        create.setTnext(0.1);
//        p11.setQueue(2);
//        p12.setQueue(2);
//        create.setName("c");
//        p11.setName("p11");
//        p12.setName("p12");
//
//        create.setDistribution("exp");
//        p11.setDistribution("exp");
//        p12.setDistribution("exp");
//
//       Model model = new Model(Arrays.asList(create,p11,p12));
//       model.simulate(1000.0, create);
//
//        CreateHospital c = new CreateHospital(10, "CREATOR", "Exponential", 0);
//        ProcessHospital p0 = new ProcessHospital(2, 5, "GO TO DOCTOR", "Uniform", 3);
//        ProcessHospital p1 = new ProcessHospital(3, 8, "CHAMBERS", "Uniform", 3);
//        ProcessHospital d = new ProcessHospital(3, 8, "EXIT", "Exponential", 3);
//        ProcessHospital p2 = new ProcessHospital(3, 4.5, "REGISTRATION", "Erlang", 4);
//        Laboratory p3 = new Laboratory(2, 4, "LABORATORY", "Erlang", 2);
//        ProcessHospital p4 = new ProcessHospital(2, 5, "GO TO REGISTRATION", "Exponential", 3);
//        Doctor p5 = new Doctor(2, 5, "DOCTOR", "Uniform", 2);
//
//        c.setNextElement(p0);
//        c.patientsTypes = new ArrayList<>(Arrays.asList(
//                new PatientType(1, 0.5, 1 / 15),
//                new PatientType(2, 0.1, 1 / 40),
//                new PatientType(3, 0.4, 1 / 30)
//        ));
//        p0.setNextProcesses(new ArrayList<>(Arrays.asList(p5)));
//        p5.setNextProcesses(new ArrayList<>(Arrays.asList(p1, p4)));
//        p2.setNextProcesses(new ArrayList<>(Arrays.asList(p3))) ;
//        p3.setNextProcesses(new ArrayList<>(Arrays.asList(p0,d)));
//        p4.setNextProcesses(new ArrayList<>(Arrays.asList(p2)));
//
//        List<Element> elementsList = new ArrayList<Element>  (Arrays.asList(c, p0, p1, p2, p3, p4, p5, d));
//        ModelHospital model = new ModelHospital(elementsList);
//        model.Simulate(10);

//                         HOSPITAL
//        var c = new HospitalCreate(10, "CREATOR", "exp", 0);
//        HospitalProcess p0 = new HospitalProcess(2, 5, "GO TO DOCTOR", "unif", 3);
//        HospitalProcess p1 = new HospitalProcess(3, 8, "CHAMBERS", "unif", 3);
//        HospitalProcess d = new HospitalProcess(3, 8, "EXIT", "exp", 3);
//        HospitalProcess p2 = new HospitalProcess(3, 4.5, "REGISTRATION", "erlang", 4);
//        Laboratory p3 = new Laboratory(2, 4, "LABORATORY", "erlang", 2);
//        HospitalProcess p4 = new HospitalProcess(2, 5, "GO TO REGISTRATION", "exp", 3);
//        Doctor p5 = new Doctor(2, 5, "DOCTOR", "unif", 2);
//
//        c.setNextElement(p0);
//        c.patientsTypes = Arrays.asList(
//                new PatientType(1, 0.5, ((double) 1) / 15),
//                new PatientType(2, 0.1, ((double) 1) / 40),
//                new PatientType(3, 0.4, ((double) 1) / 30));
//
//        p0.setNextProcesses(Collections.singletonList(p5));
//        p5.setNextProcesses(Arrays.asList(p1, p4));
//        p2.setNextProcesses(Collections.singletonList(p3));
//        p3.setNextProcesses(Arrays.asList(p0, d));
//        p4.setNextProcesses(Collections.singletonList(p2));
//
//        List<Element> elementsList = Arrays.asList(c, p0, p1, p2, p3, p4, p5, d);
//        HospitalModel model = new HospitalModel(elementsList);
//        model.Simulate(1000.0);


        //  BANK
        Create c = new Create(0.1, "CREATOR", "Exponential",0.0);

        Process p1 = new Process(0.3, "CASHBOX1", "Exponential", 3, 1,0.0);
        Process p2 = new Process(0.3, "CASHBOX2", "Exponential", 3, 1,0.0);
        Process d = new Process(0.3, "GO OUT", "Exponential", 3, 1,0.0);
        p1.setNextProcesses(new ArrayList<>(Arrays.asList(p2,d)));
        p2.setNextProcesses(new ArrayList<>(Arrays.asList(p1,d))) ;
        p1.OtherProcess = p2;
        p2.OtherProcess = p1;
        p1.setState(1);
        p2.setState(1);
        c.settNext(0.5);;
        p1.setQueueLength(2) ;
        p2.setQueueLength(2) ;
        c.setNextElement(new ArrayList<>(Arrays.asList(p1,p2))) ;
        List<Element> list = new ArrayList<>(Arrays.asList(c, p1, p2, d));
        Model2 model = new Model2(list);
        model.Simulate(100.0);
        model.ReturnResult();
//                 Model
//               CreateBase c = new CreateBase(2.0, "CREATOR", "exp",0);
//        ProcessBase p1 = new ProcessBase(0.6, "PROCESSOR1", "exp", 2, 1,0);
//        ProcessBase p2 = new ProcessBase(0.3, "PROCESSOR2", "Exponential", 2, 1,0);
//        ProcessBase p3 = new ProcessBase(0.4, "PROCESSOR3", "Exponential", 2, 1,0);
//        ProcessBase p4 = new ProcessBase(0.1, "PROCESSOR4", "Exponential", 2, 2,0);
//        ProcessBase d = new ProcessBase(0, "DISPOSE", "Exponential", 0, 1,0);
//
//        c.setNextElement(p1);
//        p1.setNextProcesses(new ArrayList<>(Arrays.asList(p2, p3, p4)));
//        p1.setPobabilities(new  ArrayList<>(Arrays.asList(new PobabilityBase(0.15), new PobabilityBase(0.13), new PobabilityBase(0.3))));
//        p2.setPobabilities(new  ArrayList<>(Arrays.asList(new PobabilityBase(0.15), new PobabilityBase(0.13), new PobabilityBase(0.3))));
//        p3.setPobabilities(new  ArrayList<>(Arrays.asList(new PobabilityBase(0.15), new PobabilityBase(0.13), new PobabilityBase(0.3))));
//        p4.setPobabilities(new  ArrayList<>(Arrays.asList(new PobabilityBase(0.15), new PobabilityBase(0.13), new PobabilityBase(0.3))));
//
//        p1.setPriority(new  ArrayList<>(Arrays.asList(2, 3, 1)));
//        p2.setPriority(new  ArrayList<>(Arrays.asList(3, 2, 1)));
//        p3.setPriority(new  ArrayList<>(Arrays.asList(1, 3, 2)));;
//        p4.setPriority(new  ArrayList<>(Arrays.asList(1, 2, 3)));
//
//        p2.setNextProcesses(Arrays.asList(p1));
//        p3.setNextProcesses (Arrays.asList(p1));
//        p4.setNextProcesses (Arrays.asList(p1));
//        List<ElementBase> elementsList = new ArrayList<ElementBase> (Arrays.asList(c, p1, p2, p3, p4));
//        ModelBase model = new ModelBase(elementsList);
//        model.Simulate(1000.0);
//        List<Result> res = model.ReturnResult();
//
//        double[] theoreticalAverageQueue = new double[] { 1.786, 0.003, 0.004, 0.00001 };
//        double[] theoreticalWorkload = new double[] { 0.714, 0.054, 0.062, 0.036 };
//        double[] queueAccuracy = new double[res.size()];
//        double[] workloadAccuracy = new double[res.size()];
//
//        for (int i = 0; i < theoreticalAverageQueue.length; i++) {
//            queueAccuracy[i] = Math.abs(res.get(i).average - theoreticalAverageQueue[i]) / theoreticalAverageQueue[i];
//            workloadAccuracy[i] = Math.abs(res.get(i).workload - theoreticalWorkload[i]) / theoreticalWorkload[i];
//        }
//        System.out.println("Theoretical Queue Accuracy");
//        Arrays.stream(theoreticalAverageQueue).forEach(System.out::println);
//        System.out.println(" Theoretical workload Accuracy");
//
//        Arrays.stream(theoreticalWorkload).forEach(System.out::println);
//        System.out.println("Queue Accuracy");
//        Arrays.stream(queueAccuracy).forEach(System.out::println);
//        System.out.println("workload Accuracy");
//
//        Arrays.stream(workloadAccuracy).forEach(System.out::println);

    }

}

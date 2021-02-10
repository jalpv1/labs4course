package com.company;

import javax.swing.*;

public class Bounce {
    public static void main(String[] args)  {
        BounceFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        System.out.println("Thread name = " +
                Thread.currentThread().getName());
        try {
            if (Thread.currentThread().getName().contains("_ORANGE")){
                Thread.currentThread().join();
            }
        }
        catch (InterruptedException interruptedException){}
       //  BounceFrame.joinTwoTreads();
        //TASK 5
        //wait notify lock
//        Tread1 runnable1 = new Tread1();
//        Thread thread1 = new Thread(runnable1);
//
//        Tread2 runnable2 = new Tread2();
//        Thread thread2 = new Thread(runnable2);
//        thread1.start();
//        thread2.start();
        //TASK6
//        try {
//            new  CounterTask().threadsStart();
//
//        }
//        catch (InterruptedException exception){
//
//        }
      //      new  Symbol("|").draw();
    }
}

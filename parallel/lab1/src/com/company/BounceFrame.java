package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private static BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");
        this.canvas = new BallCanvas();
        canvas.addZone(new EndZone(canvas, 400, 240));

        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStartMagenta = new JButton("Start Magenta");

        JButton buttonStartOrange = new JButton("Start Orange");
        JButton buttonStartTwoBalls = new JButton("Start Two");

        JButton buttonStop = new JButton("Stop");
        buttonStartMagenta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas, Color.MAGENTA);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(10);
                thread.start();
//                try {
//                    thread.join();
//                } catch (InterruptedException interruptedException) {
//                    interruptedException.printStackTrace();
//                }
//                System.out.println("MAGENTA Thread name = " +
//                        thread.getName());
            }
        });

        buttonStartOrange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//for
                Ball b = new Ball(canvas, Color.ORANGE);
                canvas.add(b);
                //tread magents
                BallThread thread = new BallThread(b);
                thread.setPriority(1);
                thread.start();
//                System.out.println("ORANGE Thread name = " +
//                        thread.getName() + " " + thread.getPriority());
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        buttonStartTwoBalls.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                for (int i = 0; i < 500; i++) {
                    Ball b2 = new Ball(canvas, Color.GREEN);
                    canvas.add(b2);
                    BallThread thread2 = new BallThread(b2);
                    thread2.setPriority(1);
                    thread2.start();
                    System.out.println("GREEN Thread name = " +
                            thread2.getName());
                }
                Ball b = new Ball(canvas, Color.YELLOW);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(10);
                thread.start();
                System.out.println("YELLOW Thread name = " +
                        thread.getName());
            }
        });

        buttonPanel.add(buttonStartMagenta);
        buttonPanel.add(buttonStartOrange);
        buttonPanel.add(buttonStartTwoBalls);

        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
    public static void joinTwoTreads(){
        Ball ballBlue = new Ball(canvas, Color.blue);
        Ball ballRed = new Ball(canvas, Color.red);
        canvas.add(ballBlue);
        canvas.add(ballRed);
        BallThread threadBlue = new BallThread(ballBlue);
        BallThread threadRed = new BallThread(ballRed);
        threadBlue.start();
        try{
            threadBlue.join();
        }catch(InterruptedException exception){}

        threadRed.start();
    }
}

package com.company;

import java.awt.*;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball) {
        b = ball;
    }

    @Override
    public void run() {
        if (b.color.equals(Color.ORANGE)){
            Thread.currentThread().setName(Thread.currentThread().getName()+ "_ORANGE");
        }
        try {
            for (int i = 1; i < 1000; i++) {
                b.move();
                System.out.println("Thread name = "
                        + Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
                Thread.sleep(5);
                //TODO
                if (b.color.equals(Color.LIGHT_GRAY)) {
                    return;
                }

            }

        } catch (InterruptedException ex) {
            return;
        }
    }
}

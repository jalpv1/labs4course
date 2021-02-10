package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterTask {
    //volatile Integer counter = 0;
     Integer counter = 0;

    public  void threadsStart() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
         //   try {
               //synchronized (counter) {
                    lock.lock();
                    for (int i = 0; i < 100; i++) {
                   //     counter = counter - 1000;
                        dec();
                       // System.out.println("Counter- = " + counter);
//                        Thread.sleep(5);

                    }
                lock.unlock();

                //}
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
        Thread thread2 = new Thread(() -> {
         //   try {
              //  synchronized (counter) {
             lock.lock();

                for (int i = 0; i < 10000; i++) {
                       // counter = counter + 1000;
                    inc();
                 //       System.out.println("Counter+ = " + counter);
                //        Thread.sleep(5);

                //    }
                }
             lock.unlock();

//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Counter+ = " + counter);

    }
     void inc(){
           counter= counter+1000;
    }
        void dec(){
         counter= counter-1000;
    }
}

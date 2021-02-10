package com.company;

public class Symbol {
    String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    public void draw() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                drawSymbol1();
            }
        });
        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                drawSymbol2();
            }
        });
        thread1.start();
        thread2.start();

    }

    public synchronized void drawSymbol1() {
        try {
            while (symbol.equals("-")) {
                wait();
            }
            System.out.print("-");
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        symbol = "-";
        notify();

    }

    public synchronized void drawSymbol2() {
        try {
            while (symbol.equals("|")) {
                wait();
            }
            System.out.print("|");
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        symbol = "|";
        notify();

    }
}

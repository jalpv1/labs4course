package com.company.algorithm;

public class Channel {
    private boolean isFree;
    private double timeOut;
    private String name;

    public Channel(boolean isFree, double timeOut, String name) {
        this.isFree = isFree;
        this.timeOut = timeOut;
        this.name = name;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public double getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(double timeOut) {
        this.timeOut = timeOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

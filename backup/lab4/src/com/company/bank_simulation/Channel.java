package com.company.bank_simulation;

public class Channel {
    public String name;
    public double timeOut;
    public boolean isFree;
    public Channel(String name, double timeOut, boolean isFree)
    {
        this.name = name;
        this.timeOut = timeOut;
        this.isFree = isFree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(double timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}

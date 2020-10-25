package com.company.bank_simulation;


import java.util.Comparator;

public class ChannelComparator2 implements Comparator<Channel> {
    @Override
    public int compare(Channel a, Channel b) {
        if (a.getTimeOut() < b.getTimeOut()) return -1;
        if (a.getTimeOut() > b.getTimeOut()) return 1;
        return 0;
    }
}

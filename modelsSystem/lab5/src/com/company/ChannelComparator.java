package com.company;

import java.util.Comparator;

public class ChannelComparator implements Comparator<Channel> {

    public int compare(Channel a, Channel b){
        if (a.getTimeOut() < b.getTimeOut()) return -1;
        if (a.getTimeOut() > b.getTimeOut()) return 1;
        return 0;
    }
}

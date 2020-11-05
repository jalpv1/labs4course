package com.company;

public class Place {
    public int markersCount;
    public int markersMin = 0;
    public int markersMax = 0;
    public double markersAvarage = 0.0;
    public String name;

    public Place(String name, int markersCount)
    {
        this.name = name;
        this.markersCount = markersCount;
        if (markersMin<markersCount)
        {
            markersMin = markersCount;
        }
    }

    public void markersStatistic()
    {
        if (markersCount < markersMin){
            markersMin = markersCount;
        }
        if (markersCount > markersMax){
            markersMax = markersCount;
        }
        markersAvarage += markersCount;
    }
}


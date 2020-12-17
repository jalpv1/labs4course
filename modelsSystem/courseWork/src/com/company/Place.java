package com.company;

import java.util.ArrayList;
import java.util.List;

public class Place {
    public String title;
    public int markersCount;
    public int markersMin = 0;
    public int markersMax = 0;
    public double markersAvg = 0.0;
    public List<Marker> markers;
    public Place(String title, int markersCount)
    {
        this.title = title;
        this.markersCount = markersCount;
        markers = new ArrayList<>();
        if (markersMin<markersCount)
        {
            markersMin = markersCount;
        }
    }

    public Place(int markersCount) {
        this.markersCount = markersCount;
    }

    public Place(int markersCount, int markersMin, int markersMax, double markersAvg, String title) {
        this.markersCount = markersCount;
        this.markersMin = markersMin;
        this.markersMax = markersMax;
        this.markersAvg = markersAvg;
        this.title = title;
    }

    public void markersStatistic()
    {
        markersAvg += markersCount;
        if (markersCount < markersMin){
            markersMin = markersCount;
        }
        if (markersCount > markersMax){
            markersMax = markersCount;
        }
    }

    public int getMarkersCount() {
        return markersCount;
    }

    public void setMarkersCount(int markersCount) {
        this.markersCount = markersCount;
    }

    public int getMarkersMin() {
        return markersMin;
    }

    public void setMarkersMin(int markersMin) {
        this.markersMin = markersMin;
    }

    public int getMarkersMax() {
        return markersMax;
    }

    public void setMarkersMax(int markersMax) {
        this.markersMax = markersMax;
    }

    public double getMarkersAvg() {
        return markersAvg;
    }

    public void setMarkersAvg(double markersAvg) {
        this.markersAvg = markersAvg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


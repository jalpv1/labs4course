package com.company;

public class Arc {
    public Transition nextTransition ;
    public Position plaseFrom ;
    public Position placeTo;
    public int n ;
    public String name;

    public Arc(String name, Position next, int n)
    {
        this.name = name;
        placeTo = next;
        this.n = n;
    }

    public Arc(String name, Position previoustPosition, Transition nextTransition, int n)
    {
        this.name = name;
        nextTransition = nextTransition;
        plaseFrom = previoustPosition;
        this.n = n;
    }
}


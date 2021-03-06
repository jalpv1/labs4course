package com.company;

public class Arc {
    public Transition nextTransition ;
    public Place placeFrom;
    public Place placeTo;
    public int multiplicity;
    public String name;

    public Arc(String name, Place next, int multiplicity)
    {
        this.name = name;
        placeTo = next;
        this.multiplicity = multiplicity;
    }

    public Arc(String name, Place previoustPlace, Transition nextTransition, int multiplicity)
    {
        this.name = name;
        this.nextTransition = nextTransition;
        placeFrom = previoustPlace;
        this.multiplicity = multiplicity;
    }
}


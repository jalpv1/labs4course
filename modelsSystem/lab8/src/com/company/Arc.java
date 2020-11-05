package com.company;

public class Arc {
    public Transition nextTransition ;
    public Place placeFrom;
    public Place placeTo;
    public int multiplicity;
    public String name;

    public Arc(String name, Place placeTo, int multiplicity)
    {
        this.name = name;
        this.placeTo = placeTo;
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


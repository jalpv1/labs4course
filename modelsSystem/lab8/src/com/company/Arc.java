package com.company;

public class Arc {
    public String title;
    public Transition nextTransition ;
    public int multiplicity;
    public Place placeFrom;
    public Place placeTo;

    public Arc(String title, Place placeTo, int multiplicity)
    {
        this.title = title;
        this.placeTo = placeTo;
        this.multiplicity = multiplicity;
    }

    public Arc(String title, Place previoustPlace, Transition nextTransition, int multiplicity)
    {
        this.title = title;
        this.nextTransition = nextTransition;
        placeFrom = previoustPlace;
        this.multiplicity = multiplicity;
    }

    public Arc(Transition nextTransition, Place placeFrom, Place placeTo) {
        this.nextTransition = nextTransition;
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
    }

    public Transition getNextTransition() {
        return nextTransition;
    }

    public void setNextTransition(Transition nextTransition) {
        this.nextTransition = nextTransition;
    }

    public Place getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(Place placeFrom) {
        this.placeFrom = placeFrom;
    }

    public Place getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(Place placeTo) {
        this.placeTo = placeTo;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


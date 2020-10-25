package com.company.base;

import java.util.ArrayList;
import java.util.Random;

public class ComposeElement extends BaseElement {
    ArrayList<BaseElement> elements;

    public ComposeElement() {
        super();
        elements = new ArrayList<>();
    }

    @Override
    public boolean act() {

        //return super.act();
        BaseElement el = getRandomPosition();
        //44444444444444444444444
        return el.act();
    }

    @Override
    public void inn() {
        BaseElement el = getRandomPosition();
        //44444444444444444444444
        el.inn();
    }

    @Override
    public void showInfo() {
        super.showInfo();
    }

    public int getMinIndex() {
return 0;
    }

    @Override
    public void incrementCountOfHandledObjects() {
        super.incrementCountOfHandledObjects();
    }

    public BaseElement getRandomPosition() {
        Random rnd = new Random();
        return elements.get(rnd.nextInt(elements.size()));

    }
}

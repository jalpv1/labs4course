package com.company.base;

public class Creator extends BaseElement {
    double curt;

    public boolean act() {
        curt = getExecuteFinishTime();
        incrementCountOfHandledObjects();
        if (getNextElement() == null) {
            return false;
        }
        getNextElement().inn();

        return true;
    }

    @Override
    public void inn() {
        super.inn();
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("el" + getElementName()+ "handl" + getCountOfHandledObjects());
    }
}

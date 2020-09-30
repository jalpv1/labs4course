package com.company.base;

import com.company.randomer.FunRand;

public class BaseElement {
    private double maxDelay;
    private String randomType  = "exp";
    private String elementName = "";
    private int countOfHandledObjects = 0;
    private double executeFinishTime = 0.0;
    private double squareTimeDelay = 0.0;
    private BaseElement nextElement ;
    public BaseElement() {
        nextElement = new BaseElement();
    }

    public BaseElement(double maxDelay, String randomType, double squareTimeDelay) {
        this.maxDelay = maxDelay;
        this.randomType = randomType;
        this.squareTimeDelay = squareTimeDelay;
    }

    public double getDelay() {
        double delay = getMaxDelay();
        if ("exp".equalsIgnoreCase(getRandomType())) {
            delay = FunRand.Exp(getMaxDelay());
        } else {
            if ("norm".equalsIgnoreCase(getRandomType())) {
                delay = FunRand.Norm(getMaxDelay(),
                       getSquareTimeDelay());
            } else {
                if ("unif".equalsIgnoreCase(getRandomType())) {
                    delay = FunRand.Unif(getMaxDelay(),
                            getSquareTimeDelay());
                } else {
                    if ("".equalsIgnoreCase(getRandomType()))
                        delay = getMaxDelay();
                }
            }
        }

        return delay;
    }
//    public void getExecuteFinishTime(){
//
//    }

    public boolean act(){
     return true;
    }
    public void inn(){

    }
    public void showInfo(){

    }
    public void incrementCountOfHandledObjects(){
        countOfHandledObjects += 1;

    }
    public void incrementExecuteFinishTime(double timeValue){
        executeFinishTime += timeValue;

    }

    public double getMaxDelay() {
        return maxDelay;
    }

    public void setMaxDelay(double maxDelay) {
        this.maxDelay = maxDelay;
    }

    public double getSquareTimeDelay() {
        return squareTimeDelay;
    }

    public void setSquareTimeDelay(double squareTimeDelay) {
        this.squareTimeDelay = squareTimeDelay;
    }

    public String getRandomType() {
        return randomType;
    }

    public void setRandomType(String randomType) {
        this.randomType = randomType;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public int getCountOfHandledObjects() {
        return countOfHandledObjects;
    }

    public void setCountOfHandledObjects(int countOfHandledObjects) {
        this.countOfHandledObjects = countOfHandledObjects;
    }

    public double getExecuteFinishTime() {
        return executeFinishTime;
    }

    public void setExecuteFinishTime(double executeFinishTime) {
        this.executeFinishTime = executeFinishTime;
    }

    public BaseElement getNextElement() {
        return nextElement;
    }

    public void setNextElement(BaseElement nextElement) {
        this.nextElement = nextElement;
    }
}

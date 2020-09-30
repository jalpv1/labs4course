package com.company;

public class Despose  extends Element{
    @Override
    public void outAct(int count) {
        System.out.println("Dispose");
        super.outAct(1);
    }

}

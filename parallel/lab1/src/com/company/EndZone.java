package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EndZone {
    private Component canvas;
    private static final int XSIZE = 50;
    private static final int YSIZE = 50;
    public static int x = 0;
    public static int  y= 0;

    public EndZone(Component c, int x, int y) {
        this.canvas = c;
        EndZone.x = x;
        EndZone.y = y;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));


    }
}


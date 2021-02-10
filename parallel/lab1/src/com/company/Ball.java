package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

class Ball {
    private Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    Color color;
    static int deleted = 0;

    public Ball(Component c, Color color) {
        this.canvas = c;
        this.color = color;

//        if (Math.random() < 0.5) {
//          //  x = new Random().nextInt(this.canvas.getWidth());
//            x = 20;
//
//            y = 0;
//        } else {
        // y = new Random().nextInt(this.canvas.getHeight());
        //}
//ONE PLACE
        x = 0;
        y = 20;
        //NOT ONE
//        if (Math.random() < 0.5) {
//            x = new Random().nextInt(this.canvas.getWidth());
//            y = 0;
//        } else {
//            x = 0;
//            y = new Random().nextInt(this.canvas.getHeight());
//        }
    }

    public static void f() {
        int a = 0;
    }

    public void draw(Graphics2D g2, Color color) {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));

    }

    public void move() throws InterruptedException {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        if ((y == EndZone.y && x == EndZone.x) || (y== EndZone.y + 50 && x == EndZone.x + 50) || (y == EndZone.y - 50 && x == EndZone.x - 50)) {
            color = Color.LIGHT_GRAY;
            deleted++;
            System.out.println("DELETED = " + deleted);
            return;

        }
        this.canvas.repaint();
    }
}

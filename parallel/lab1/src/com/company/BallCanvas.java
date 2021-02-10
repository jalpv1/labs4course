package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();
    private EndZone zone;

    public void add(Ball b) {
        this.balls.add(b);
        this.zone = zone;
    }

    public void addZone(EndZone zone) {
        this.zone = zone;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g3 = (Graphics2D) g;
        Graphics2D g2 = (Graphics2D) g;
        balls = balls.stream().filter(ball -> !ball.color.equals(Color.LIGHT_GRAY)).collect(Collectors.toCollection(ArrayList::new));
        for (Ball ball : balls) {
            ball.draw(g2, ball.color);
        }
        zone.draw(g3);
    }
}

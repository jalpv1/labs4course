package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EndZoneCanvas extends JPanel {
    private ArrayList<EndZone> pockets = new ArrayList<>();

    public void add(EndZone p) {
        this.pockets.add(p);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < pockets.size(); i++) {
            EndZone p = pockets.get(i);
            p.draw(g2);

        }
    }
}

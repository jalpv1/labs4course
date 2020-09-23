package com.company;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static java.awt.Color.RED;

class DrawingComponent extends JPanel {
    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;

        //горизонтальные линии и обозначения
        for (int i=2; i<9; i++) {
            drp.drawLine(50, 50+44*i, 400, 50+44*i);
            int vs = 80 - i*10;
            drp.drawString(vs+"", 30, 50+44*i);
        }

        drp.drawString("upread.ru", 100, 40);
        drp.drawString("google.ru", 100, 60);
        drp.drawString("yandex.ru", 100, 80);

        drp.drawString("Январь", 60, 420);
        drp.drawString("Февраль", 160, 420);
        drp.drawString("Март", 260, 420);
        drp.drawString("Апрель", 360, 420);

        drp.setColor(Color.BLUE);
        drp.fillRect(80, 30, 10, 10);
        drp.setColor(RED);
        drp.fillRect(80, 50, 10, 10);
        drp.setColor(Color.GREEN);
        drp.fillRect(80, 70, 10, 10);

        for (int i=0; i<4; i++) {
            //строим саму гистограмму
            //извлекаем цвет для каждого графика
            Color color = RED;
            for (int j=0;j<3;j++) {
                try {
                    Field field = Class.forName("java.awt.Color").getField(GrGis.col[j].toLowerCase());
                    color = (Color)field.get(null);
                } catch (Exception e) {}
                drp.setColor(color);
                //переводим полученные данные в реальные координаты
                int realY = (int) (400-44*GrGis.y[j][i]/10)+3;
                drp.fillRect(50+20*j+100*i, realY, 20,(int) (GrGis.y[j][i]*4.4));
            }
        }
    }
}


package model;

import java.awt.*;

/**
 * Eine Farbe welche sich die Haeufigkeit ihres Vorkommens merken kann
 * Created by const on 02.05.2016.
 */

public class AdvColor extends Color {

    int num;//Vorkommen

    public AdvColor(int rgb) {
        super(rgb);
        num = 1;
    }

    /**
     * Methode zum erhoehen des Vorkommens
     */
    void add(){
        num++;
    }
    public String toString(){
        return getClass().getName() + "[r=" + getRed() + ",g=" + getGreen() + ",b=" + getBlue() + "]" + "Count: " + num;
    }

}

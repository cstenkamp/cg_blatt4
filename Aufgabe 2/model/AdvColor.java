package model;

import java.awt.*;

/**
 * Created by const on 02.05.2016.
 */
public class AdvColor extends Color {

    int num;

    public AdvColor(int rgb) {
        super(rgb);
        num = 1;
    }

    void add(){
        num++;
    }
    public String toString(){
        return getClass().getName() + "[r=" + getRed() + ",g=" + getGreen() + ",b=" + getBlue() + "]" + "Count: " + num;
    }

}

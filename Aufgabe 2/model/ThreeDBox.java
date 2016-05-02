package model;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;

/**
 * Beschreibt eine dreidimensionale box
 * Created by const on 01.05.2016.
 *
 */
public class ThreeDBox {

    private AdvColor[] Colors;
    private HashMap colors;
    private int depth;//Groesse der Box
    int rmin,gmin,bmin,rmax,gmax,bmax;

    /**
     * Baut eine Box aus einer Hashmap
     * @param depth
     * @param colors
     */
    public ThreeDBox(int depth, HashMap colors){
        this.depth = depth;
        this.colors = colors;
        Collection cols = colors.values();
        Colors = (AdvColor[]) cols.toArray(new AdvColor[colors.size()]);//wandelt HashMap in ein Array
        int[] Red = new int[colors.size()];
        int[] Green = new int[colors.size()];
        int[] Blue = new int[colors.size()];

        for(int i = 0; i < colors.size(); i++){
            Red[i] = Colors[i].getRed();
            Green[i] = Colors[i].getGreen();
            Blue[i] = Colors[i].getBlue();
        }//Kopiert Werte in jeweilige Farbarrays

        rmin = Minimum(Red);
        gmin = Minimum(Green);
        bmin = Minimum(Blue);
        rmax = Maximum(Red);
        gmax = Maximum(Green);
        bmax = Maximum(Blue);


    }

    public int getDepth(){
        return depth;
    }

    public int getLength(){
        return Colors.length;
    }

    public AdvColor[] getColors(){
        return Colors;
    }

    public HashMap getHashMap(){
        return colors;
    }


    /**
     * Berechnet das Minimum eines Int Arrays
     * @param Array Das Array
     * @return Das Minimum
     */
    private int Minimum(int[] Array){
        int minimum = Array[0];
        for(int i = 1; i < Array.length; i++){
            if(Array[i] < minimum){
                minimum = Array[i];
            }
        }
        return minimum;
    }
    /**
     * Berechnet das Maximum eines Int Arrays
     * @param Array Das Array
     * @return Das Maximum
     */
    private int Maximum(int[] Array){
        int maximum = Array[0];
        for(int i = 1; i < Array.length; i++){
            if(Array[i] > maximum){
                maximum = Array[i];
            }
        }
        return maximum;
    }

    @Override
    public String toString() {
        return "RedMinimum: " + rmin + " RedMaximum: " + rmax + " GreenMinimum: " + gmin + " GreenMaximum: " + gmax + " BlueMinimum: " + bmin + " BlueMaximum: " + bmax;
    }
}

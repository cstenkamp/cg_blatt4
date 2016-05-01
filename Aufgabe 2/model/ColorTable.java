package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorTable {

    private Color[] ReducedColors;
	
	public ColorTable(BufferedImage img, int numColors) {
        ReducedColors = new Color[numColors];


		// TODO: (A2) Bild analysieren und Ergebnis speichern
		System.out.println("With: " + numColors);
	}
	
	/**
	 * Gibt die Farbe der erstellten Farbtabelle zurück, mit der
	 * die originale Farbe dargestellt werden soll.
	 * @param orig Die originale Farbe
	 * @return
	 */
	public Color getReducedColor(Color orig) {
		int index = 0;
        float best = calcdist(orig, ReducedColors[0]);
        for(int i = 1; i < ReducedColors.length; i++){
            if(best > calcdist(orig, ReducedColors[i])){
                best = calcdist(orig, ReducedColors[i]);
                index = i;
            }
        }
        return ReducedColors[index];
		
		// return dummy colors
		//if (orig.getGreen() > 100) {
		//	return Color.WHITE;
		//} else {
		//	return Color.BLUE;
		//}
	}

    private float calcdist(Color colorA, Color colorB){
        float red = Math.abs(colorA.getRed() - colorB.getRed());
        float green = Math.abs(colorA.getGreen() - colorB.getGreen());
        float blue = Math.abs(colorA.getBlue() - colorB.getBlue());
        return (float) ((red+green+blue)/3.0);
    }

}

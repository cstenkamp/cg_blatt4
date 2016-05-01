package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorTable {
	
	public ColorTable(BufferedImage img, int numColors) {
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
		// TODO: (A2) Auf die generierten Daten zugreifen, um eine
		// passende Farbe zurück zu geben.
		
		// return dummy colors
		if (orig.getGreen() > 100) {
			return Color.WHITE;
		} else {
			return Color.BLUE;
		}
	}

}

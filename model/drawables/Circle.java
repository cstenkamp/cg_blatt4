package model.drawables;

import java.awt.Color;
import java.awt.Graphics;


/**
 * Circle Klasse, die einen Kreis mit dem Bresenham-Algorithmus zeichen kann.
 * Ein Kreis besteht aus einem Mittelpunkt und einem Radius.
 * 
 * @author Nicolas Neubauer
 */
public class Circle extends DrawableObject {

	protected Point center; // Mittelpunkt m
	protected int radius; // Radius r
	
	public Color color;

	/**
	 * Konstruktor, der aus einem Objekt Point und dem Radius einen Kreis
	 * erzeugt.
	 * 
	 * @param m
	 *            Mittelpunkt des Kreises
	 * @param r
	 *            Radius des Kreises
	 */
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	/**
	 * Zeichnet den Kreis im XOR Modus
	 * 
	 * @param g
	 *            der grafische Kontext in den der Kreis gezeichnet werden soll
	 */
	public void paint(Graphics g) {
		// additive colors
		g.setXORMode(Color.BLACK);
		
		// naive, easy way of drawing the circle
		for (int x = -radius; x < radius; x++) {
			for (int y = -radius; y < radius; y++) {
				double distance = Math.sqrt(x*x + y*y);
				if (distance <= radius) {
					setPixel(center.x + x, center.y + y, color, g);
				}
			}
		}
	}

}

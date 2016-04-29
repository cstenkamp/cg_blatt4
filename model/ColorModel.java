package model;

import java.awt.Color;
import java.util.Observable;

/**
 * Kapselt eine Farbe (Color) im RGB-Raum und ist Observable Setter und
 * Beanchrichtigung müssen noch implementiert werden.
 * 
 * @author Nicolas Neubauer
 * 
 */
public class ColorModel extends Observable {

	// Die Farbe
	private Color color;

	/**
	 * Konstruktor, der die Farbe setzt
	 * 
	 * @param color
	 *            die Farbe
	 */
	public ColorModel(Color color) {
		this.color = color;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setzt die Farbe und benachrichtigt alle Observer.
	 * 
	 * @param c
	 *            die Farbe
	 */
	public void setColor(Color c) {
		this.color = c;
		setChanged();
		notifyObservers();
	}

}

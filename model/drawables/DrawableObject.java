/**
 * 
 */
package model.drawables;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Oberklasse für grafische (2D-)Objekte
 * 
 * @author Nicolas Neubauer
 * 
 */
public abstract class DrawableObject {
	/**
	 * Diese Methode sollte das jeweilige Objekt in den grafischen Kontext
	 * zeichnen.
	 * 
	 * @param g
	 *            der grafische Kontext
	 */
	public abstract void paint(Graphics g);

	/**
	 * Setzt Pixel in einen grafischen Kontext
	 * 
	 * @param x
	 *            X-Koordinate des Punktes
	 * @param y
	 *            Y-Koordniate des Punktes
	 * @param g
	 *            der grafische Kontext in dem das Pixel gesetzt werden soll
	 */
	protected void setPixel(int x, int y, Graphics g) {
		// Ein Viereck zeichnen.
		g.fillRect(x, y, 1, 1);
	}

	/**
	 * Setzt Pixel mit einem anzugebenden Grauwert in einen grafischen Kontext.
	 * Stellt die vorher gesetzte Farbe wieder her (seiteneffektfrei).
	 * 
	 * @param x
	 *            X-Koordinate
	 * @param y
	 *            Y-Koordinate
	 * @param brightness
	 *            Grauwert (zwischen 0.0 (schwarz) und 1.0 (weiß))
	 * @param g
	 *            grafischer Kontext mit dem gezeichnet werden soll
	 */
	protected void setPixel(int x, int y, float brightness, Graphics g) {

		if (brightness < 0.0f || brightness > 1.0f)
			throw new IllegalArgumentException(
					"Grauwert muss zwischen 0.0 und 1.0 liegen.");

		Color newColor = new Color(brightness, brightness, brightness);
		setPixel(x, y, newColor, g);
	}
	
	/**
	 * Setzt Pixel mit der gegebenen Farbe in einen grafischen Kontext. 
	 * (seiteneffektfrei)
	 * 
	 * @param x
	 *            X-Koordinate
	 * @param y
	 *            Y-Koordinate
	 * @param color
	 *            Zu setzende Farbe
	 * @param g
	 *            grafischer Kontext mit dem gezeichnet werden soll
	 */
	protected void setPixel(int x, int y, Color color, Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(color);
		setPixel(x, y, g);
		g.setColor(oldColor);
	}

}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.drawables.Circle;
import model.drawables.Point;

/**
 * Stellt eine Zeichenfläche zur Verfügung, auf der drei Kreise die gerade
 * gewählte Farbe anzeigen.
 * 
 * @author Nicolas Neubauer
 * 
 */
@SuppressWarnings("serial")
public class ColorPanel extends JPanel {

	private Color displayColor;

	/**
	 * Konstruktor, der die bevorzugte Größe erwartet
	 * 
	 * @param width
	 * @param height
	 * @param drawableObjectsModel
	 */
	public ColorPanel(int width, int height) {

		super();

		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException(
					"Groesse muss >= 1x1 Pixel sein.");

		// Setze die bevorzugte Größe
		Dimension d = new Dimension(width, height);
		setPreferredSize(d);

		// Setze die Farben
		setBackground(Color.BLACK);

	}

	/**
	 * Zeichne die Farbkreise
	 */
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.BLACK);
		
		Circle c;
		int radius = 120;
		Point mitte = new Point(this.getSize().width / 2,
				this.getSize().height / 2);

		c = new Circle(new Point((int) (mitte.x - 0.3 * radius),
				(int) (mitte.y - 0.3 * radius)), radius);
		c.color = new Color(getDisplayColor().getRed(), 0, 0);
		c.paint(g);

		c = new Circle(new Point((int) (mitte.x),
				(int) (mitte.y + 0.3 * radius)), radius);
		c.color = new Color(0, 0, getDisplayColor().getBlue());
		c.paint(g);

		c = new Circle(new Point((int) (mitte.x + 0.3 * radius),
				(int) (mitte.y - 0.3 * radius)), radius);
		c.color = new Color(0, getDisplayColor().getGreen(), 0);
		c.paint(g);

	}

	/**
	 * @return the displayColor
	 */
	public Color getDisplayColor() {
		return displayColor;
	}

	/**
	 * @param displayColor
	 *            the displayColor to set
	 */
	public void setDisplayColor(Color displayColor) {
		this.displayColor = displayColor;
	}

}

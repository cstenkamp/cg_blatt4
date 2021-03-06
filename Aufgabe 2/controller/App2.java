package controller;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * 
 * Eine Applikation, um zweidimensionale Objekte in eine Zeichenfläche zu
 * zeichnen.
 * 
 * @author Nicolas Neubauer
 * 
 */
public class App2 {

	/**
	 * Startet die Applikation als JFrame.
	 * 
	 * @param args
	 *            nicht unterstützt
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Draw2D");

		ImageController c = new ImageController();

		frame.add(c.getView(), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}

package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Eine ColorPanelView stellt eine Zeichenfläche und Farbauswahlmöglichkeiten
 * bereit.
 * 
 * @author Nicolas Neubauer
 * 
 */
@SuppressWarnings("serial")
public class ColorPanelView extends JPanel {

	// View-Components
	private ColorPanel colorPanel;

	// Color-Choosing-Panels
	private ColorChoosingPanel rgbPanel;
	private ColorChoosingPanel cmyPanel;
	private ColorChoosingPanel hsvPanel;
	private ColorChoosingPanel yuvPanel;
	

	
	/**
	 * Konstruktor, der eine neue View anlegt.
	 * 
	 * @param preferedWidthOfDrawingPanel
	 * @param preferedHeightOfDrawingPanel
	 */

	public ColorPanelView(int preferedWidthOfDrawingPanel,
			int preferedHeightOfDrawingPanel) {

		if (preferedWidthOfDrawingPanel < 1 || preferedHeightOfDrawingPanel < 1)
			throw new IllegalArgumentException(
					"Groesse des DrawingPanels muss 1x1 oder groesser sein.");

		this.setLayout(new BorderLayout());

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		// Besorge ein ColorPanel
		colorPanel = new ColorPanel(preferedWidthOfDrawingPanel,
				preferedHeightOfDrawingPanel);
		leftPanel.add(colorPanel, BorderLayout.CENTER);

		// Fuege Linkes Panel hinzu
		this.add(leftPanel, BorderLayout.CENTER);

		// Erzeuge Platz für die ColorChoosingPanels
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		rightPanel.setLayout(new GridLayout(0, 1));
		this.add(rightPanel, BorderLayout.EAST);

		// Füge einzelne ColorChoosingPanels hinzu
		rgbPanel = new ColorChoosingPanel("RGB", "R", "G", "B");
		rightPanel.add(rgbPanel);

		cmyPanel = new ColorChoosingPanel("CMY", "C", "M", "Y");
		rightPanel.add(cmyPanel);
		
		hsvPanel = new ColorChoosingPanel("HSV", "H", "S", "V");
		rightPanel.add(hsvPanel);
	}

	/**
	 * @return the colorPanel
	 */
	public ColorPanel getColorPanel() {
		return colorPanel;
	}

	/**
	 * @return the rgbPanel
	 */
	public ColorChoosingPanel getRGBPanel() {
		return rgbPanel;
	}

	/**
	 * @return the cmyPanel
	 */
	public ColorChoosingPanel getCMYPanel() {
		return cmyPanel;
	}

	/**
	 * @return the hsvPanel
	 */
	public ColorChoosingPanel getHSVPanel() {
		return hsvPanel;
	}

	/**
	 * @return the yuvPanel
	 */
	public ColorChoosingPanel getYUVPanel() {
		return yuvPanel;
	}

}

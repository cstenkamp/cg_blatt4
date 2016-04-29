package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


/**
 * Ein Panel mit drei Slidern mit deren Hilfe Farbwerte gesetzt werden können
 * sollen.
 * 
 * @author Nicolas Neubauer
 * 
 */
@SuppressWarnings("serial")
public class ColorChoosingPanel extends JPanel {

	// View-Components
	private JLabel modelLabel;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel valueLabel1;
	private JLabel valueLabel2;
	private JLabel valueLabel3;
	private JSlider slider1;
	private JSlider slider2;
	private JSlider slider3;

	/**
	 * Erzeugt ein neues Panel
	 * 
	 * @param modelName
	 *            Name des Farbmodells
	 * @param s1
	 *            Name der Komponente1
	 * @param s2
	 *            Name der Komponente2
	 * @param s3
	 *            Name der Komponente3
	 */
	public ColorChoosingPanel(String modelName, String s1, String s2, String s3) {

		this.setPreferredSize(new Dimension(200, 150));

		modelLabel = new JLabel(modelName);
		label1 = new JLabel(s1);
		label2 = new JLabel(s2);
		label3 = new JLabel(s3);
		valueLabel1 = new JLabel();
		valueLabel2 = new JLabel();
		valueLabel3 = new JLabel();
		slider1 = new JSlider();
		slider1.setPreferredSize(new Dimension(100, 0));
		slider2 = new JSlider();
		slider2.setPreferredSize(new Dimension(100, 0));
		slider3 = new JSlider();
		slider3.setPreferredSize(new Dimension(100, 0));

		this.setLayout(new GridLayout(4, 3));

		this.add(modelLabel);
		this.add(new JLabel());
		this.add(new JLabel());

		this.add(label1);
		this.add(valueLabel1);
		this.add(slider1);

		this.add(label2);
		this.add(valueLabel2);
		this.add(slider2);

		this.add(label3);
		this.add(valueLabel3);
		this.add(slider3);
	
	}

	/**
	 * @return the modelLabel
	 */
	public JLabel getModelLabel() {
		return modelLabel;
	}

	/**
	 * @return the label1
	 */
	public JLabel getLabel1() {
		return label1;
	}

	/**
	 * @return the label2
	 */
	public JLabel getLabel2() {
		return label2;
	}

	/**
	 * @return the label3
	 */
	public JLabel getLabel3() {
		return label3;
	}

	/**
	 * @return the valueLabel1
	 */
	public JLabel getValueLabel1() {
		return valueLabel1;
	}

	/**
	 * @return the valueLabel2
	 */
	public JLabel getValueLabel2() {
		return valueLabel2;
	}

	/**
	 * @return the valueLabel3
	 */
	public JLabel getValueLabel3() {
		return valueLabel3;
	}

	/**
	 * @return the slider1
	 */
	public JSlider getSlider1() {
		return slider1;
	}

	/**
	 * @return the slider2
	 */
	public JSlider getSlider2() {
		return slider2;
	}

	/**
	 * @return the slider3
	 */
	public JSlider getSlider3() {
		return slider3;
	}

}

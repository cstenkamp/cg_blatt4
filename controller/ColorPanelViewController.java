package controller;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.event.ChangeListener;

import model.ColorModel;
import model.HsvColor;
import model.RgbColor;
import model.CmyColor;
import view.ColorPanelView;

/**
 * Der Controller, der die ColorPanelView verwaltet. Observiert das Model und
 * muss ggf. Änderungen an die View weitergeben. Hier finden auch alle
 * Farbumrechnungen statt.
 * 
 * @author Nicolas Neubauer
 * 
 */
public class ColorPanelViewController implements Observer {

	// View
	private ColorPanelView colorPanelView;

	// Model
	private ColorModel colorModel;

	// Konstanten für die Farbmodelle
	protected static final int RGB = 0;
	protected static final int CMY = 1;
	protected static final int HSV = 2;

	private boolean updating = false;

	/**
	 * Der Konstruktor initialisiert die View und das Model.
	 */
	public ColorPanelViewController() {

		colorModel = new ColorModel(Color.WHITE);
		colorPanelView = new ColorPanelView(640, 480);

		colorModel.addObserver(this);

		update(colorModel, null);

		// Passe Slider für Modelle an
		// RGB
		getView().getRGBPanel().getSlider1().setMinimum(0);
		getView().getRGBPanel().getSlider2().setMinimum(0);
		getView().getRGBPanel().getSlider3().setMinimum(0);
		getView().getRGBPanel().getSlider1().setMaximum(255);
		getView().getRGBPanel().getSlider2().setMaximum(255);
		getView().getRGBPanel().getSlider3().setMaximum(255);

		// CMY
		getView().getCMYPanel().getSlider1().setMinimum(0);
		getView().getCMYPanel().getSlider2().setMinimum(0);
		getView().getCMYPanel().getSlider3().setMinimum(0);
		getView().getCMYPanel().getSlider1().setMaximum(255);
		getView().getCMYPanel().getSlider2().setMaximum(255);
		getView().getCMYPanel().getSlider3().setMaximum(255);

		// HSV
		getView().getHSVPanel().getSlider1().setMinimum(0);
		getView().getHSVPanel().getSlider2().setMinimum(0);
		getView().getHSVPanel().getSlider3().setMinimum(0);
		getView().getHSVPanel().getSlider1().setMaximum(359);
		getView().getHSVPanel().getSlider2().setMaximum(100);
		getView().getHSVPanel().getSlider3().setMaximum(100);

		// ChangeListner für das RGB-Panel
		ChangeListener rgb = e -> slidersDidChange(RGB);
		getView().getRGBPanel().getSlider1().addChangeListener(rgb);
		getView().getRGBPanel().getSlider2().addChangeListener(rgb);
		getView().getRGBPanel().getSlider3().addChangeListener(rgb);

		// ChangeListner für das CMY-Panel
		ChangeListener cmy = e -> slidersDidChange(CMY);
		getView().getCMYPanel().getSlider1().addChangeListener(cmy);
		getView().getCMYPanel().getSlider2().addChangeListener(cmy);
		getView().getCMYPanel().getSlider3().addChangeListener(cmy);

		// ChangeListner für das HSV-Panel
		ChangeListener hsv = e -> slidersDidChange(HSV);
		getView().getHSVPanel().getSlider1().addChangeListener(hsv);
		getView().getHSVPanel().getSlider2().addChangeListener(hsv);
		getView().getHSVPanel().getSlider3().addChangeListener(hsv);

		// Einmal Änderungen provozieren, damit alle Slider angepasst werden.
		slidersDidChange(RGB);
	}

	/**
	 * Wird von den Listenern aufgerufen, um die Farben im Model anzupassen.
	 * Wird durch einen Pseudo-Monitor durch Zugriffe im gleichen Laufzeit-Stack
	 * geschützt.
	 * 
	 * @param colorModel
	 *            Konstante für das Farbmodell
	 */
	private void slidersDidChange(int colorModel) {

		if (updating) {
			return;
		}
		else {
			updating = true;
		}

		switch (colorModel) {
		case RGB: {
			int r = getView().getRGBPanel().getSlider1().getValue();
			int g = getView().getRGBPanel().getSlider2().getValue();
			int b = getView().getRGBPanel().getSlider3().getValue();

			
			this.colorModel.setColor(new RgbColor(r, g, b).toColor());
			break;
		}
		case CMY: {
			int y = getView().getCMYPanel().getSlider1().getValue();
			int m = getView().getCMYPanel().getSlider2().getValue();
			int c = getView().getCMYPanel().getSlider3().getValue();

			this.colorModel.setColor(new CmyColor(y, m, c).toColor());
			break;
		}
		case HSV: {

			int h = getView().getHSVPanel().getSlider1().getValue();
			double s = getView().getHSVPanel().getSlider2().getValue() / 100.0;
			double v = getView().getHSVPanel().getSlider3().getValue() / 100.0;
			
			this.colorModel.setColor(new HsvColor(h, s, v).toColor());
			break;
		}
		default:
			break;
		}

		updating = false;
	}

	/**
	 * Gibt die verwaltete View zurück.
	 * 
	 * @return die View
	 */
	public ColorPanelView getView() {
		return colorPanelView;
	}

	/**
	 * Ändere die View-Komponenten entsprechend der Änderung des Models.
	 */
	public void update(Observable o, Object arg) {

		getView().getColorPanel().setDisplayColor(colorModel.getColor());

		// RGB-Values
		RgbColor rgb = RgbColor.fromColor(this.colorModel.getColor());
		getView().getRGBPanel().getValueLabel1().setText(rgb.r + "");
		getView().getRGBPanel().getValueLabel2().setText(rgb.g + "");
		getView().getRGBPanel().getValueLabel3().setText(rgb.b + "");

		// RGB-Slider
		getView().getRGBPanel().getSlider1().setValue(rgb.r);
		getView().getRGBPanel().getSlider2().setValue(rgb.g);
		getView().getRGBPanel().getSlider3().setValue(rgb.b);

		// CMY-Values
		CmyColor cmy = CmyColor.fromColor(this.colorModel.getColor());
		getView().getCMYPanel().getValueLabel1().setText(cmy.c + "");
		getView().getCMYPanel().getValueLabel2().setText(cmy.m + "");
		getView().getCMYPanel().getValueLabel3().setText(cmy.y + "");

		// CMY-Slider
		getView().getCMYPanel().getSlider1().setValue(cmy.c);
		getView().getCMYPanel().getSlider2().setValue(cmy.m);
		getView().getCMYPanel().getSlider3().setValue(cmy.y);

		// HSV-Values
		HsvColor hsv = HsvColor.fromColor(this.colorModel.getColor());
	
		getView().getHSVPanel().getValueLabel1().setText(hsv.h + "");
		getView().getHSVPanel().getValueLabel2().setText((int)hsv.s + "%");
		getView().getHSVPanel().getValueLabel3().setText((int)hsv.v + "%");

		// HSV-Slider
		getView().getHSVPanel().getSlider1().setValue(hsv.h);
		getView().getHSVPanel().getSlider2().setValue((int)hsv.s);
		getView().getHSVPanel().getSlider3().setValue((int)hsv.v);

		getView().getColorPanel().repaint();

	}

}

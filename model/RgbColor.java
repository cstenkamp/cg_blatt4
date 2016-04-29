package model;

import java.awt.Color;

public class RgbColor {
	public int r, g, b;
	
	public RgbColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public static RgbColor fromColor(Color rgb) {
		return new RgbColor(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
	}
	
	public Color toColor() {
		return new Color(r, g, b);
	}
}

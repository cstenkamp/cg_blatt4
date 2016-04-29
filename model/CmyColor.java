package model;

import java.awt.Color;

public class CmyColor {
	public int c, m, y;
	
	public CmyColor(int c, int m, int y) {
		this.c = c;
		this.m = m;
		this.y = y;
	}
	
	public static CmyColor fromColor(Color rgb) {
		int c = 255-rgb.getRed();
		int m = 255-rgb.getGreen();
		int y = 255-rgb.getBlue();
		return new CmyColor(c, m, y);
	}
	
	public Color toColor() {
		int r = 255-c;
		int g = 255-m;
		int b = 255-y;
		return new Color(r,g,b);
	}

}

package model;

import java.awt.Color;

public class HsvColor {
	public int h;
	public double s, v;
	
	public HsvColor(int h, double s, double v) {
		this.h = h;
		this.s = s;
		this.v = v;
	}
	
	public static HsvColor fromColor(Color rgb) {
		double r,g,b;
		r = rgb.getRed()/255.0;
		g = rgb.getGreen()/255.0;
		b = rgb.getBlue()/255.0;
		double tmp = r > g ? r : g;
		double value = tmp > b ? tmp : b;
		double tmp2 = r < g ? r : g;
		double min = tmp2 < b ? tmp2 : b;		
		double saturation = value == 0 ? 0 : (value-min)/value;
		int hue = 0;
		if ((r == g) && (r == b)) {
			hue = 0;
		}
		if ((r >= g) && (r >= b)) {
			hue = (int)Math.round(60*((g-b)/(value-min)));
		}
		if ((g >= r) && (g >= b)) {
			hue = (int)Math.round(60*(2+((b-r)/(value-min))));
		}
		if ((b >= r) && (b >= g)) {
			hue = (int)Math.round(60*(4+((r-g)/(value-min))));
		}		
		if (hue < 0) {
			hue = hue+360;
		}
		return new HsvColor(hue, saturation*100, value*100); 
	}
	
	public Color toColor() {
		int h1 = (int)Math.floor((this.h/60.0));
		double f = (this.h/60.0)-h1;
		double v = this.v;
		double p = (v*(1-s))*255;
		double q = (v*(1-s*f))*255;
		double t = (v*(1-s*(1-f)))*255;
		v = v*255;
		switch (h1) {
		case 1: 
			return new Color((int)Math.round(q),(int)Math.round(v),(int)Math.round(p));
		case 2:
			return new Color((int)Math.round(p),(int)Math.round(v),(int)Math.round(t));
		case 3: 
			return new Color((int)Math.round(p),(int)Math.round(q),(int)Math.round(v));
		case 4:
			return new Color((int)Math.round(t),(int)Math.round(p),(int)Math.round(v));
		case 5: 
			return new Color((int)Math.round(v),(int)Math.round(p),(int)Math.round(q));
		default:
			return new Color((int)Math.round(v),(int)Math.round(t),(int)Math.round(p));
			
		}
	}

}

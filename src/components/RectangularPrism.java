package components;

import java.awt.Color;

/**
 * A rectangular prism
 * @author Benjamin Buck
 *
 */
public class RectangularPrism {
	Vector length;
	Vector width;
	Vector height;
	Point motion;
	double mass;
	
	Color color;
	
	/**
	 * Constructs a rectangular prism based on three vectors with one common point
	 * @param l A vector specifying the object's length
	 * @param w A vector specifying the object's width
	 * @param h A vector specifying the object's height
	 * @param v A vector specifying the object's initial velocity
	 * @param m Mass of the prism
	 * @param c The color of the rectangle
	 */
	public RectangularPrism(Vector l, Vector w, Vector h, Point v, double m, Color c){
		length = l;
		width = w;
		height = h;
		motion = v;
		mass = m;
		color = c;
	}
	
	/** Returns the volume of the prism*/
	public double volume(){
		return (length.length() * width.length() * height.length());
	}
	/** Returns the surface area of the prism*/
	public double surfaceArea(){
		double t_x = length.length();
		double t_y = width.length();
		double t_z = height.length();
		
		return (2*t_x*t_y + 2*t_x*t_z + 2*t_y*t_z);
	}
}

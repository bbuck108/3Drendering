package components;

import java.awt.Color;

public class RectangularPrism {
	Point v1;
	Point v2;
	Point motion;
	double mass;
	
	Color color;
	
	/**
	 * Constructs a rectangular prism based on two points
	 * @param p_1 One corner of the prism
	 * @param p_2 A corner completely opposite of the first
	 * @param p_3 A vector specifying the object's initial velocity
	 * @param p_4 Mass of the prism
	 * @param p_5 The color of the rectangle
	 */
	public RectangularPrism(Point p_1, Point p_2, Point p_3, double p_4,Color p_5){
		v1 = p_1;
		v2 = p_2;
		motion = p_3;
		mass = p_4;
		color = p_5;
	}
	
	/** Returns the volume of the prism*/
	public double volume(){
		double t_x = Math.abs(v1.getX()-v2.getX());
		double t_y = Math.abs(v1.getY()-v2.getY());
		double t_z = Math.abs(v1.getZ()-v2.getZ());
		
		return (t_x * t_y * t_z);
	}
	/** Returns the surface area of the prism*/
	public double surfaceArea(){
		double t_x = Math.abs(v1.getX()-v2.getX());
		double t_y = Math.abs(v1.getY()-v2.getY());
		double t_z = Math.abs(v1.getZ()-v2.getZ());
		
		return (2*t_x*t_y + 2*t_x*t_z + 2*t_y*t_z);
	}
}

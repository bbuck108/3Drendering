package components;

import java.awt.Color;

import util.Axis;
import util.Position;

/**
 * A rectangular prism
 * @author Benjamin Buck
 *
 */
public class RectangularPrism {
	Line length;
	Line width;
	Line height;
	Vector motion;
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
	public RectangularPrism(Line l, Line w, Line h, Vector v, double m, Color c){
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
	public Line getSide(Position p, Axis a){
		if(p == Position.FRONT){
			if(a == Axis.X){
				return length;
			}
			if(a == Axis.Y){
				return width;
			}
			if(a == Axis.Z){
				return height;
			}
		}
		if(p == Position.BACK){
			if(a == Axis.X){
				return length.translate(width.direction().addWith(height.direction()));
			}
			if(a == Axis.Y){
				return width.translate(length.direction().addWith(height.direction()));
			}
			if(a == Axis.Z){
				return height.translate(length.direction().addWith(height.direction()));
			}
		}
		if(p == Position.RIGHT) {
			if(a == Axis.X){
				return length.translate(height.direction());
			}
			if(a == Axis.Y){
				return width.translate(length.direction());
			}
			if(a == Axis.Z){
				return height.translate(length.direction());
			}
		}
		if(p == Position.LEFT) {
			if(a == Axis.X){
				return length.translate(width.direction());
			}
			if(a == Axis.Y){
				return width.translate(height.direction());
			}
			if(a == Axis.Z){
				return height.translate(width.direction());
			}
		}
		System.err.println("Invalid index");
		return null;
	}
	public boolean isPointInside(Vector p){
		
		return false;
	}
}

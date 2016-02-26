package components;

import java.awt.Color;

import main.Start;
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
	public RectangularPrism(Line l, Line w, Line h, Vector v, double m, Color c) {
		length = l;
		width = w;
		height = h;
		motion = v;
		mass = m;
		color = c;
		Start.addToRenderList(this);
	}
	
	public RectangularPrism(Vector pos, double size, Vector v, double m, Color c) {
		this(new Line(pos, pos.addWith(Vector.createFromRectangular(size, 0, 0))),new Line(pos, pos.addWith(Vector.createFromRectangular(0, size, 0))),new Line(pos, pos.addWith(Vector.createFromRectangular(0, 0, size))),v,m,c);
	}
	
	/** Returns the volume of the prism*/
	public double volume() {
		return (length.length() * width.length() * height.length());
	}
	/** Returns the surface area of the prism*/
	public double surfaceArea() {
		double t_x = length.length();
		double t_y = width.length();
		double t_z = height.length();
		
		return (2*t_x*t_y + 2*t_x*t_z + 2*t_y*t_z);
	}
	/**Returns the specified side of the prism*/
	public Line getSide(Position p, Axis a) {
		if(p == Position.FRONT) {
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
		if(p == Position.BACK) {
			if(a == Axis.X){
				return length.translateBy(width.direction().addWith(height.direction()));
			}
			if(a == Axis.Y){
				return width.translateBy(length.direction().addWith(height.direction()));
			}
			if(a == Axis.Z){
				return height.translateBy(length.direction().addWith(height.direction()));
			}
		}
		if(p == Position.RIGHT) {
			if(a == Axis.X){
				return length.translateBy(height.direction());
			}
			if(a == Axis.Y){
				return width.translateBy(length.direction());
			}
			if(a == Axis.Z){
				return height.translateBy(length.direction());
			}
		}
		if(p == Position.LEFT) {
			if(a == Axis.X){
				return length.translateBy(width.direction());
			}
			if(a == Axis.Y){
				return width.translateBy(height.direction());
			}
			if(a == Axis.Z){
				return height.translateBy(width.direction());
			}
		}
		System.err.println("Invalid index");
		return null;
	}
	public boolean isPointInside(Vector p) {
		//Pretty sure that this code is 100% flawed....
		boolean cx = false;
		boolean cy = false;
		boolean cz = false;
		
		//X component
		Plane x1 = 	new Plane(getSide(Position.FRONT, Axis.Y).direction().crossProduct(getSide(Position.FRONT, Axis.Z).direction()), getSide(Position.FRONT, Axis.Y).getStart());
		Plane x2 = 	new Plane(getSide(Position.BACK, Axis.Y).direction().crossProduct(getSide(Position.BACK, Axis.Z).direction()), getSide(Position.BACK, Axis.Y).getStart());
		if(!x1.compare(p).equals(x2.compare(p))) cx = true;
		
		//Y component
		Plane y1 = 	new Plane(getSide(Position.FRONT, Axis.X).direction().crossProduct(getSide(Position.FRONT, Axis.Z).direction()), getSide(Position.FRONT, Axis.X).getStart());
		Plane y2 = 	new Plane(getSide(Position.BACK, Axis.X).direction().crossProduct(getSide(Position.BACK, Axis.Z).direction()), getSide(Position.BACK, Axis.X).getStart());
		if(!y1.compare(p).equals(y2.compare(p))) cy = true;
		
		//Z component
		Plane z1 = 	new Plane(getSide(Position.FRONT, Axis.X).direction().crossProduct(getSide(Position.FRONT, Axis.Y).direction()), getSide(Position.FRONT, Axis.X).getStart());
		Plane z2 = 	new Plane(getSide(Position.BACK, Axis.X).direction().crossProduct(getSide(Position.BACK, Axis.Y).direction()), getSide(Position.BACK, Axis.X).getStart());
		if(!z1.compare(p).equals(z2.compare(p))) cz = true;
		
		return (cx && cy && cz);
	}
	public Color getColor() {
		return color;
	}
}

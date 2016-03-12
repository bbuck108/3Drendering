package components;

import java.awt.Color;

/*
 * @Authi
 */	
public class PhysicalObject {
	//-------------------Fields-------------//
	/** the velocity of the object */
	private Vector velocity;
	private double mass;
	private Color color;
	/** This must contain an extended Class of Shape */
	private Shape shape;
	
	//--------Constructors-------------//
	/** Default Constructor (sets mass to 1) */
	public PhysicalObject() {
		mass = 1;
	}
	
	/** Sets the shape and sets mass to 1 */
	public PhysicalObject(Shape s) {
		shape = s;
		mass = 1;
	}
	
	public PhysicalObject(Vector motion, double mass, Color c, Shape s) {
		this.velocity = motion;
		this.mass = mass;
		color = c;
		shape = s;
	}
	
	/** Redefines the location of the shape within physical object */
	public void move() {
		shape = shape.translateBy(velocity);
	}
	
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public double getMass() {
		return mass;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Shape getShape() {
		return shape;
	}

}

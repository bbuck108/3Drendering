package components;

import java.awt.Color;

public class PhysicalObject {
	Vector motion;
	double mass;
	Color color;
	
	public PhysicalObject() {}
	
	public PhysicalObject(PhysicalObject p) {
		motion = p.motion;
		mass = p.mass;
		color = p.color;
	}
	
	public void move() {
		translateBy(motion);
	}
	
	public PhysicalObject translateBy(Vector v) { 
		return new PhysicalObject();
	}
	
	public double isIntersecting(Ray ray) {
		// -1 implies no intersection
		return -1;
	}
	
	public Vector getSurfaceNormal(Vector v) {
		return (new Vector());
	}
	
	public Color getColor() {
		return color;
	}
	
	public Vector getMotion() {
		return motion;
	}
}

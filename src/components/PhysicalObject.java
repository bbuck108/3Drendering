package components;

import java.awt.Color;

public abstract class PhysicalObject {
	Vector motion;
	double mass;
	Color color;
	
	public void move() {
		editTo(translateBy(motion));
	}
	
	//Javadoc Here!!!!!!
	/** */
	public abstract PhysicalObject translateBy(Vector v);
	
	public abstract double isIntersecting(Ray ray);
	
	public abstract Vector getSurfaceNormal(Vector v);
	
	public Color getColor() {
		return color;
	}
	
	public Vector getMotion() {
		return motion;
	}
	
	//Get rid of this
	public abstract void editTo(PhysicalObject p);
}

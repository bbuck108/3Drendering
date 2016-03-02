package components;

import java.awt.Color;

public abstract class PhysicalObject {
	Vector motion;
	double mass;
	Color color;
	public void move(){
		this.translateBy(motion);
	}
	public void translateBy(Vector p) {
		
	}
	public double isIntersecting(Ray ray) {
		// -1 implies no intersection
		return -1;
	}
	public Vector getSurfaceNormal(Vector p){
		return (new Vector());
	}
	public Color getColor() {
		return color;
	}
	public Vector getMotion() {
		return motion;
	}
}

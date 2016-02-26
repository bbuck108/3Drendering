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
	public boolean isIntersecting(Vector check) {
		// TODO Auto-generated method stub
		return false;
	}
	public Color getColor() {
		return color;
	}
	public Vector getMotion() {
		return motion;
	}
}

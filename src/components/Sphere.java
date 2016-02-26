package components;

import java.awt.Color;

public class Sphere extends PhysicalObject{
	Vector center;
	double radius;
	
	public Sphere(Vector c, double r, Vector v, double m, Color color){
		center = c;
		radius = r;
		motion = v;
		mass = m;
		this.color = color;
	}
	public void translateBy(Vector p){
		center = center.addWith(p);
	}
	public boolean isIntersecting(Vector p){
		return false;
		
	}
}

package components;

import java.awt.Color;

import main.Start;
import util.Util;

public class Sphere extends PhysicalObject{
	Vector center;
	double radius;
	
	public Sphere(Vector c, double r, Vector v, double m, Color color){
		center = c;
		radius = r;
		motion = v;
		mass = m;
		this.color = color;
		Start.addToRenderList(this);
	}
	public void translateBy(Vector p){
		center = center.plus(p);
	}
	public Vector getSurfaceNormal(Vector p){
		return (new Segment(center, p)).direction();
	}
	public double isIntersecting(Ray ray){
		Vector EO = (new Segment(ray.origin, center)).direction();
		Vector V  = Vector.createFromSpherical(EO.norm(), ray.theta, ray.phi);
		double v = EO.dot(V);
		double disc = Util.sq(radius) - (Util.sq(EO.norm())-v);
		double r;
		//System.out.println(disc);
		if(disc < 0){
			r = -1; //-1 implies no intersection
		}
		else{
			r = v - Math.sqrt(disc);
		}
		return r;
	}
}

package components;

import util.Util;

public class Sphere extends Shape{
	double radius;
	
	public Sphere(Vector c, double r){
		super(c);
		radius = r;
	}
	
	@Override
	public double isIntersecting(Ray ray) {
		Vector EO = (new Segment(ray.origin, location)).direction();
		Vector V  = Vector.createFromSpherical(EO.norm(), ray.theta, ray.phi);
		double v = EO.dot(V);
		double disc = Util.sq(radius) - (Util.sq(EO.norm())-v);
		double r;
		//System.out.println(disc);
		if(disc < 0) {
			r = -1; //-1 implies no intersection
		}
		else{
			r = Math.sqrt(v) - Math.sqrt(disc);
		}
		return r;
	}
	
	@Override
	public Vector getSurfaceNormal(Vector p) {
		return (new Segment(location, p)).direction();
	}
	
	@Override
	public Sphere translateBy(Vector p) {
		return new Sphere(location.plus(p), radius);
	}	
}
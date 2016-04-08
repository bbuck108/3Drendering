package components;

import org.json.JSONObject;

import util.Util;

/** Defines a sphere in 3D space.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class Sphere extends Shape{
	final double radius;
	
	public Sphere() {
		super();
		radius = 0;
	}
	
	public Sphere(Vector c, double r) {
		super(c, new Rotation(0,0,0));
		radius = r;
	}
	
	public Sphere(Vector c, double r, Rotation rotation) {
		super(c, rotation);
		radius = r;
	}
	
	public Sphere(JSONObject jsonObject) {
		super(Vector.createFromRectangular(
				jsonObject.getJSONArray("location").getDouble(0),
				jsonObject.getJSONArray("location").getDouble(1),
				jsonObject.getJSONArray("location").getDouble(2)),
				new Rotation(0,0,0));
		radius = jsonObject.getDouble("radius");
	}

	@Override
	public double isIntersecting(Ray ray) {
		Vector EO = (new Segment(ray.origin, location)).direction();
		Vector V  = Vector.createFromSpherical(EO.norm(), ray.theta, ray.phi);
		double v = EO.dot(V);
		double disc = Util.sq(radius) - (Util.sq(EO.norm())-v);
		double r;
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
	public Shape translateBy(Vector p) {
		return new Sphere(location.plus(p), radius);
	}

	@Override
	public Shape rotateBy(Rotation r) {
		return new Sphere(location, radius, rotation.plus(r));
	}	
}
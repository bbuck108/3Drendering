package shape;

import org.json.JSONObject;

import component.Ray;
import component.Rotation;
import component.Segment;
import component.Vector;
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
		Vector EO = (new Segment(ray.getOrigin(), getLocation())).direction();
		Vector V  = Vector.createFromSpherical(EO.norm(), ray.getTheta(), ray.getPhi());
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
	public double[] getTexturingPoint(Vector v) {
		return new double[]{0, 0};
	}
	
	@Override
	public Vector getSurfaceNormal(Vector p) {
		return (new Segment(getLocation(), p)).direction();
	}
	
	@Override
	public Shape translateBy(Vector p) {
		return new Sphere(getLocation().plus(p), radius);
	}

	@Override
	public Shape rotateBy(Rotation r) {
		return new Sphere(getLocation(), radius, rotation.plus(r));
	}	
}
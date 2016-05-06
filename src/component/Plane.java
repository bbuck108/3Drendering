package component;

import util.Inequality;

/** Defines a plane 3D space
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class Plane {
	double a;
	double b;
	double c;
	double d;
	
	/**Constructs a plane from a normal vector and a point on the plane*/
	public Plane(Vector normal, Vector point) {
		a = normal.x;
		b = normal.y;
		c = normal.z;
		d = -1 * normal.dot(point);
	}
	
	public Inequality compare(Vector point) {
		double t_1 = a * point.x + b * point.y + c * point.z + d;
		if(t_1 == 0) return Inequality.EQUAL;
		if(t_1 <= 0) return Inequality.LESS;
		if(t_1 >= 0) return Inequality.GREATER;
		
		System.err.println("Invalid: "+t_1);
		return null;
	}
	public double compareDouble(Vector point) {
		return a * point.x + b * point.y + c * point.z + d;
	}
	public Vector intersection(Ray ray){
		Vector n = Vector.createFromRectangular(a, b, c);
		Vector v = ray.getV();
		double t = -1*(ray.getOrigin().dot(n)+d)/(v.dot(n));
		return ray.getOrigin().plus(v.scaleBy(t));
	}
	public Vector getNormalVector(){
		return Vector.createFromRectangular(a, b, c);
	}
}

package component;

import util.Inequality;

/** Defines a plane 3D space
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class Plane {
	private double a;
	private double b;
	private double c;
	private double d;
	
	/**Constructs a plane from a normal vector and a point on the plane*/
	public Plane(Vector normal, Vector point) {
		a = normal.x;
		b = normal.y;
		setC(normal.z);
		setD(-1 * normal.dot(point));
	}
	
	public Inequality compare(Vector point) {
		double t_1 = a * point.x + b * point.y + getC() * point.z + getD();
		if(t_1 == 0) return Inequality.EQUAL;
		if(t_1 <= 0) return Inequality.LESS;
		if(t_1 >= 0) return Inequality.GREATER;
		
		System.err.println("Invalid: "+t_1);
		return null;
	}
	public double compareDouble(Vector point) {
		return a * point.x + b * point.y + getC() * point.z + getD();
	}
	public Vector intersection(Ray ray){
		Vector n = Vector.createFromRectangular(a, b, getC());
		Vector v = ray.getV();
		double t = -1*(ray.getOrigin().dot(n)+getD())/(v.dot(n));
		return ray.getOrigin().plus(v.scaleBy(t));
	}
	public Vector getNormalVector(){
		return Vector.createFromRectangular(a, b, c);
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}
}

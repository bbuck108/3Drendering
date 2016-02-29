package components;

import util.Inequality;

/** 
 * Defines a plane in 3D space
 * @author Benjamin Buck
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
		d = -1 * (a * point.x + b * point.y + c * point.z);
	}
	
	public Inequality compare(Vector point) {
		double t_1 = a * point.x + b * point.y + c * point.z + d;
		if(t_1 == 0) return Inequality.EQUAL;
		if(t_1 <= 0) return Inequality.LESS;
		if(t_1 >= 0) return Inequality.GREATER;
		
		System.err.println("Invalid");
		return null;
	}
}

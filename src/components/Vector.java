package components;

import static util.Util.*;

/** 
 *A three dimensional point 
 * @author Connor Lehmacher
 */
public class Vector {
	//----------Fields----------//
	// Basic Information   
	public double x;
	public double y;
	public double z;
	
	//------------Constructors------------//
	protected Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/** Default; 0 */
	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	//-----------Pseudo-Constructors------------//
	public static Vector createFromRectangular(double x, double y, double z) {
		return new Vector(x, y, z);
	}
	
	/** Returns a vector based on a vertical cylindrical system (radians) */
	public static Vector createFromCylindrical(double r, double theta, double z) {
		final double x = r * Math.cos(theta);
		final double y = r * Math.sin(theta);
		return new Vector(x, y, z);
	}
	
	/** Returns a vector based on spherical coordinates
	 * @param theta on x-y plane (radians)
	 * @param phi from z axis and down */
	public static Vector createFromSpherical(double r, double theta, double phi) {
		final double x = r * Math.sin(phi) * Math.cos(theta);
		final double y = r * Math.sin(phi) * Math.sin(theta);
		final double z = r * Math.cos(phi);
		return new Vector(x, y, z);
	}
	
	//------------------Methods----------------------//
	/** Finds the length of the vector (from origin) */
	public double norm() {
		return Math.sqrt(sq(x) + sq(y) + sq(z));
	}
	
	/** finds the angle of the vector on the x-y plane from 0 to 2 PI
	 * not that a zero vector will have angle 0 */
	public double theta() {
		return Math.atan2(y, x) + Math.PI;
	}
	
	public double phi() {
		return Math.atan2(Math.sqrt(sq(x) + sq(y)), z);
	}
	
	/** Add one point to another */
	public Vector plus(Vector v) {
		return createFromRectangular(x + v.x, y + v.y, z + v.z);
	}
	
	/** Finds the Point which is in the same line but negatively with the same norm value */
	public Vector negative() {
		return createFromRectangular(-x, -y, -z);
	}
	
	/** Subtracts two points with each other this - v */
	public Vector minus(Vector v) {
		return plus(v.negative());
	}
	
	/** Computes some norm increase by a scalar */
	public Vector scaleBy(double a) {
		return createFromRectangular(x * a, y * a, z * a);
	}
	
	/** Rotates the vector by the angle of another vector */
	public Vector rotateBy(Vector v) {
		return createFromSpherical(norm(), theta() + v.theta(), phi() + v.phi());
	}
	
	/** computes the dot product (inner product type) with another vector */
	public double dot(Vector v) {
		return x * v.x + y * v.y + z * v.z; 
	}
	
	/** computes the cross product with another vector
	 * oder of cross product -> this x v */
	public Vector cross(Vector v) {
		final double i = y * v.z - v.y * z;
		final double j = z * v.x - v.z * x;
		final double k = x * v.y - v.x * y;
		return createFromRectangular(i, j, k);
	}

	/** computes the distance between two vectors */
	public double distanceWith(Vector v) {
		return Math.sqrt(sq(x - v.x) + sq(y - v.y) + sq(z - v.z));
	}
	
	/** computes angle with some other vector radians */
	public double angleWith(Vector v) {
		return Math.acos(dot(v) / norm() / v.norm());
	}
	
	/**Gives a unit length vector*/
	public Vector toUnit() {
		return Vector.createFromSpherical(1, theta(), phi());
	}
	
	/** Print the vector as a concatenation of its x y and z parts
	 * and adds a new line */
	public void println() {
		System.out.println(x + " " + y + " " + z);
	}
}
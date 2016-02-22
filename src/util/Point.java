package util;

import static util.Util.sq;

public class Point {
	//----------Fields----------//
	// Basic Information   
	private double x;
	private double y;
	private double z;
	
	//------------Constructors------------//
	private Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/** Default; 0 */
	public Point() {}
	
	//-----------Pseudo-Constructors------------//
	public static Point createFromRectangular(double x, double y, double z) {
		return new Point(x, y, z);
	}
	
	/** Returns a vector based on a vertical cylindrical system (radians) */
	public static Point createFromCylindrical(double r, double theta, double z) {
		final double x = r * Math.cos(theta);
		final double y = r * Math.sin(theta);
		return new Point(x, y, z);
	}
	
	/** Returns a vector based on spherical coordinates
	 * @param theta on x-y plane (radians)
	 * @param phi on z axis and vector it self */
	public static Point createFromSpherical(double r, double theta, double phi) {
		final double x = r * Math.cos(phi) * Math.cos(theta);
		final double y = r * Math.cos(phi) * Math.sin(theta);
		final double z = r * Math.sin(phi);
		return new Point(x, y, z);
	}
	
	//------------------Methods----------------------//
	/** Finds the length of the vector (from origin) */
	public double norm() {
		return Math.sqrt(sq(x) + sq(y) + sq(z));
	}
	
	public double theta() {
		//TODO actually do this
		return 1.0;
	}
	
	/** computes the dot product (inner product type) with another vector */
	public double dotProduct(Point v) {
		return x * v.x + y * v.y + z * v.z; 
	}
	
	/** computes the cross product with another vector
	 * oder of cross product -> this x v*/
	public Point crossProduct(Point v) {
		final double i = y * v.z - v.y * z;
		final double j = z * v.x - v.z * x;
		final double k = x * v.y - v.x * y;
		return createFromRectangular(i, j, k);
	}
	
	//------------Getter-Methods-----//
	public double getX() {return x;}
	public double getY() {return y;}
	public double getZ() {return z;}
}

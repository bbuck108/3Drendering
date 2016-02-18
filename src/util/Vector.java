package util;

public class Vector {
	//----------Fields----------//
	// Basic Information   
	private double x;
	private double y;
	private double z;
	
	//------------Constructors------------//
	private Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/** Default; 0 */
	public Vector() {}
	
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
	 * @param phi on z axis and vector it self */
	public static Vector createFromSpherical(double r, double theta, double phi) {
		final double x = r * Math.cos(phi) * Math.cos(theta);
		final double y = r * Math.cos(phi) * Math.sin(theta);
		final double z = r * Math.sin(phi);
		return new Vector(x, y, z);
	}
	
	//------------Getter-Methods-----//
	public double getX() {return x;}
	public double getY() {return y;}
	public double getZ() {return z;}
}

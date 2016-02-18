package util;

public class Vector {
	//----------Fields----------//
	// Basic Information   
	public double x;
	public double y;
	public double z;
	
	//------------Constructors------------//
	private Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/** Default; 0 */
	public Vector() {}
	
	//-----------Methods------------//
	public static Vector createFromRectangular(double x, double y, double z) {
		return new Vector(x, y, z);
	}
	
	public static Vector createFromCylindrical() {
		return new Vector();
	}
	
	public static Vector createFromSpherical() {
		return new Vector();
	}
}

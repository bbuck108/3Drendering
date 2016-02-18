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
	
	//-----------Puesdo-Constructors------------//
	public static Vector createFromRectangular(double x, double y, double z) {
		return new Vector(x, y, z);
	}
	
	public static Vector createFromCylindrical(double r, double theta, double z) {
		
		return new Vector();
	}
	
	public static Vector createFromSpherical() {
		return new Vector();
	}
	
	//------------Getter-Methods-----//
	public double getX() {return x;}
	public double getY() {return y;}
	public double getZ() {return z;}
}

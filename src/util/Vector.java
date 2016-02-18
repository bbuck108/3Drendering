package util;

public class Vector {
	//----------Fields----------//
	// Ze Basic Information   
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
	public static Vector createFromRect(double x, double y, double z) {
		return new Vector(x, y, z);
	}
}

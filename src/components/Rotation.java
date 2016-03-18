package components;

import util.Util;

/**
 * A vector which contains three angles in radians about the axis
 * @author Benjamin Buck and Connor Lehmacher
 * used to rotate complex shapes when a theta and phi is not complete
 * x rotation is about the x-axis (in y,z plane) starting from the positive z-axis going counterclockwise
 * y rotation is about the y-axis (in x,z plane) starting from the positive z-axis going counterclockwise
 * z rotation is about the z-axis (in x,y plane) starting from the positive y-axis going counterclockwise
 */
public class Rotation extends Vector{
	public Rotation() {}
	
	public Rotation(double x, double y, double z) {
		super(x, y, z);
	}
	
	public Rotation(Vector v){
		this(v.x, v.y, v.z);
	}
	
	
	public Rotation plus(Rotation r) {
		return Util.reduceRotation(new Rotation(super.plus((Vector)r)));
	}
	
	public Rotation negative() {
		return new Rotation(TAU - x, TAU - y, TAU - z);
	}
	
	final static double TAU = 2 * Math.PI;
}

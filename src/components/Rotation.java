package components;

import util.Util;

/**
 * A vector which contains three angles in radians about the axis
 * @author Connor Lehmacher
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
}

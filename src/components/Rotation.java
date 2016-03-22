package components;

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
		return (new Rotation(super.plus((Vector)r))).reduce();
	}
	
	
	
	
	
	
	
	
	
	

    /** returns a Rotation from a rotation which may be outside the standard range
     * @return a rotation in the range of 0 to 2 Pi */
	public Rotation reduce() {
		double x = this.x;
		double y = this.y;
		double z = this.z;
		if(x < 0 || y < 0 || z < 0) {
			x += TAU;
			y += TAU;
			z += TAU;
			reduce();
		}
		x %= TAU;
		y %= TAU;
		z %= TAU;
		return new Rotation(x, y, z);
	}
	
	public Rotation negative() {
		return new Rotation(TAU - x, TAU - y, TAU - z);
	}
	
	final static double TAU = 2 * Math.PI;
}

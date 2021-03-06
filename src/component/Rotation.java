package component;

import static java.lang.Math.sin;
import static java.lang.Math.cos;

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
	
	/** computes the Rotation Matrix over the x axis */
	public Matrix xRotationMatrix() {
		Matrix value = new Matrix(3, 3);
		value.set(0, 0, 1);
		value.set(0, 1, 0);
		value.set(0, 2, 0);
		value.set(1, 0, 0);
		value.set(1, 1, cos(x));
		value.set(1, 2, -sin(x));
		value.set(2, 0, 0);
		value.set(2, 1, sin(x));
		value.set(2, 2, cos(x));
		return value;
	}
	
	/** computes the Rotation Matrix over the y axis */
	public Matrix yRotationMatrix() {
		Matrix value = new Matrix(3, 3);
		value.set(0, 0, cos(y));
		value.set(0, 1, 0);
		value.set(0, 2, sin(y));
		value.set(1, 0, 0);
		value.set(1, 1, 1);
		value.set(1, 2, 0);
		value.set(2, 0, -sin(y));
		value.set(2, 1, 0);
		value.set(2, 2, cos(y));
		return value;
	}
	
	/** computes the Rotation Matrix over the Z axis */
	public Matrix zRotationMatrix() {
		Matrix value = new Matrix(3, 3);
		value.set(0, 0, cos(z));
		value.set(0, 1, -sin(z));
		value.set(0, 2, 0);
		value.set(1, 0, sin(z));
		value.set(1, 1, cos(z));
		value.set(1, 2, 0);
		value.set(2, 0, 0);
		value.set(2, 1, 0);
		value.set(2, 2, 1);
		return value;
	}
	
	/** computes the full rotation matrix in the order x,y,z
	 * faster ??? */
	public Matrix fullRotationMatrix() {
		double sx = sin(x), cx = cos(x), sy = sin(y), cy = cos(y), sz = sin(z), cz = cos(z);
		Matrix value = new Matrix(3, 3);
		value.set(0, 0, cy * cz);
		value.set(0, 1, -cy * sz);
		value.set(0, 2, sy);
		value.set(1, 0, cx * sz + sx * sy * cz);
		value.set(1, 1, cx * cz - sx * sy * sz);
		value.set(1, 2, -sx * cy);
		value.set(2, 0, sx * sz - cx * sy * cz);
		value.set(2, 1, cx * sy * sz + sx * cz);
		value.set(2, 2, cx * cy);
		return value;
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

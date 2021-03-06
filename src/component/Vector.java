package component;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static util.Util.sq;

/** 
 *A three dimensional point 
 * @author Benjamin Buck and Connor Lehmacher
 */
public class Vector {
	//----------Fields----------//
	// Basic Information   
	public final double x;
	public final double y;
	public final double z;
	
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
	
	/** Creates a matrix from a 3 by 1 matrix */
	public Vector(Matrix m) {
		if(m.rows() == 3 && m.columns() == 1) {
			x = m.get(0, 0);
			y = m.get(1, 0);
			z = m.get(2, 0);
		}
		else{
			System.err.println("Bad Matrix");
			x = 0;
			y = 0;
			z = 0;
		}
	}
	
	//-----------Pseudo-Constructors------------//
	public static Vector createFromRectangular(double x, double y, double z) {
		return new Vector(x, y, z);
	}
	
	/** Returns a vector based on a vertical cylindrical system (radians) */
	public static Vector createFromCylindrical(double r, double theta, double z) {
		final double x = r * cos(theta);
		final double y = r * sin(theta);
		return new Vector(x, y, z);
	}
	
	/** Returns a vector based on spherical coordinates
	 * @param theta on x-y plane (radians)
	 * @param phi from z axis and down */
	public static Vector createFromSpherical(double r, double theta, double phi) {
		final double rsp = r * sin(phi);
		final double x = rsp * cos(theta);
		final double y = rsp * sin(theta);
		final double z = r * cos(phi);
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
		double answer = Math.atan2(y, x);
		if(answer < 0) {
			answer += 2 * Math.PI;
		}
		return answer;
	}
	
	public double phi() {
		return Math.atan2(Math.sqrt(sq(x) + sq(y)), z);
	}
	
	/** Add one point to another */
	public Vector plus(Vector v) {
		return new Vector(x + v.x, y + v.y, z + v.z);
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
		return new Vector(x * a, y * a, z * a);
	}
	
	/** Rotates the vector by the angle of another vector */
	public Vector rotateBy(Vector v) {
		return createFromSpherical(norm(), theta() + v.theta(), phi() + v.phi());
	}
	
	/** Rotate the vector by a three dimension rotation */
	public Vector rotateBy2(Rotation r) {
		double sx = sin(r.x), cx = cos(r.x), sy = sin(r.y), cy = cos(r.y), sz = sin(r.z), cz = cos(r.z);
		return createFromRectangular(x * cy * cz - y * cy * sz + z * sy,
				x * (cx * sz + sx * sy * cz) + y * (cx * cz - sx * sy * sz) - z * (sx * cy),
				x * (sx * sz - cx * sy * cz) + y * (cx * sy * sz + sx * cz) + z * (cx * cy));
		//Old code still works but slow
		//return new Vector(r.fullRotationMatrix().times(toMatrix()));
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
		return new Vector(i, j, k);
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
	
	/** Creates a 3 x 1 matrix from the vector */
	public Matrix toMatrix(){
		Matrix value = new Matrix(3, 1);
		value.set(0, 0, x);
		value.set(1, 0, y);
		value.set(2, 0, z);
		return value;
	}
	
	/** Print the vector as a concatenation of its x y and z parts
	 * and adds a new line */
	public void println() {
		System.out.println(x + " " + y + " " + z);
	}
	
	/** Returns true if all three components of the vector are finite*/
	public boolean isFinite(){
		return ((Double.isFinite(x))&&(Double.isFinite(y))&&(Double.isFinite(z)));
	}
}
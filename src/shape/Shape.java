package shape;

import component.Ray;
import component.Rotation;
import component.Vector;

/**
 * Some 3 dimensional shape for example cube, sphere, rectangular prism etc.
 * @author Benjamin Buck and Connor Lehmacher
 */
public abstract class Shape {
	/** The location of a shape <b> must </b> be at the center of mass for uniform density */
	private final Vector location;
	protected final Rotation rotation;
	
 	/** Default constructor center at 0, 0, 0
	 * is important for Physical Object functionality*/
	public Shape() {
		location = new Vector();
		rotation = new Rotation();
	}
	
	/** Creates a shape with a center at c and a rotation r*/
	public Shape(Vector c, Rotation r) {
		location = c;
		rotation = r;
	}
	
	/** Computes the length along a ray where it intersects with this shape */
	public abstract double isIntersecting(Ray ray);
	
	/** Computes a perpendicular to the edge of the shape by a point which must intersect with the place approximately */
	public abstract Vector getSurfaceNormal(Vector v);
	
	/** Computes two numbers from 0 to 99 which is a relative point on the surface of the shape and index for which one to use
	 * used for texturing; should almost intersect the shape
	 * must be in form {index, xPoint, yPoint} <b> very important </b> */
	public abstract int[] getTexturingPoint(Vector v);	
	
	/** Moves the center of a shape by some vector amount v */
	public abstract Shape translateBy(Vector v);
	
	/**Rotates the object by some Rotation r*/
	public abstract Shape rotateBy(Rotation r);

	public Vector getLocation() {
		return location;
	}
}

package components;

/**
 * Some 3 dimensional shape for example cube, sphere, rectangular prism etc.
 * @author Connor Lehmacher and Benjamin Buck
 */
public abstract class Shape {
	/** The location of a shape <b> must </b> be at the center of mass for uniform density */
	final Vector location;
	
	/** Default constructor center at 0, 0, 0
	 * is important for Physical Object functionality*/
	public Shape() {
		location = new Vector();
	}
	
	/** Creates a shape with a center at c */
	public Shape(Vector c) {
		location = c;
	}
	
	/** Computes the length along a ray where it intersects with this shape */
	public abstract double isIntersecting(Ray ray);
	
	/** Computes a perpendicular to the edge of the shape by a point which must intersect with the place */
	public abstract Vector getSurfaceNormal(Vector v);
	
	/** Moves the center of a shape by some vector amount v */
	public abstract Shape translateBy(Vector v);
}

package components;

public abstract class Shape implements Cloneable {
	/** The location of a shape <b> must </b> be at the center of mass for uniform density */
	Vector location;
	
	/** Default constructor center at 0, 0, 0
	 * is important for Physical Object functionality*/
	public Shape() {}
	
	/** Creates a shape with a center at c */
	public Shape(Vector c) {
		location = c;
	}
	
	/** Computes the length along a ray where it intersects with this shape */
	public abstract double isIntersecting(Ray ray);
	
	/** Computes a perpendicular to the edge of the shape by a point which must intersect with the place */
	public abstract Vector getSurfaceNormal(Vector p);
	
	/** Moves the center of a shape by some vector amount v */
	public Shape translateBy(Vector v){
		try {
			Shape obj = (Shape) this.clone();
			obj.location = this.location.plus(v);
			return obj;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	};
}

package components;

/**
 * A line with a direction
 * @author Connor Lehmacher
 */
public class Segment {
	//-----------------Fields----------------//
	/** Initial location */
	private Vector start;
	/** Final location, defines direction */
	private Vector end;
	
	//---------------Constructors-------------//
	/** From 0, 0, 0 to 0, 0, 0 */
	public Segment() {
		start = new Vector();
		end = new Vector();
	}
	
	/** Creates a Line based on two points
	 * @param s the start of the vector
	 * @param e the end of the vector */
	public Segment(Vector s, Vector e) {
		start = s;
		end = e;
	}
	
	/** Creates a Line based on 6 numbers */
	public Segment(double sx, double sy, double sz, double ex, double ey, double ez) {
		start = Vector.createFromRectangular(sx, sy, sz);
		end = Vector.createFromRectangular(ex, ey, ez);
	}
	
	/** Creates a Line based on a point and a length and a direction
	 * @param l the length */
	public Segment(Vector p, double l, double theta, double phi) {
		start = p;
		end = Vector.createFromSpherical(l, theta, phi).plus(p);
	}
	
	//----------------Methods----------------//
	/** Computes the length from start to end */
	public double length() {
		return end.minus(start).norm();
	}
	
	/** Moves vector but does not change direction */
	public Segment translateBy(Vector v) {
		return new Segment(start.plus(v), end.plus(v));
	}
	
	/** Finds the direction of the line as a vector */
	public Vector direction() {
		return end.minus(start);
	}
	
	/** Returns true if two lines are parallel */
	public boolean isParallel(Segment l) {
		return direction().equals(l.direction());
	}
	
	/** Enlarges or shrinks a line by some amount */
	public Segment scaleBy(double a) {
		return new Segment(start.scaleBy(a), end.scaleBy(a));
	}
	
	//--------------Getter-Methods--------------//
	public Vector getStart() { return start; }
	public Vector getEnd() { return end; }
}

package component;

/**
 * A line with a direction
 * @author Benjamin Buck and Connor Lehmacher
 */
public class Segment {
	//-----------------Fields----------------//
	/** Initial location */
	final private Vector start;
	/** Final location, defines direction */
	final private Vector end;
	
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
	public Segment(Vector v, double l, double theta, double phi) {
		start = v;
		end = Vector.createFromSpherical(l, theta, phi).plus(v);
	}

	//-------------Pseudo-Constructors---------//
	/**Creates a segment based on a start location a direction (vector quantity) */
	public static Segment createFromDirection(Vector start, Vector direction) {
		return new Segment(start, start.plus(direction));
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
	
	/** Rotates a segment over the x, y, and z axis relative to the start */
	public Segment rotateBy(Rotation r) {
		return createFromDirection(start, direction().rotateBy2(r));
	}
	
	/** Finds the direction of the line as a vector */
	public Vector direction() {
		return end.minus(start);
	}
	
	/** Finds the rotation (3 Numbers) of a line */
	public Rotation rotation() {
		final double relX = direction().x;
		final double relY = direction().y;
		final double relZ = direction().z;
		final double xRot = Math.atan2(relZ, relY);
		final double yRot = Math.atan2(relX, relZ);
		final double zRot = Math.atan2(relY, relX);
		return new Rotation(xRot, yRot, zRot);
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
	public Vector getMidpoint() { return end.minus(start).scaleBy(0.5).plus(start);}
}

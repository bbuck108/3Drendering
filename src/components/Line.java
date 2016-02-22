package components;

/**
 * A line with a direction
 * @author Connor Lehmacher
 */
public class Line {
	//-----------------Fields----------------//
	/** Initial location */
	private Vector start;
	/** Final location, defines direction */
	private Vector end;
	
	//---------------Constructors-------------//
	/** From 0,0,0 to 0,0,0, */
	public Line() {
		start = new Vector();
		end = new Vector();
	}
	
	/** Creates a Vector based on two points	
	 * @param s the start of the vector
	 * @param e the end of the vector */
	public Line(Vector s, Vector e) {
		start = s;
		end = e;
	}
	
	/** Creates a Vector based on 6 numbers */
	public Line(double sx, double sy, double sz, double ex, double ey, double ez) {
		start = Vector.createFromRectangular(sx, sy, sz);
		end = Vector.createFromRectangular(ex, ey, ez);
	}
	
	/** Creates a Vector based on a point and a length and a direction
	 * @param l the length */
	public Line(Vector p, double l, double theta, double phi) {
		start = p;
		end = Vector.createFromSpherical(l, theta, phi).addWith(p);
	}
	
	//----------------Methods----------------//
	/** Computes the length from start to end */
	public double length() {
		return end.subtractWith(start).norm();
	}
	
	/** Moves vector but does not change direction */
	public Line translate(Vector p) {
		return new Line(start.addWith(p), end.addWith(p));
	}
	
	/** Finds the direction of the Vector as a point */
	public Vector direction() {
		return end.subtractWith(start);
	}
	
	//--------------Getter-Methods--------------//
	public Vector getStart() { return start; }
	public Vector getEnd() { return end; }
}

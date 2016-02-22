package components;

/**
 * A line with a direction
 * @author Connor Lehmacher
 */
public class Vector {
	//-----------------Fields----------------//
	/** Initial location */
	private Point start;
	/** Final location, defines direction */
	private Point end;
	
	//---------------Constructors-------------//
	/** From 0,0,0 to 0,0,0, */
	public Vector() {
		start = new Point();
		end = new Point();
	}
	
	/** Creates a Vector based on two points	
	 * @param s the start of the vector
	 * @param e the end of the vector */
	public Vector(Point s, Point e) {
		start = s;
		end = e;
	}
	
	/** Creates a Vector based on 6 numbers */
	public Vector(double sx, double sy, double sz, double ex, double ey, double ez) {
		start = Point.createFromRectangular(sx, sy, sz);
		end = Point.createFromRectangular(ex, ey, ez);
	}
	
	/** Creates a Vector based on a point and a length and a direction
	 * @param l the length */
	public Vector(Point p, double l, double theta, double phi) {
		start = p;
		end = Point.createFromSpherical(l, theta, phi).addWith(p);
	}
	
	//----------------Methods----------------//
	/** Computes the length from start to end */
	public double length() {
		return end.subtractWith(start).norm();
	}
	
	/** Moves vector but does not change direction */
	public Vector translate(Point p) {
		return new Vector(start.addWith(p), end.addWith(p));
	}
	
	/** Finds the direction of the Vector as a point */
	public Point direction() {
		return end.subtractWith(start);
	}
	
	//--------------Getter-Methods--------------//
	public Point getStart() { return start; }
	public Point getEnd() { return end; }
}

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
	
	public Vector(Point s, Point e) {
		start = s;
		end = e;
	}
	
	
	//--------------Getter-Methods--------------//
	public Point getStart() { return start; }
	public Point getEnd() { return end; }
}

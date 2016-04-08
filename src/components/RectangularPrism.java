package components;

import org.json.JSONObject;

import util.Axis;
import util.Position;

/** Defines a rectangular prism in 3D space.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class RectangularPrism extends Shape {
	Vector size;
	
	private Vector point_1;
	private Vector point_2;
	private Vector point_3;
	private Vector point_4;
	private Vector point_5;
	private Vector point_6;
	private Vector point_7;
	private Vector point_8;
	private boolean pointsDefined = false;
	
	private Segment side_FRONT_X;
	private Segment side_FRONT_Y;
	private Segment side_FRONT_Z;
	private Segment side_BACK_X;
	private Segment side_BACK_Y;
	private Segment side_BACK_Z;
	private Segment side_LEFT_X;
	private Segment side_LEFT_Y;
	private Segment side_LEFT_Z;
	private Segment side_RIGHT_X;
	private Segment side_RIGHT_Y;
	private Segment side_RIGHT_Z;
	private boolean sidesDefined = false;
	
	private Plane plane_FRONT_X;
	private Plane plane_FRONT_Y;
	private Plane plane_FRONT_Z;
	private Plane plane_BACK_X;
	private Plane plane_BACK_Y;
	private Plane plane_BACK_Z;
	private boolean planesDefined = false;
	
	public RectangularPrism(Vector c, Vector s, Rotation r) {
		super(c,r);
		size = s;
	}

	public RectangularPrism(JSONObject jsonObject) {
		super(Vector.createFromRectangular(
				jsonObject.getJSONArray("location").getDouble(0),
				jsonObject.getJSONArray("location").getDouble(1),
				jsonObject.getJSONArray("location").getDouble(2)),
				new Rotation(
						jsonObject.getJSONArray("rotation").getDouble(0),
						jsonObject.getJSONArray("rotation").getDouble(1),
						jsonObject.getJSONArray("rotation").getDouble(2)));
		size = Vector.createFromRectangular(
				jsonObject.getJSONArray("size").getDouble(0),
				jsonObject.getJSONArray("size").getDouble(1),
				jsonObject.getJSONArray("size").getDouble(2));
	}
	
	
	/** Returns the volume of the prism*/
	public double volume() {
		return (size.x * size.y * size.z);
	}
	
	/** Returns the surface area of the prism*/
	public double surfaceArea() {
		double t_x = size.x;
		double t_y = size.y;
		double t_z = size.z;
		
		return (2*t_x*t_y + 2*t_x*t_z + 2*t_y*t_z);
	}
	
	public Vector getPoint(int index){
		if(!pointsDefined) calculatePoints();
		if(index == 1) return point_1;
		if(index == 2) return point_2;
		if(index == 3) return point_3;
		if(index == 4) return point_4;
		if(index == 5) return point_5;
		if(index == 6) return point_6;
		if(index == 7) return point_7;
		if(index == 8) return point_8;
		System.err.println("Invalid point index");
		return new Vector();
	}
	
	public void calculatePoints(){
		point_1 = (new Segment(location, location.plus(Vector.createFromRectangular(size.x/2,  size.y/2,  size.z/2)))).rotateBy(rotation).getEnd();
		point_2 = (new Segment(location, location.plus(Vector.createFromRectangular(size.x/2,  size.y/2, -size.z/2)))).rotateBy(rotation).getEnd();
		point_3 = (new Segment(location, location.plus(Vector.createFromRectangular(size.x/2, -size.y/2,  size.z/2)))).rotateBy(rotation).getEnd();
		point_4 = (new Segment(location, location.plus(Vector.createFromRectangular(size.x/2, -size.y/2, -size.z/2)))).rotateBy(rotation).getEnd();
		point_5 = (new Segment(location, location.plus(Vector.createFromRectangular(-size.x/2,  size.y/2,  size.z/2)))).rotateBy(rotation).getEnd();
		point_6 = (new Segment(location, location.plus(Vector.createFromRectangular(-size.x/2,  size.y/2, -size.z/2)))).rotateBy(rotation).getEnd();
		point_7 = (new Segment(location, location.plus(Vector.createFromRectangular(-size.x/2, -size.y/2,  size.z/2)))).rotateBy(rotation).getEnd();
		point_8 = (new Segment(location, location.plus(Vector.createFromRectangular(-size.x/2, -size.y/2, -size.z/2)))).rotateBy(rotation).getEnd();
		pointsDefined = true;
	}
	
	public void calculateSides() {
		side_FRONT_X = new Segment(getPoint(8), getPoint(4));
		side_FRONT_Y = new Segment(getPoint(8), getPoint(6));
		side_FRONT_Z = new Segment(getPoint(8), getPoint(7));
		side_BACK_X  = new Segment(getPoint(5), getPoint(1));
		side_BACK_Y  = new Segment(getPoint(3), getPoint(1));
		side_BACK_Z  = new Segment(getPoint(2), getPoint(1));
		side_RIGHT_X = new Segment(getPoint(7), getPoint(3));
		side_RIGHT_Y = new Segment(getPoint(4), getPoint(3));
		side_RIGHT_Z = new Segment(getPoint(4), getPoint(2));
		side_LEFT_X  = new Segment(getPoint(6), getPoint(2));
		side_LEFT_Y  = new Segment(getPoint(7), getPoint(5));
		side_LEFT_Z  = new Segment(getPoint(6), getPoint(5));
		sidesDefined = true;
	}
	
	/**Returns the specified side of the prism*/
	public Segment getSide(Position p, Axis a) {
		if(!sidesDefined) calculateSides();
		if(p == Position.FRONT) {
			if(a == Axis.X) return side_FRONT_X;
			if(a == Axis.Y) return side_FRONT_Y;
			if(a == Axis.Z) return side_FRONT_Z;
		}
		if(p == Position.BACK) {
			if(a == Axis.X) return side_BACK_X;
			if(a == Axis.Y) return side_BACK_Y;
			if(a == Axis.Z) return side_BACK_Z;
		}
		if(p == Position.RIGHT) {
			if(a == Axis.X) return side_RIGHT_X;
			if(a == Axis.Y) return side_RIGHT_Y;
			if(a == Axis.Z) return side_RIGHT_Z;
		}
		if(p == Position.LEFT) {
			if(a == Axis.X) return side_LEFT_X;
			if(a == Axis.Y) return side_LEFT_Y;
			if(a == Axis.Z) return side_LEFT_Z;
		}
		System.err.println("Invalid side index");
		return null;
	}
	
	public void calculatePlanes(){
		plane_FRONT_X = new Plane(getSide(Position.FRONT, Axis.Y).direction().cross(getSide(Position.FRONT, Axis.Z).direction()),getSide(Position.FRONT, Axis.Y).getStart());
		plane_FRONT_Y = new Plane(getSide(Position.FRONT, Axis.X).direction().cross(getSide(Position.FRONT, Axis.Z).direction()),getSide(Position.FRONT, Axis.X).getStart());
		plane_FRONT_Z = new Plane(getSide(Position.FRONT, Axis.X).direction().cross(getSide(Position.FRONT, Axis.Y).direction()),getSide(Position.FRONT, Axis.X).getStart());
		plane_BACK_X = new Plane(getSide(Position.BACK, Axis.Y).direction().cross(getSide(Position.BACK, Axis.Z).direction()),getSide(Position.BACK, Axis.Y).getStart());
		plane_BACK_Y = new Plane(getSide(Position.BACK, Axis.X).direction().cross(getSide(Position.BACK, Axis.Z).direction()),getSide(Position.BACK, Axis.X).getStart());
		plane_BACK_Z = new Plane(getSide(Position.BACK, Axis.X).direction().cross(getSide(Position.BACK, Axis.Y).direction()),getSide(Position.BACK, Axis.X).getStart());
		planesDefined = true;
	}
	
	public Plane getPlane(Position p, Axis a){
		if(!planesDefined) calculatePlanes();
		if(p == Position.FRONT){
			if(a == Axis.X) return plane_FRONT_X;
			if(a == Axis.Y) return plane_FRONT_Y;
			if(a == Axis.Z) return plane_FRONT_Z;
		}
		if(p == Position.BACK){
			if(a == Axis.X) return plane_BACK_X;
			if(a == Axis.Y) return plane_BACK_Y;
			if(a == Axis.Z) return plane_BACK_Z;
		}
		System.err.println("Invalid plane index");
		return null;
	}
	
	
	/** Computes if a ray intersects with the rectangular prism
	 * @return the distance on the ray when it intersects if r = -1 the the ray does not intersect */
	public double isIntersecting(Ray ray) {
		long time = System.nanoTime();
		double r = -1;
		//define planes
		Plane x1 = getPlane(Position.FRONT, Axis.X);
		Plane x2 = getPlane( Position.BACK, Axis.X);
		Plane y1 = getPlane(Position.FRONT, Axis.Y);
		Plane y2 = getPlane( Position.BACK, Axis.Y);
		Plane z1 = getPlane(Position.FRONT, Axis.Z);
		Plane z2 = getPlane( Position.BACK, Axis.Z);
		
		//X component
		Vector x1i = x1.intersection(ray);
		double x1r = -1;
		if((Double.isFinite(x1i.x))&&(Double.isFinite(x1i.y))&&(Double.isFinite(x1i.z))){
			if((!y1.compare(x1i).equals(y2.compare(x1i)))&&(!z1.compare(x1i).equals(z2.compare(x1i)))){
				x1r = (new Segment(ray.origin, x1i)).direction().norm();
				if(x1r < 0){x1r = -1;}
			}
		}
		
		Vector x2i = x2.intersection(ray);
		double x2r = -1;
		if((Double.isFinite(x2i.x))&&(Double.isFinite(x2i.y))&&(Double.isFinite(x2i.z))){
			if((!y1.compare(x2i).equals(y2.compare(x2i)))&&(!z1.compare(x2i).equals(z2.compare(x2i)))){
				x2r = (new Segment(ray.origin, x2i)).direction().norm();
				if(x2r < 0){x2r = -1;}
			}
		}
		
		//Y component
		Vector y1i = y1.intersection(ray);
		double y1r = -1;
		if((Double.isFinite(y1i.x))&&(Double.isFinite(y1i.y))&&(Double.isFinite(y1i.z))){
			if((!x1.compare(y1i).equals(x2.compare(y1i)))&&(!z1.compare(y1i).equals(z2.compare(y1i)))){
				y1r = (new Segment(ray.origin, y1i)).direction().norm();
				if(y1r < 0){y1r = -1;}
			}
		}
		
		Vector y2i = y2.intersection(ray);
		double y2r = -1;
		if((Double.isFinite(y2i.x))&&(Double.isFinite(y2i.y))&&(Double.isFinite(y2i.z))){
			if((!x1.compare(y2i).equals(x2.compare(y2i)))&&(!z1.compare(y2i).equals(z2.compare(y2i)))){
				y2r = (new Segment(ray.origin, y2i)).direction().norm();
				if(y2r < 0){y2r = -1;}
			}
		}
		
		//Z component
		Vector z1i = z1.intersection(ray);
		double z1r = -1;
		if((Double.isFinite(z1i.x))&&(Double.isFinite(z1i.y))&&(Double.isFinite(z1i.z))){
			if((!x1.compare(z1i).equals(x2.compare(z1i)))&&(!y1.compare(z1i).equals(y2.compare(z1i)))){
				z1r = (new Segment(ray.origin, z1i)).direction().norm();
				if(z1r < 0){z1r = -1;}
			}
		}
		
		Vector z2i = z2.intersection(ray);
		double z2r = -1;
		if((Double.isFinite(z2i.x))&&(Double.isFinite(z2i.y))&&(Double.isFinite(z2i.z))){
			if((!x1.compare(z2i).equals(x2.compare(z2i)))&&(!y1.compare(z2i).equals(y2.compare(z2i)))){
				z2r = (new Segment(ray.origin, z2i)).direction().norm();
				if(z2r < 0){z2r = -1;}
			}
		}
		
		//Calculate r
		double max = Math.max(x1r, Math.max(x2r, Math.max(y1r, Math.max(y2r, Math.max(z1r, z2r)))));
		if(x1r == -1) x1r = max;
		if(x2r == -1) x2r = max;
		if(y1r == -1) y1r = max;
		if(y2r == -1) y2r = max;
		if(z1r == -1) z1r = max;
		if(z2r == -1) z2r = max;
		r = Math.min(x1r, Math.min(x2r, Math.min(y1r, Math.min(y2r, Math.min(z1r, z2r))))); 
		
		//System.out.println(System.nanoTime()-time);
		return r;
	}
	
	/** Computes an angle of a perpendicular line from the rectangular prism based on a vector */
	public Vector getSurfaceNormal(Vector p) {
		long time = System.nanoTime();
		Vector c = (new Segment(location, p)).direction();
		double thetaL = c.angleWith(getSide(Position.FRONT, Axis.X).direction());
		double thetaL2 = c.angleWith(getSide(Position.FRONT, Axis.X).direction().negative());
		double thetaW = c.angleWith(getSide(Position.FRONT, Axis.Y).direction());
		double thetaW2 = c.angleWith(getSide(Position.FRONT, Axis.Y).direction().negative());
		double thetaH = c.angleWith(getSide(Position.FRONT, Axis.Z).direction());
		double thetaH2 = c.angleWith(getSide(Position.FRONT, Axis.Z).direction().negative());
		double minTheta = Math.min(thetaL, Math.min(thetaW, Math.min(thetaH, Math.min(thetaL2, Math.min(thetaH, Math.min(thetaW2, thetaH2))))));
		
		if(minTheta == thetaL) {
			return getSide(Position.FRONT, Axis.X).direction();
		}
		if(minTheta == thetaW) {
			return getSide(Position.FRONT, Axis.Y).direction();
		}
		if(minTheta == thetaH) {
			return getSide(Position.FRONT, Axis.Z).direction();
		}
		if(minTheta == thetaL2) {
			return getSide(Position.FRONT, Axis.X).direction().negative();
		}
		if(minTheta == thetaW2) {
			return getSide(Position.FRONT, Axis.Y).direction().negative();
		}
		if(minTheta == thetaH2) {
			return getSide(Position.FRONT, Axis.Z).direction().negative();
		}
		
		System.err.println("Problem with reflection.");
		return (new Vector());
	}
	
	public Shape translateBy(Vector v) {
		return new RectangularPrism(location.plus(v), size, rotation);
	}
	
	public Shape rotateBy(Rotation r) {
		return new RectangularPrism(location, size, rotation.plus(r));
	}
}

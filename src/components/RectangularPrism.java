package components;

import java.awt.Color;

import main.Start;
import util.Axis;
import util.Position;
import util.Util;

/**
 * A rectangular prism
 * @author Benjamin Buck
 *
 */
public class RectangularPrism extends PhysicalObject {
	Segment length;
	Segment width;
	Segment height;
	
	
	/**
	 * Constructs a rectangular prism based on three vectors with one common point
	 * @param l A vector specifying the object's length
	 * @param w A vector specifying the object's width
	 * @param h A vector specifying the object's height
	 * @param v A vector specifying the object's initial velocity
	 * @param m Mass of the prism
	 * @param c The color of the rectangle
	 */
	public RectangularPrism(Segment l, Segment w, Segment h, Vector v, double m, Color c) {
		length = l;
		width = w;
		height = h;
		motion = v;
		mass = m;
		color = c;
		Start.addToRenderList(this);
	}
	
	public RectangularPrism(Vector pos, double size, Vector v, double m, Color c) {
		this(new Segment(pos, pos.plus(Vector.createFromRectangular(size, 0, 0))),new Segment(pos, pos.plus(Vector.createFromRectangular(0, size, 0))),new Segment(pos, pos.plus(Vector.createFromRectangular(0, 0, size))),v,m,c);
	}
	
	/** Creates a rectangular prism based on three segments which all <b> must </b> share one origin point
	 * default color, mass, and motion */
	public RectangularPrism(Segment l, Segment w, Segment h) {
		length = l;
		width = w;
		height = h;
		Start.addToRenderList(this);
	}
	public RectangularPrism(Segment l, Segment w, Segment h, boolean list) {
		length = l;
		width = w;
		height = h;
		if(list){Start.addToRenderList(this);}
	}
	
	/** Returns the volume of the prism*/
	public double volume() {
		return (length.length() * width.length() * height.length());
	}
	/** Returns the surface area of the prism*/
	public double surfaceArea() {
		double t_x = length.length();
		double t_y = width.length();
		double t_z = height.length();
		
		return (2*t_x*t_y + 2*t_x*t_z + 2*t_y*t_z);
	}
	/**Returns the specified side of the prism*/
	public Segment getSide(Position p, Axis a) {
		if(p == Position.FRONT) {
			if(a == Axis.X){
				return length;
			}
			if(a == Axis.Y){
				return width;
			}
			if(a == Axis.Z){
				return height;
			}
		}
		if(p == Position.BACK) {
			if(a == Axis.X){
				return length.translateBy(width.direction().plus(height.direction()));
			}
			if(a == Axis.Y){
				return width.translateBy(length.direction().plus(height.direction()));
			}
			if(a == Axis.Z){
				return height.translateBy(length.direction().plus(height.direction()));
			}
		}
		if(p == Position.RIGHT) {
			if(a == Axis.X){
				return length.translateBy(height.direction());
			}
			if(a == Axis.Y){
				return width.translateBy(length.direction());
			}
			if(a == Axis.Z){
				return height.translateBy(length.direction());
			}
		}
		if(p == Position.LEFT) {
			if(a == Axis.X){
				return length.translateBy(width.direction());
			}
			if(a == Axis.Y){
				return width.translateBy(height.direction());
			}
			if(a == Axis.Z){
				return height.translateBy(width.direction());
			}
		}
		System.err.println("Invalid index");
		return null;
	}
	
	//Currently Not being used
	/*public double isIntersecting(Vector p) {
		//Pretty sure that this code is 100% flawed....
		boolean cx = false;
		boolean cy = false;
		boolean cz = false;
		
		//X component
		Plane x1 = 	new Plane(getSide(Position.FRONT, Axis.Y).direction().cross(getSide(Position.FRONT, Axis.Z).direction()),
				getSide(Position.FRONT, Axis.Y).getStart());
		Plane x2 = 	new Plane(getSide(Position.BACK, Axis.Y).direction().cross(getSide(Position.BACK, Axis.Z).direction()),
				getSide(Position.BACK, Axis.Y).getStart());
		if(!x1.compare(p).equals(x2.compare(p))) cx = true;
		
		//Y component
		Plane y1 = 	new Plane(getSide(Position.FRONT, Axis.X).direction().cross(getSide(Position.FRONT, Axis.Z).direction()),
				getSide(Position.FRONT, Axis.X).getStart());
		Plane y2 = 	new Plane(getSide(Position.BACK, Axis.X).direction().cross(getSide(Position.BACK, Axis.Z).direction()),
				getSide(Position.BACK, Axis.X).getStart());
		if(!y1.compare(p).equals(y2.compare(p))) cy = true;
		
		//Z component
		Plane z1 = 	new Plane(getSide(Position.FRONT, Axis.X).direction().cross(getSide(Position.FRONT, Axis.Y).direction()),
				getSide(Position.FRONT, Axis.X).getStart());
		Plane z2 = 	new Plane(getSide(Position.BACK, Axis.X).direction().cross(getSide(Position.BACK, Axis.Y).direction()),
				getSide(Position.BACK, Axis.X).getStart());
		if(!z1.compare(p).equals(z2.compare(p))) cz = true;
		
		//return (cx && cy && cz);
		return -1;
	}*/
	public double isIntersecting(Ray ray) {
		double r = -1;
		//define planes
		Plane x1 = 	new Plane(getSide(Position.FRONT, Axis.Y).direction().cross(getSide(Position.FRONT, Axis.Z).direction()),
				getSide(Position.FRONT, Axis.Y).getStart());
		Plane x2 = 	new Plane(getSide(Position.BACK, Axis.Y).direction().cross(getSide(Position.BACK, Axis.Z).direction()),
				getSide(Position.BACK, Axis.Y).getStart());
		Plane y1 = 	new Plane(getSide(Position.FRONT, Axis.X).direction().cross(getSide(Position.FRONT, Axis.Z).direction()),
				getSide(Position.FRONT, Axis.X).getStart());
		Plane y2 = 	new Plane(getSide(Position.BACK, Axis.X).direction().cross(getSide(Position.BACK, Axis.Z).direction()),
				getSide(Position.BACK, Axis.X).getStart());
		Plane z1 = 	new Plane(getSide(Position.FRONT, Axis.X).direction().cross(getSide(Position.FRONT, Axis.Y).direction()),
				getSide(Position.FRONT, Axis.X).getStart());
		Plane z2 = 	new Plane(getSide(Position.BACK, Axis.X).direction().cross(getSide(Position.BACK, Axis.Y).direction()),
				getSide(Position.BACK, Axis.X).getStart());
		
		//X component
		Vector x1i = x1.intersection(ray);
		double x1r = -1;
		if((!Util.isNaN(x1i.x))&&(!Util.isNaN(x1i.y))&&(!Util.isNaN(x1i.z))){
			if((!y1.compare(x1i).equals(y2.compare(x1i)))&&(!z1.compare(x1i).equals(z2.compare(x1i)))){
				x1r = (new Segment(ray.origin, x1i)).direction().norm();
				if(x1r < 0){x1r = -1;}
			}
		}
		
		Vector x2i = x2.intersection(ray);
		double x2r = -1;
		if((!Util.isNaN(x2i.x))&&(!Util.isNaN(x2i.y))&&(!Util.isNaN(x2i.z))){
			if((!y1.compare(x2i).equals(y2.compare(x2i)))&&(!z1.compare(x2i).equals(z2.compare(x2i)))){
				x2r = (new Segment(ray.origin, x2i)).direction().norm();
				if(x2r < 0){x2r = -1;}
			}
		}
		
		//Y component
		Vector y1i = y1.intersection(ray);
		double y1r = -1;
		if((!Util.isNaN(y1i.x))&&(!Util.isNaN(y1i.y))&&(!Util.isNaN(y1i.z))){
			if((!x1.compare(y1i).equals(x2.compare(y1i)))&&(!z1.compare(y1i).equals(z2.compare(y1i)))){
				y1r = (new Segment(ray.origin, y1i)).direction().norm();
				if(y1r < 0){y1r = -1;}
			}
		}
		
		Vector y2i = y2.intersection(ray);
		double y2r = -1;
		if((!Util.isNaN(y2i.x))&&(!Util.isNaN(y2i.y))&&(!Util.isNaN(y2i.z))){
			if((!x1.compare(y2i).equals(x2.compare(y2i)))&&(!z1.compare(y2i).equals(z2.compare(y2i)))){
				y2r = (new Segment(ray.origin, y2i)).direction().norm();
				if(y2r < 0){y2r = -1;}
			}
		}
		
		//Z component
		Vector z1i = z1.intersection(ray);
		double z1r = -1;
		if((!Util.isNaN(z1i.x))&&(!Util.isNaN(z1i.y))&&(!Util.isNaN(z1i.z))){
			if((!x1.compare(z1i).equals(x2.compare(z1i)))&&(!y1.compare(z1i).equals(y2.compare(z1i)))){
				z1r = (new Segment(ray.origin, z1i)).direction().norm();
				if(z1r < 0){z1r = -1;}
			}
		}
		
		Vector z2i = z2.intersection(ray);
		double z2r = -1;
		if((!Util.isNaN(z2i.x))&&(!Util.isNaN(z2i.y))&&(!Util.isNaN(z2i.z))){
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
		
		return r;
	}
	
	public Vector getSurfaceNormal(Vector p) {
		Vector c = (new Segment(getCenter(), p)).direction();
		double thetaL = c.angleWith(length.direction());
		double thetaL2 = c.angleWith(length.direction().negative());
		double thetaW = c.angleWith(width.direction());
		double thetaW2 = c.angleWith(width.direction().negative());
		double thetaH = c.angleWith(height.direction());
		double thetaH2 = c.angleWith(height.direction().negative());
		double minTheta = Math.min(thetaL, Math.min(thetaW, Math.min(thetaH, Math.min(thetaL2, Math.min(thetaH, Math.min(thetaW2, thetaH2))))));
		
		if(minTheta == thetaL){
			return length.direction();
		}
		if(minTheta == thetaW){
			return width.direction();
		}
		if(minTheta == thetaH){
			return height.direction();
		}
		if(minTheta == thetaL2){
			return length.direction().negative();
		}
		if(minTheta == thetaW2){
			return width.direction().negative();
		}
		if(minTheta == thetaH2){
			return height.direction().negative();
		}
		
		System.err.println("Problem with reflection.");
		return (new Vector());
	}
	
	/* Moves a rectangular prism by some vector */
	public RectangularPrism translateBy(Vector v) {
		return new RectangularPrism(
			length.translateBy(v),
			width.translateBy(v),
			height.translateBy(v),
			false
		);
	}
	
	@Override
	public void editTo(PhysicalObject r) {
		length = ((RectangularPrism)r).length;
		width = ((RectangularPrism)r).width;
		height = ((RectangularPrism)r).height;
		super.editTo(r);
	}
	
	public Vector getCenter(){
		return length.getMidpoint().plus(width.direction().scaleBy(0.5)).plus(height.direction().scaleBy(0.5));
	}
}

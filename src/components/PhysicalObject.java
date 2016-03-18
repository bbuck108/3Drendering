package components;

import java.awt.Color;

import org.json.JSONObject;

import main.Start;

/** Defines an object located in 3D space.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class PhysicalObject {
	//-------------------Fields-------------//
	/** the velocity of the object */
	private Vector velocity;
	private double mass;
	private Color color;
	/** This must contain an extended Class of Shape */
	private Shape shape;
	
	//--------Constructors-------------//
	/** Default Constructor (sets mass to 1) */
	public PhysicalObject() {
		mass = 1;
		color = Color.RED;
		velocity = new Vector();
		shape = null;
	}
	
	/** Sets the shape and sets mass to 1 */
	public PhysicalObject(Shape s) {
		this();
		shape = s;
	}
	
	public PhysicalObject(Vector motion, double mass, Color c, Shape s) {
		this(s);
		this.velocity = motion;
		this.mass = mass;
		color = c;
	}
	
	public PhysicalObject(Vector motion, double mass, Color c, Shape s, boolean r) {
		this(motion, mass, c, s);
		Start.addToRenderList(this);
	}
	
	public PhysicalObject(JSONObject jsonObject) {
		velocity = Vector.createFromRectangular(
				jsonObject.getJSONArray("velocity").getDouble(0),
				jsonObject.getJSONArray("velocity").getDouble(1),
				jsonObject.getJSONArray("velocity").getDouble(2));
		mass = jsonObject.getDouble("mass");
		color = new Color(
				jsonObject.getJSONArray("color").getInt(0),
				jsonObject.getJSONArray("color").getInt(1),
				jsonObject.getJSONArray("color").getInt(2));
		switch (jsonObject.getJSONObject("shape").getString("type")){
			case "Sphere":
				shape = new Sphere(jsonObject.getJSONObject("shape"));
				break;
			case "RectangularPrism":
				shape = new RectangularPrism(jsonObject.getJSONObject("shape"));
				break;
		}
		Start.addToRenderList(this);
	}

	/** Redefines the location of the shape within physical object */
	public void move() {
		shape = shape.translateBy(velocity);
	}
	
	
	public Vector getVelocity() {
		return velocity;
	}
	
	public double getMass() {
		return mass;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Shape getShape() {
		return shape;
	}

}

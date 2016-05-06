package object;

import java.awt.Color;

import org.json.JSONObject;

import component.Rotation;
import component.Vector;
import main.Start;
import render.Camera;
import render.LightSource;
import shape.RectangularPrism;
import shape.Shape;
import shape.Sphere;
import texturing.Texture;

/** Defines an object located in 3D space.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class PhysicalObject {
	//-------------------Fields-------------//
	/** the linear velocity of the object */
	private Vector velocity;
	private Rotation rotationalVelocity;
	private double mass;
	private Color color;
	/** This must contain an extended Class of Shape */
	private Shape shape;
	private Texture texture;
	
	//--------Constructors-------------//
	/** Default Constructor (sets mass to 1) */
	public PhysicalObject() {
		mass = 1;
		color = Color.RED;
		velocity = new Vector();
		rotationalVelocity = new Rotation();
		shape = null;
		texture = null;
	}
	
	/** Sets the shape and sets mass to 1 */
	public PhysicalObject(Shape s) {
		this();
		shape = s;
	}
	
	public PhysicalObject(Vector motion, Rotation rotation, double mass, Color c, Shape s, Texture t) {
		this();
		this.velocity = motion;
		this.rotationalVelocity = rotation;
		this.mass = mass;
		color = c;
		texture = t;
	}
	
	public PhysicalObject(Vector motion, Rotation rotation, double mass, Color c, Shape s, Texture t, boolean render, boolean physics) {
		this(motion, rotation, mass, c, s, t);
		if(render)  Start.addToRenderList(this);
		if(physics) Start.addToPhysicsList(this);
	}
	
	public PhysicalObject(JSONObject jsonObject) {
		velocity = Vector.createFromRectangular(
				jsonObject.getJSONArray("velocity").getDouble(0),
				jsonObject.getJSONArray("velocity").getDouble(1),
				jsonObject.getJSONArray("velocity").getDouble(2));
		rotationalVelocity = new Rotation(
				jsonObject.getJSONArray("rotationalVelocity").getDouble(0),
				jsonObject.getJSONArray("rotationalVelocity").getDouble(1),
				jsonObject.getJSONArray("rotationalVelocity").getDouble(2));
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
			case "Camera":
				shape = new Camera(jsonObject.getJSONObject("shape"));
				break;
			case "LightSource":
				shape = new LightSource(jsonObject.getJSONObject("shape"));
				break;
		}
		Start.addToPhysicsList(this);
		if(jsonObject.getBoolean("render")){
			Start.addToRenderList(this);
		}
	}

	/** Redefines the location of the shape within physical object */
	public void move() {
		shape = shape.translateBy(velocity).rotateBy(rotationalVelocity);
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

	public Texture getTexture() {
		return texture;
	}
}

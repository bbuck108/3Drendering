package render;

import org.json.JSONObject;

import component.Ray;
import component.Rotation;
import component.Vector;
import shape.Shape;

public class LightSource extends Shape{
	public LightSource(Vector location, Rotation rotation){
		super(location, rotation);
	}
	public LightSource(Vector location){
		this(location, new Rotation());
	}
	public LightSource(JSONObject jsonObject) {
		this(Vector.createFromRectangular(
				jsonObject.getJSONArray("location").getDouble(0),
				jsonObject.getJSONArray("location").getDouble(1),
				jsonObject.getJSONArray("location").getDouble(2)),
				new Rotation(
						jsonObject.getJSONArray("rotation").getDouble(0),
						jsonObject.getJSONArray("rotation").getDouble(1),
						jsonObject.getJSONArray("rotation").getDouble(2)));
	}
	@Override
	public double isIntersecting(Ray ray) {
		return -1;
	}
	@Override
	public Vector getSurfaceNormal(Vector v) {
		return new Vector();
	}
	@Override
	public Shape translateBy(Vector v) {
		return new LightSource(getLocation().plus(v));
	}
	@Override
	public Shape rotateBy(Rotation r) {
		return new LightSource(getLocation(), rotation.plus(r));
	}

	@Override
	public int[] getTexturingPoint(Vector v) {
		return new int[]{0, 0, 0};
	}
}

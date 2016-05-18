package shape;

import org.json.JSONObject;

import component.Plane;
import component.Ray;
import component.Rotation;
import component.Segment;
import component.Vector;

public class RenderablePlane extends Shape {
	final Plane plane;
	
	public RenderablePlane(Plane plane){
		this.plane = plane;
	}
	
	public RenderablePlane(JSONObject jsonObject) {
		plane = new Plane(
				Vector.createFromRectangular(
					jsonObject.getJSONArray("normal").getDouble(0),
					jsonObject.getJSONArray("normal").getDouble(1),
					jsonObject.getJSONArray("normal").getDouble(2)),
				Vector.createFromRectangular(
					jsonObject.getJSONArray("location").getDouble(0),
					jsonObject.getJSONArray("location").getDouble(1),
					jsonObject.getJSONArray("location").getDouble(2)));
	}

	@Override
	public double isIntersecting(Ray ray) {
		return new Segment (ray.getOrigin(), plane.intersection(ray)).length();
	}

	@Override
	public Vector getSurfaceNormal(Vector v) {
		return plane.getNormalVector();
	}
	
	@Override
	public int[] getTexturingPoint(Vector v) {
		return new int[]{0, 0, 0};
	}

	@Override
	public Shape translateBy(Vector v) {
		Vector point = Vector.createFromRectangular(0, 0, (-1*plane.getD()/plane.getC()));
		return new RenderablePlane(new Plane(plane.getNormalVector(), point.plus(v)));
	}

	@Override
	public Shape rotateBy(Rotation r) { 
		// TODO RenderablePlane rotations
		return this;
	}
}

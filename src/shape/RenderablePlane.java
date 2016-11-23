package shape;

import org.json.JSONObject;

import component.Plane;
import component.Ray;
import component.Rotation;
import component.Segment;
import component.Vector;
import main.Start;

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
		double distance = new Segment (ray.getOrigin(), plane.intersection(ray)).length();
		if(new Segment (ray.getOrigin(), plane.intersection(ray)).direction().phi()>Math.PI/2){
			return -1;
		}else{
			if(!Double.isFinite(distance)){
				return -1;
			}else{
				return distance;
			}
		}
	}
	
	@Override
	public Vector getSurfaceNormal(Vector v) {
		if(Math.cos(plane.getNormalVector().angleWith((new Segment(v, Start.lightSource.getShape().getLocation())).direction())) > Math.cos(plane.getNormalVector().negative().angleWith((new Segment(v, Start.lightSource.getShape().getLocation())).direction()))){
			return plane.getNormalVector();
		}else{
			return plane.getNormalVector().negative();
		}
	}
	
	@Override
	public int[] getTexturingPoint(Vector v) {
		return new int[]{0, 0, 0};
	}

	@Override
	public Shape translateBy(Vector v) {
		//Vector point = Vector.createFromRectangular(0, 0, (-1*plane.getD()/plane.getC()));
		//return new RenderablePlane(new Plane(plane.getNormalVector(), point.plus(v)));
		return this;
	}

	@Override
	public Shape rotateBy(Rotation r) { 
		// TODO RenderablePlane rotations
		return this;
	}
}

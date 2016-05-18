package shape;

import component.Ray;
import component.Rotation;
import component.Vector;

public class Cylinder extends Shape{
	double height;
	double radius;
	
	public Cylinder(Vector center, Rotation rotation, double height, double radius){
		super(center,rotation);
		this.height = height;
		this.radius = radius;
	}

	@Override
	public double isIntersecting(Ray ray) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector getSurfaceNormal(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape translateBy(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape rotateBy(Rotation r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getTexturingPoint(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

}

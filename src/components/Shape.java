package components;

public abstract class Shape {
	Vector location;
	
	public Shape(Vector c) {
		location = c;
	}
	public abstract double isIntersecting(Ray ray);
	public abstract Vector getSurfaceNormal(Vector p);
	public abstract Shape translateBy(Vector p);
}

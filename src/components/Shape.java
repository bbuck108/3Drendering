package components;

public abstract class Shape implements Cloneable {
	Vector location;
	
	public Shape(Vector c) {
		location = c;
	}
	public abstract double isIntersecting(Ray ray);
	public abstract Vector getSurfaceNormal(Vector p);
	public Shape translateBy(Vector p){
		try {
			Shape obj = (Shape) this.clone();
			obj.location = this.location.plus(p);
			return obj;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	};
}

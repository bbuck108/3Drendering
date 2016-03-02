package components;

public class Ray {
	Vector origin;
	double theta;
	double phi;
	
	public Ray(Vector origin, double theta, double phi){
		this.origin = origin;
		this.theta  = theta;
		this.phi    = phi;
	}
}

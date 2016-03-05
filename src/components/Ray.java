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
	public Vector getV(){
		return Vector.createFromSpherical(1, theta, phi);
	}
}

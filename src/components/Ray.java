package components;

/** Defines a ray in 3D space.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
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

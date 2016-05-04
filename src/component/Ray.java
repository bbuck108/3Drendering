package component;

/** Defines a ray in 3D space.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class Ray {
	private Vector origin;
	private double theta;
	private double phi;
	
	public Ray(Vector origin, double theta, double phi){
		this.setOrigin(origin);
		this.setTheta(theta);
		this.setPhi(phi);
	}
	public Ray(Segment segment){
		this(segment.getStart(),
				segment.direction().theta(),
				segment.direction().phi());
	}
	public Ray(Vector origin, Vector direction){
		this(origin,
				direction.theta(),
				direction.phi());
	}
	public Vector getV(){
		return Vector.createFromSpherical(1, getTheta(), getPhi());
	}
	public double getTheta() {
		return theta;
	}
	public void setTheta(double theta) {
		this.theta = theta;
	}
	public double getPhi() {
		return phi;
	}
	public void setPhi(double phi) {
		this.phi = phi;
	}
	public Vector getOrigin() {
		return origin;
	}
	public void setOrigin(Vector origin) {
		this.origin = origin;
	}
}

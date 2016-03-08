package components;

/**
 * A vector which contains three angles in radians about the axis
 * @author Connor Lehmacher
 */
public class Rotation extends Vector{
	public Rotation() {}
	
	public Rotation(double x, double y, double z) {
		super(x, y, z);
	}
	
	public void reduce() {
		if(x < 0 || y < 0 || z < 0) {
			x += TAU;
			y += TAU;
			z += TAU;
			reduce();
		}
		x %= TAU;
		y %= TAU;
		z %= TAU;
	}
	
	/** Pi times two */
	final static double TAU = Math.PI * 2;
}

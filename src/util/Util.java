package util;

import components.Rotation;

/**
 * Other utility functions.
 * @author Benjamin Buck and Connor Lehmacher
 */
public class Util {
	//-----------Static-Methods--------//
	/** Computes a^2 */
	public static double sq(double a) {
		return a * a;
	}
	
    public static boolean isNearZero(double x) {
    	final double EPSILON = Double.MIN_NORMAL * 1000000;
    	return x > -EPSILON && x < EPSILON;
    }

    /** returns a Rotation from a rotation which may be outside the standard range
     * @return a rotation in the range of 0 to 2 Pi */
	public static Rotation reduceRotation(Rotation r) {
		double x = r.x;
		double y = r.y;
		double z = r.z;
		if(x < 0 || y < 0 || z < 0) {
			x += TAU;
			y += TAU;
			z += TAU;
			reduceRotation(new Rotation(x, y, z));
		}
		x %= TAU;
		y %= TAU;
		z %= TAU;
		return new Rotation(x, y, z);
	}
	
	/** Pi times two */
	final static double TAU = Math.PI * 2;
}

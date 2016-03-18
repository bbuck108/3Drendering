package util;

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
	
	/** Pi times two */
	final static double TAU = Math.PI * 2;
}

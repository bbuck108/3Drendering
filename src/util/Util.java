package util;

/**
 * other utility functions
 * @author Connor Lehmacher
 */
public class Util {
	//-----------Static-Methods--------//
	public static double sq(double a) {
		return a * a;
	}
	
    public static boolean isNearZero(double x) {
    	final double EPSILON = Double.MIN_NORMAL * 1000000;
    	return x > -EPSILON && x < EPSILON;
    }
    
    public static boolean isNaN(double x){return x != x;}
}

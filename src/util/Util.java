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
	
	/** Powers with int */
	public static int power(int x, int y) {
		if(y == 0) {
			return 1;
		}
		if(y < 0) {
			if(x == 2 && y == -1) {
				return 1;
			}
			return 0;
		}
		return reducedpower(x, y);
	}
	
	/** Powers with int were y >= 1 for making power faster*/
	private static int reducedpower(int x, int y) {
		if(y == 1) {
			return x;
		}
		if(y % 2 == 0) {
			return reducedpower(x * x, y / 2);
		}
		return x * reducedpower(x, y - 1);
	}
}

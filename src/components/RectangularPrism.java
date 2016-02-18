package components;
import java.awt.Color;
import util.Vector;

public class RectangularPrism {
	Vector v1;
	Vector v2;
	Vector motion;
	
	Color color;
	
	public RectangularPrism(Vector p_1, Vector p_2, Vector p_3, Color p_4){
		v1 = p_1;
		v2 = p_2;
		motion = p_3;
		color = p_4;
	}
}

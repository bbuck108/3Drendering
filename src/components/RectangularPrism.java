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
	public double findVolume(){
		double t_x = Math.abs(v1.getX()-v2.getX());
		double t_y = Math.abs(v1.getY()-v2.getY());
		double t_z = Math.abs(v1.getZ()-v2.getZ());
		
		return (t_x * t_y * t_z);
	}
	public double findSurfaceArea(){
		double t_x = Math.abs(v1.getX()-v2.getX());
		double t_y = Math.abs(v1.getY()-v2.getY());
		double t_z = Math.abs(v1.getZ()-v2.getZ());
		
		return (2*t_x*t_y + 2*t_x*t_z + 2*t_y*t_z);
	}
}

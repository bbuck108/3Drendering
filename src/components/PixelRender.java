package components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import main.Start;

public class PixelRender extends RecursiveTask<double[]>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5435402063578387591L;
	int i;
	int j;
	Color color;
	Camera parent;
	
	public PixelRender(int x, int y, Camera parent){
		i = x;
		j = y;
		this.parent = parent;
	}
	
	
	
	public void run() {
		
		
		
	}


	@Override
	protected double[] compute() {
		double x = i - parent.screen.x/2;
		double y = j - parent.screen.y/2;
		double maxDistance = 0;
		ArrayList<double[]> intersections = new ArrayList<double[]>();
		
		Vector angle = Vector.createFromRectangular(x, y, parent.screen.z);
		double theta = angle.theta();
		double phi = angle.phi();
		Ray ray = new Ray(parent.point, theta, phi);
		
		for(PhysicalObject object: Start.renderList) {
			Shape shape = object.getShape();
			double distance = shape.isIntersecting(ray);
			
			if(distance != -1){
				Vector intersection = Vector.createFromSpherical(distance, theta, phi).plus(parent.point);
				double shade = Math.cos(shape.getSurfaceNormal(intersection).angleWith((new Segment(intersection, parent.lightSource)).direction()));
				if(shade < 0){shade = 0;}
				shade = (0.1 + 0.9*shade);
				color = new Color((int)(object.getColor().getRed()*shade),(int)(object.getColor().getGreen()*shade),(int)(object.getColor().getBlue()*shade));
				double[] pair = {distance, color.getRGB()};
				intersections.add(pair);
				maxDistance = Math.max(distance, maxDistance);
			}
		}
		double minDistance = maxDistance;
		color = Color.BLACK;
		for(double[] pair : intersections){
			if(pair[0]<=minDistance){
				minDistance = pair[0];
				color = new Color((int) pair[1]);
			}
		}
		
		double[] data = {i,j,color.getRGB()};
		return data;
	}

}

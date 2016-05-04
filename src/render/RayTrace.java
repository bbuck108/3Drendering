package render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import component.Ray;
import component.Segment;
import component.Vector;
import main.Start;
import object.PhysicalObject;
import shape.Shape;


/** A task compatible with ForkJoinPools that renders one pixel and returns the color
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class RayTrace extends RecursiveTask<double[]>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5435402063578387591L;
	int i;
	int j;
	Color color;
	Ray ray;
	
	public RayTrace(int i, int j, Ray ray){
		this.i = i;
		this.j = j;
		this.ray = ray;
	}
	
	@Override
	protected double[] compute() {
		ArrayList<double[]> intersections = new ArrayList<double[]>();
		double maxDistance = 0;
		
		
		for(PhysicalObject object: Start.renderList) {
			Shape shape = object.getShape();
			double distance = shape.isIntersecting(ray);
			
			if(distance != -1){
				Vector intersection = Vector.createFromSpherical(distance, ray.getTheta(), ray.getPhi()).plus(ray.getOrigin());
				
				
				//Check for shadows
				boolean shadow = false;
				for(PhysicalObject t_1 : Start.renderList){
					if(!object.getShape().equals(t_1.getShape())){
						if(t_1.getShape().isIntersecting(new Ray(new Segment(intersection, Start.lightSource.location))) != -1){
							shadow = true;
						}
					}
				}
				
				double shade = Math.cos(shape.getSurfaceNormal(intersection).angleWith((new Segment(intersection, Start.lightSource.location)).direction()));
				if(shade < 0){shade = 0;}
				if(shadow)   {shade = 0;}
				shade = (0.2 + 0.8*shade);
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

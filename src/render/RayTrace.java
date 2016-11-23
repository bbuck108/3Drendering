package render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import component.Plane;
import component.Ray;
import component.Rotation;
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
	PhysicalObject ignore;
	
	public RayTrace(int i, int j, Ray ray){
		this.i = i;
		this.j = j;
		this.ray = ray;
	}
	public RayTrace(int i, int j, Ray ray, PhysicalObject ignore){
		this.i = i;
		this.j = j;
		this.ray = ray;
		this.ignore = ignore;
	}
	
	@Override
	protected double[] compute() {
		ArrayList<double[]> intersections = new ArrayList<double[]>();
		
		
		for(PhysicalObject object: Start.renderList) {
			if(!object.equals(ignore)){
				Shape shape = object.getShape();
				double distance = shape.isIntersecting(ray);
				
				if(distance != -1){
					Vector intersection = Vector.createFromSpherical(distance, ray.getTheta(), ray.getPhi()).plus(ray.getOrigin());
					
					//Check for shadows
					boolean shadow = false;
					for(PhysicalObject t_1 : Start.renderList){
						if(!object.getShape().equals(t_1.getShape())){
							if(t_1.getShape().isIntersecting(new Segment(intersection, Start.lightSource.getShape().getLocation())) != -1){
								shadow = true;
							}
						}
					}
					
					//Calculate the shade
					double shade = Math.cos(shape.getSurfaceNormal(intersection).angleWith((new Segment(intersection, Start.lightSource.getShape().getLocation())).direction()));
					if(shade < 0) shade = 0;
					if(shadow) shade = 0;
					//Makes objects not completely black
					shade = (0.2 + 0.8*shade);
					
					//Get Point and index for shape
					int[] information = shape.getTexturingPoint(intersection);
					final int index = information[0];
					final int xPixel = information[1];
					final int yPixel = information[2];
					
					//TODO texturing
					int intColor = object.getTexture().getImage(index).getRGB(xPixel, yPixel);
					color = new Color(intColor);
					
					//Reflections
					if(object.getReflectivity() != 0){
						Vector incoming = Vector.createFromSpherical(1,ray.getTheta(),ray.getPhi()).rotateBy2(new Rotation(shape.getSurfaceNormal(intersection))).negative();
						Vector outgoing = Vector.createFromSpherical(1, incoming.theta(), -1*incoming.phi()).rotateBy2(new Rotation(shape.getSurfaceNormal(intersection)));
						Ray reflection = new Ray(intersection, outgoing);
						Color reflectColor = new Color((int) (new RayTrace(0,0,reflection, object)).compute()[2]);
						color = new Color((int)(reflectColor.getRed()*object.getReflectivity()+color.getRed()*(1-object.getReflectivity())),
									(int)(reflectColor.getGreen()*object.getReflectivity()+color.getGreen()*(1-object.getReflectivity())),
									(int)(reflectColor.getBlue()*object.getReflectivity()+color.getBlue()*(1-object.getReflectivity())));
					}
					
					//Apply shade
					color = new Color((int)(color.getRed()*shade),(int)(color.getGreen()*shade),(int)(color.getBlue()*shade));
					if(color.equals(Color.BLACK)){
						System.out.println(object.getClass().getName());
					}
					
					
					//Send data
					double[] pair = {distance, color.getRGB()};
					intersections.add(pair);
				}
			}
		}
		double maxDistance = 0;
		for(double[] pair : intersections){
			if(Double.isFinite(pair[0])){
				maxDistance = Math.max(maxDistance, pair[0]);
			}
		}
		double minDistance = maxDistance;
		color = Color.BLACK;
		for(double[] pair : intersections){
			if(Double.isFinite(pair[0])) {
				if(pair[0]<=minDistance){
					minDistance = pair[0];
					color = new Color((int) pair[1]);
				}
			}
		}
		
		double[] data = {i,j,color.getRGB()};
		return data;
	}

}

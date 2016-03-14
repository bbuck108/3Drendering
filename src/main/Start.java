package main;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import components.Camera;
import components.PhysicalObject;
import components.RectangularPrism;
import components.Rotation;
import components.Vector;

public class Start {
	public static ArrayList<PhysicalObject> renderList;
	public static Camera camera;
	static RectangularPrism x;
	
	public static void addToRenderList(PhysicalObject p_1) {
		renderList.add(p_1);
	}
	
	public static void main(String[] args) {
		renderList = new ArrayList<PhysicalObject>();
		
		//Add new objects here
		camera = new Camera(Vector.createFromRectangular(0, 0, 0), Vector.createFromRectangular(800, 600, 600), Vector.createFromRectangular(0, 0, 0));
		//
		//addToRenderList(new PhysicalObject(Vector.createFromRectangular(0, 0, 50), 1, Color.YELLOW, new Sphere(Vector.createFromRectangular(0, 0, 1000), 100)));
		addToRenderList(new PhysicalObject(Vector.createFromRectangular(0, 0, 0),
				1, Color.RED, 
				new RectangularPrism(Vector.createFromRectangular(0, 0, 1000),
						Vector.createFromRectangular(100, 100, 100),
						new Rotation(0, 0, 0))
				));
		//
		
		
		//Builds the screen
		try{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.setFullscreen(true);
			Display.create();
		} catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		
		//I have no idea what this does
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		//Begins the game loop
		loop();
	}
	
	public static void loop() {
		while (!Display.isCloseRequested()) {
			//Move
			for(PhysicalObject t_1: Start.renderList){
				t_1.move();
			}
			
			
			// I have no idea what this does...
			// When this is causing a problem I'm sorry
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
            // render OpenGL here
			Start.camera.render();
			System.out.println("Done rendering");
             
			//Update display
            Display.update();
        }
         
        Display.destroy();
	}
}

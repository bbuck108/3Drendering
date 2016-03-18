package main;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.json.JSONArray;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import components.Camera;
import components.PhysicalObject;
import components.RectangularPrism;
import components.Vector;
import file.ParseFile;

public class Start {
	public static ArrayList<PhysicalObject> renderList;
	public static Camera camera;
	static RectangularPrism x;
	static File input;
	
	public static void addToRenderList(PhysicalObject p_1) {
		renderList.add(p_1);
	}
	
	/**
	 * Opens a File Chooser dialog to select a scene file.
	 * The files should have .json extensions and follow a specific format.
	 * The file should be an array containing a list of objects with the following fields.<br>
	 * "velocity" : [x,y,z]<br>
	 * "mass"     : number larger than 0<br>
	 * "color"    : [r,g,b]<br>
	 * "shape"    : A JSONObject which can follow several formats:<br>
	 * <br>
	 * For a Sphere,<br>
	 * "type"     : "Sphere"<br>
	 * "location" : [x,y,z]<br>
	 * "radius"   : number larger than 0<br>
	 * <br>
	 * For a RectangularPrism,<br>
	 * this isn't implemented yet...<br>
	 * <br>
	 * Basic Example:<br>
	 * <span style="text-indent: 0px;">[</span><br>
	 * <span style="text-indent: 5px;">		{</span><br>
	 * <span style="text-indent:10px;">			"velocity" : [0,0,50],</span><br>
	 * <span style="text-indent:10px;">			"mass"     : 1,</span><br>
	 * <span style="text-indent:10px;">			"color"    : [255,255,0],</span><br>
	 * <span style="text-indent:10px;">			"shape"    : {</span><br>
	 * <span style="text-indent:15px;">							"type" : "Sphere",</span><br>
	 * <span style="text-indent:15px;">							"location" : [0, 0, 1000],</span><br>
	 * <span style="text-indent:15px;">							"radius"   : 100</span><br>
	 * <span style="text-indent:10px;">						}</span><br>
	 * <span style="text-indent: 5px;">		}</span><br>
	 * <span style="text-indent: 0px;">	]</span><br>
	 */
	public static void openSceneFile(){
		//File chooser dialog
		JFileChooser window = new JFileChooser();
		window.showOpenDialog(window);
		input = window.getSelectedFile();
		
		//Create objects from file
		JSONArray set = new JSONArray(ParseFile.parseFile(input.getPath()));
		for(int i = 0; i<set.length();i++){
			new PhysicalObject(set.getJSONObject(i));
		}
	}
	
	public static void main(String[] args) {
		renderList = new ArrayList<PhysicalObject>();
		camera = new Camera(Vector.createFromRectangular(0, 0, 0), Vector.createFromRectangular(800, 600, 600), Vector.createFromRectangular(0, 0, 0));
		
		openSceneFile();
		
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
		//Loop until the exit button is pressed
		while (!Display.isCloseRequested()) {
			//Move
			for(PhysicalObject t_1: renderList){
				t_1.move();
			}
			
			
			// I have no idea what this does...
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
            // render OpenGL here
			camera.render();
			//System.out.println("Done rendering");
             
			//Update display
            Display.update();
        }
        
		//Exit the window
        Display.destroy();
	}
}

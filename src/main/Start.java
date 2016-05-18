package main;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import file.ParseFile;
import gui.ResolutionSelection;
import object.PhysicalObject;
import render.Camera;

/** The main class in the application.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */

public class Start {
	public static ArrayList<PhysicalObject> renderList = new ArrayList<PhysicalObject>();
	public static ArrayList<PhysicalObject> physicsList = new ArrayList<PhysicalObject>();
	public static PhysicalObject camera;
	public static PhysicalObject lightSource;
	static File input;
	public static DisplayMode[] modes;
	public static DisplayMode displayMode;
	public static int height;
	public static int width;
	
	public static void addToRenderList(PhysicalObject p_1) {
		renderList.add(p_1);
	}
	public static void addToPhysicsList(PhysicalObject physicalObject) {
		physicsList.add(physicalObject);
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
	 * "type"     : "Sphere"<br>
	 * "location" : [x,y,z]<br>
	 * "size"     : [x,y,z]<br>
	 * "rotation" : [x,y,z]<br>
	 * <br>
	 * Basic Example:<br>
	 * <pre>
*[
*	{
*		"velocity" : [0,0,50],
*		"mass"     : 1,
*		"color"    : [255,255,0],
*		"shape"    : {
*						"type" : "Sphere",
*						"location" : [0, 0, 1000],
*						"radius"   : 100
*					}
*	},
*	{
*		"velocity" : [0,50,0],
*		"mass"     : 1,
*		"color"    : [255,0,0],
*		"shape"    : {
*						"type" : "RectangularPrism",
*						"location" : [0, 200, 1500],
*						"size" : [100, 100, 100],
*						"rotation" : [0, 0, 0]
*					}
*	}
*] 
	 *</pre><br>
	 */
	public static void openSceneFile(){
		//File chooser dialog
		JFileChooser window = new JFileChooser();
		window.showOpenDialog(window);
		input = window.getSelectedFile();
		
		JSONObject scene = new JSONObject(ParseFile.parseFile(input.getPath()));
		
		camera = new PhysicalObject(scene.getJSONObject("camera"));
		lightSource = new PhysicalObject(scene.getJSONObject("lightSource"));
		
		//Create objects from file
		JSONArray set = scene.getJSONArray("objects");
		for(int i = 0; i<set.length();i++){
			new PhysicalObject(set.getJSONObject(i));
		}
	}
	
	
	public static void buildScreen(){
		//Builds the screen
		try{
			Display.setDisplayMode(displayMode);
			Display.setFullscreen(false);
			Display.create();
		} catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
			
		//I have no idea what this does
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	public static void chooseResolution(){
		try {
			modes = Display.getAvailableDisplayModes();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		displayMode = (new ResolutionSelection()).getDisplayMode();
		
		height = displayMode.getHeight();
		width  = displayMode.getWidth();
	}
	
	public static void loop() {
		//Loop until the exit button is pressed
		while (!Display.isCloseRequested()) {
			//Move
			for(PhysicalObject t_1: physicsList){
				t_1.move();
			}
			
			// I have no idea what this does...
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
            // render OpenGL here
			((Camera) camera.getShape()).render();
			//System.out.println("Done rendering");
             
			//Update display
            Display.update();
        }
        
		//Exit the window
        Display.destroy();
	}
	
	public static void main(String[] args) {
		chooseResolution();
		
		openSceneFile();
		
		/* Fun Colors
		renderList.get(0).getTexture().defineSidesColor(2, Color.BLUE);
		renderList.get(0).getTexture().defineSidesColor(1, Color.GREEN);
		renderList.get(0).getTexture().defineSidesColor(5, Color.YELLOW);
		renderList.get(0).getTexture().defineSidesColor(4, Color.DARK_GRAY);
		renderList.get(0).getTexture().defineSidesColor(3, Color.WHITE); */
		
		
		buildScreen();
		
		//Begins the game loop
		loop();
	}
}

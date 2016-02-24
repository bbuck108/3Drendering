package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Loop {
	public static void loop(){
		while (!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
            // render OpenGL here
			Start.camera.render();
			//System.out.println("Done rendering");
             
			//Update display
            Display.update();
        }
         
        Display.destroy();
	}
}

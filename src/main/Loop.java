package main;

import org.lwjgl.opengl.Display;

public class Loop {
	public static void loop(){
		while (!Display.isCloseRequested()) {
            
            // render OpenGL here
             
            Display.update();
        }
         
        Display.destroy();
	}
}

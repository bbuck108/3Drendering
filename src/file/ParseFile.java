package file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Handles file inputs.
 * 
 * @author Benjamin Buck and Connor Lehmacher
 *
 */
public class ParseFile {
	public static String parseFile(String p_1){
		String output = "";
		try(FileReader fileReader =
			    new FileReader(p_1)){

			    int data = fileReader.read();
			    while(data != -1) {
			        output=output+(char)data;
			        data = fileReader.read();
			    }
			} catch (FileNotFoundException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		return output;
	}
}

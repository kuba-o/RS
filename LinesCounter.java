import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinesCounter {
	public int calculateLines(String name){
		int lines = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(name));
			while (reader.readLine() != null){
				lines++;
			}
			reader.close();
			return lines;
		} catch (IOException e) {
			e.printStackTrace();
		}
	return lines;	
	}
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CoordsReader {

	public static void readCoords(ArrayList<Coord> list, String name, int numberOfLines) {
		BufferedReader br = null;

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(name));
			for (int i=0; i<numberOfLines; i++){
				sCurrentLine = br.readLine();
				String[] ar=sCurrentLine.split(",");
				list.add(new Coord(ar[0], Integer.parseInt(ar[1]), Integer.parseInt(ar[2]), Integer.parseInt(ar[3]), ar[4].charAt(0)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
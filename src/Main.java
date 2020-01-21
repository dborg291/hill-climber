import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		ElevationLandscape landscape = new ElevationLandscape(100, 100, 30, 1000, 3);
		landscape.initialize();
		landscape.printMap();

		
		PrintWriter writer = new PrintWriter("landscape.txt", "UTF-8");
		writer.println(landscape.width);
		writer.println(landscape.height);
		
		for (int i = 0; i < landscape.map.length; i++) {
			for (int j = 0; j < landscape.map[0].length; j++) {
				writer.print(landscape.map[i][j] + " ");
			}
		}
		writer.close();
	}

}

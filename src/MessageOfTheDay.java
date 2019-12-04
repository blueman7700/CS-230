import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MessageOfTheDay {
	static String baseUrl = "http://cswebcat.swan.ac.uk/message?solution=";
	static String PUZZLE_URL = "http://cswebcat.swan.ac.uk/puzzle";
	
	private static String solve() throws Exception {
		ArrayList<Integer> puzzle = getPuzzle(PUZZLE_URL);
		String finalStr= "";
		boolean odd = true;
		for(int i = 0; i < puzzle.size();i++) {
			if(puzzle.get(i) == 65 && !odd) {
				finalStr += "Z";
			}else if(puzzle.get(i) == 90 && odd) {
				finalStr += "A";
			}else if(odd) {
				finalStr += (char) (puzzle.get(i) + 1);
			}else {
				finalStr += (char) (puzzle.get(i) - 1);
			}
			odd = !odd;
		}
		return finalStr;
		
	}
	
	private static ArrayList<Integer> getPuzzle(String urlText) throws Exception{
		ArrayList<Integer> puzzle = new ArrayList<Integer>();
		int currBit;
		URL url = new URL(urlText);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		InputStream in = con.getInputStream();
		currBit = in.read();
		while(currBit >=65 && currBit <= 90) {
			puzzle.add(currBit);
			currBit = in.read();
			//System.out.println();
		}
		
		return puzzle;
	}
	
	public static String getMessage() throws Exception {
		String extension = solve();
		String finalStr = "";
		int currBit;
		//System.out.println(baseUrl + extension);
		URL url = new URL(baseUrl + extension);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		InputStream in = con.getInputStream();
		currBit = in.read();
		while(currBit >0) {
			finalStr += (char) currBit;
			//System.out.println(currBit);
			currBit = in.read();
		}
		return finalStr;
	}
}

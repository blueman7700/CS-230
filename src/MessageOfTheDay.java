import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Handles getting the message of the day from external websites.
 * @author Nathan Hardy
 * @version 1.0
 */
public class MessageOfTheDay {
	static String BASE_URL = "http://cswebcat.swan.ac.uk/message?solution=";
	static String PUZZLE_URL = "http://cswebcat.swan.ac.uk/puzzle";

	/**
	 * solve the puzzle to get the message URL extension.
	 *
	 * @return message URL extension.
	 * @throws Exception
	 */
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

	/**
	 * get the puzzle from the web server.
	 *
	 * @param urlText puzzle URL.
	 * @return puzzle to be solved.
	 * @throws Exception
	 */
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
		}
		in.close();
		return puzzle;
	}

	/**
	 * get the message from the web server.
	 *
	 * @return message The message from the server
	 * @throws Exception cant get message
	 */
	public static String getMessage() throws Exception {
		String extension = solve();
		String finalStr = "";
		int currBit;
		URL url = new URL(BASE_URL + extension);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		InputStream in = con.getInputStream();
		currBit = in.read();
		while(currBit >0) {
			if(currBit == 226) {
				finalStr += "'";
			}else {
				finalStr += (char) currBit;
			}
			currBit = in.read();
		}
		in.close();
		return finalStr;
	}
}

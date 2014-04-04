import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * Clasa Main
 * 
 * @author Andru
 * 
 */
public class Main {
	/**
	 * 
	 * Metoda main
	 * 
	 * @param args
	 *            Argumentele primite de program
	 */
	public static void main(String[] args) throws IOException {
		// dictionarul
		Dictionary d = new Dictionary(args[0]);
		// parserul
		Parser p = new Parser(d);

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		Array rez;

		// citim interogari
		while ((line = in.readLine()) != null && line.matches("exit") == false) {
			rez = p.execute(line);

			System.out.println(rez != null ? rez.toString() : "");
		}

	}
}

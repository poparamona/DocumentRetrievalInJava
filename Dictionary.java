import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * Clasa ce defineste tipul dictionar
 * 
 * @author Andru
 * 
 */
public class Dictionary {

	/**
	 * Numarul de intrari din dictionar
	 */
	private int numEntries = 0;

	/**
	 * Hash table cu perechi < cheie, valoare >
	 */
	private final HashTable data;

	/**
	 * Constructor pentru dictionar. Primeste ca parametru numele fisierului
	 * text (dictionarului) si il citeste linie cu linie
	 * 
	 * @param inputFile
	 *            Numele fisierului text
	 */
	public Dictionary(String inputFile) {
		this.data = new HashTable();

		try {
			File dictFile = new File(inputFile);
			Scanner reader = new Scanner(dictFile);
			String strLine;

			while (reader.hasNextLine()) {
				strLine = reader.nextLine(); // in strLine este linia curenta
												// din dictionar
				numEntries++;

				StringTokenizer st = new StringTokenizer(strLine);
				String key = st.nextToken();
				Array value = new Array(); // indicii asociati documentelor

				while (st.hasMoreElements())
					value.add(Integer.parseInt(st.nextToken()));

				data.insert(key, value); // adaugam o noua intrare in HashTable
			}

			reader.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Adauga o noua intrare in dictionar.
	 * 
	 * @param key
	 *            Cheie
	 * @param value
	 *            Valoare
	 */
	public void insert(String key, Array value) {
		data.insert(key, value);
	}

	/**
	 * Verifica daca o cheie data este prezenta in dictionar.
	 * 
	 * @param key
	 *            Cheia cautata
	 * @return true daca dictionarul contine cheia data, false alfel
	 */
	public boolean contains(String key) {
		return data.contains(key);
	}

	/**
	 * Intoarce valoarea asociata unei chei date
	 * 
	 * @param key
	 *            Cheia
	 * @return Valoarea corespunzatoarea cheii primite ca paramatru
	 */
	public Array getValue(String key) {
		return data.getValue(key);
	}

	/**
	 * Metoda ce returneaza numarul de intrari din dictionar
	 * 
	 * @return Numarul de intrari din dictionar
	 */
	public int getnumEntries() {
		return numEntries;
	}
}

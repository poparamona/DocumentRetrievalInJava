import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Clasa ce defineste tipul Parser. Parseaza si evalueaza o interogare data.
 * 
 * @author Ramona
 * 
 */
public class Parser {
	/**
	 * Un HashTable secundar in care se retin cheile si valorile temporare.
	 */
	private HashTable secHT;
	/**
	 * Expresia de parsat.
	 */
	private final String str;
	/**
	 * Dictionarul folosit.
	 */
	private final Dictionary dict;
	/**
	 * Generator de numere random.
	 */
	private final SecureRandom random;
	/**
	 * O expresie(interogare) elementara de forma (w1 op w2)
	 */
	private static final String pat = "\\([^ \\(]+ (or|and) [^ \\)]+\\)";
	/**
	 * Patternul unei expresii(interogari) elementare
	 */
	private static final Pattern pattern = Pattern.compile(pat);
	/**
	 * Operatorul "and"
	 */
	private static final String AND = "and";

	/**
	 * Constructorul clasei Parser. Primeste ca parametrii stringul de parsat si
	 * dictionarul folosit.
	 * 
	 * @param str
	 *            interogarea de parsat
	 * @param dict
	 *            dictionarul folosit
	 */
	public Parser(String str, Dictionary dict) {
		this.str = str;
		this.dict = dict;
		this.random = new SecureRandom();
		secHT = null;
	}

	/**
	 * Constructorul clasei Parser. Primeste ca parametru dictionarul folosit.
	 * 
	 * @param dict
	 *            dictionarul folosit
	 */
	public Parser(Dictionary dict) {
		this(null, dict);
	}

	/**
	 * Parseaza si evalueaza o interogare data.
	 * 
	 * @param str
	 *            expresia de parsat si evaluat
	 * @return rezultatul evaluarii
	 */
	public Array execute(String str) {
		return parse(str);
	}

	/**
	 * Parseaza si evalueaza o interogare data in constructor.
	 * 
	 * @return rezultatul evaluarii
	 */
	public Array execute() {
		if (str != null)
			return execute(str);
		else
			return null;
	}

	/**
	 * Parseaza evalueaza o expresie data. Este apelata de this.execute()
	 * 
	 * @param str
	 *            expresia
	 * @return rezultatul evaluarii
	 */
	private Array parse(String str) {
		Matcher matcher = pattern.matcher(str);
		secHT = new HashTable();

		// Cat timp gasim expresii elementare
		while (matcher.find()) {

			// Prima expresie gasita
			String expr = matcher.group(); // expresia elementara
			String repl = randomEntry(); // o cheie random
			str = str.replaceFirst(pat, repl); // inlocuim expresia cu cheia
												// random

			secHT.insert(repl, eval(expr)); // inseram in dictionar perechea
											// < cheie random, rezultat
											// evaluare expresie >

			matcher = pattern.matcher(str); // reevaluam expresia rezultata
		}

		// Expresia finala contine o interogare elementara
		Array rez = secHT.getValue(str);
		if (rez == null)
			rez = dict.getValue(str);

		return rez;
	}

	/**
	 * Metoda ce evalueaza o expresie elementara de forma (w1 op w2).
	 * 
	 * @param expr
	 *            Expresia de evaluat
	 * @return rezultatul evaluarii
	 */
	private Array eval(String expr) {
		StringTokenizer st = new StringTokenizer(expr);
		String w1 = st.nextToken(); // primul cuvant
		String op = st.nextToken(); // operatorul
		String w2 = st.nextToken(); // al doilea cuvant

		w1 = w1.substring(1); // eliminam
		w2 = w2.substring(0, w2.length() - 1); // parantezele

		Array m1 = dict.getValue(w1);
		if (m1 == null)
			m1 = secHT.getValue(w1);
		Array m2 = dict.getValue(w2);
		if (m2 == null)
			m2 = secHT.getValue(w2);

		if (op.matches(AND))
			return intersection(m1, m2);
		else
			return reunion(m1, m2);
	}

	/**
	 * Metoda ce realizeaza intersectia a doi vectori
	 * 
	 * @param m1
	 *            primul vector
	 * @param m2
	 *            al doilea vector
	 * @return intersectia vectorilor dati
	 */
	private Array intersection(Array m1, Array m2) {

		if (m1 == null || m2 == null)
			return null;

		return m1.intersect(m2);
	}

	/**
	 * Metoda ce realizeaza reuniunea a doi vectori
	 * 
	 * @param m1
	 *            primul vector
	 * @param m2
	 *            al doilea vector
	 * @return reuniunea vectorilor dati
	 */
	private Array reunion(Array m1, Array m2) {
		if (m1 == null)
			return m2;

		return m1.reunion(m2);
	}

	/**
	 * Metoda ce genereaza o cheie unica.
	 * 
	 * @return o cheie unica
	 */
	private String randomEntry() {
		String s;

		do
			s = (new BigInteger(128, random).toString(32));
		while (dict.contains(s));

		return s;
	}
}

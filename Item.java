/**
 * 
 * Clasa ce defineste tipul Item ca pereche < cheie, valoare >
 * 
 * @author Ramona
 * 
 */
public class Item {
	/**
	 * Cheia
	 */
	private String key;
	/**
	 * Valoarea
	 */
	private Array value;

	/**
	 * Constructor pentru Item. Primeste ca paramentrii cheia si valoarea
	 * asociata.
	 * 
	 * @param key
	 *            Cheie
	 * @param value
	 *            Valoare
	 */
	public Item(String key, Array value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Metoda pentru setarea cheii.
	 * 
	 * @param key
	 *            Noua cheie
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Metoda pentru setarea valorii.
	 * 
	 * @param value
	 *            Noua valoare
	 */
	public void setValue(Array value) {
		this.value = value;
	}

	/**
	 * Metoda pentru accesarea cheii.
	 * 
	 * @return Cheia asociata
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Metoda pentru accesarea valorii.
	 * 
	 * @return Valoarea asociata
	 */
	public Array getValue() {
		return value;
	}
}

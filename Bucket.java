/**
 * 
 * Clasa ce defineste tipul Bucket. Implementat ca un vector de obiecte Item.
 * 
 * @author Ramona
 * 
 */
public class Bucket {
	/**
	 * intrarile din bucket
	 */
	private Item data[];
	/**
	 * dimensiunea
	 */
	private int size;
	/**
	 * capacitatea
	 */
	private int cap;
	/**
	 * Capacitatea default folosita la initializare
	 */
	protected static final int DEFAULT_CAP = 32;

	/**
	 * Constructor pentru Bucket. Primeste ca parametru capacitatea initiala
	 * 
	 * @param cap
	 *            capacitatea
	 */
	public Bucket(int cap) {
		this.cap = cap;
		this.size = 0;
		this.data = new Item[cap];
	}

	/**
	 * Constructor pentru HashTable. Foloseste capacitatea default.
	 * 
	 * @param cap
	 *            capacitatea
	 */
	public Bucket() {
		this(DEFAULT_CAP);
	}

	/**
	 * Adauga un nou element in bucket.
	 * 
	 * @param it
	 *            elementul de adaugat
	 */
	public void add(Item it) {
		if (size + 1 > cap)
			resize();

		data[size++] = it;
	}

	/**
	 * Returneaza perechea de pe o pozitie data.
	 * 
	 * @param index
	 *            pozitia
	 * @return perechea ceruta
	 */
	public Item get(int index) {
		return data[index];
	}

	/**
	 * Returneaza dimensiunea acestui bucket (numarul de intrari)
	 * 
	 * @return dimensiunea
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Intoarce valoarea asociata unei chei date.
	 * 
	 * @param key
	 *            cheia
	 * @return valoarea asociata sau null daca aceasta nu este gasita
	 */
	public Array getValue(String key) {
		if (size == 0)
			return null;

		// parcurgem toate intrarile din bucket-ul asociat
		for (int i = 0; i < size; i++)
			if (data[i].getKey().equals(key))
				return data[i].getValue();

		return null;

	}

	/**
	 * Mareste capacitatea
	 */
	private void resize() {
		Item newData[] = new Item[cap * 2];

		System.arraycopy(data, 0, newData, 0, size);

		cap *= 2;
		data = newData;
	}
}

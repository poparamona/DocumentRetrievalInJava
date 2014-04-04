/**
 * 
 * Clasa ce defineste tipul HashTable. Implementat ca un vector de tipul Bucket.
 * 
 * @author Ramona
 * 
 */
public class HashTable {
	/*
	 * la depasirea acestei valori (num/cap) marim capacitatea pentru a micsora
	 * numarul de coliziuni
	 */
	private static final double FILL_RATIO = 0.75;

	/*
	 * capacitatea default
	 */
	private static final int DEFAULT_CAP = 512;

	/*
	 * factorul de resize pentru capacitate
	 */
	private static final int RESIZE_FACTOR = 2;

	/*
	 * intrarile din hash table
	 */
	private Bucket data[];

	/*
	 * capacitatea
	 */
	private int cap;

	/*
	 * numarul de intrari
	 */
	private int num;

	/**
	 * Constructor pentru HashTable. Primeste ca parametru capacitatea initiala
	 * 
	 * @param cap
	 *            capacitatea
	 */
	public HashTable(int cap) {
		this.cap = cap;
		num = 0;
		data = new Bucket[cap];
	}

	/**
	 * Constructor pentru HashTable ce foloseste capacitatea default
	 */
	public HashTable() {
		this(DEFAULT_CAP);
	}

	/**
	 * Insereaza o noua intrare
	 * 
	 * @param key
	 *            cheia
	 * @param value
	 * 
	 *            valoarea asociata
	 */
	public void insert(String key, Array value) {
		if ((num + 1.0) / cap >= FILL_RATIO) // marim capacitatea
			resize();

		Integer hash = Math.abs(key.hashCode()) % cap; // valoarea de hash
		Bucket b = data[hash]; // bucket-ul destinatie

		if (b == null) {
			// initializam bucket-ul destinatie
			b = new Bucket();
			data[hash] = b;
		}

		num++; // numarul de intrari din HashTable
		b.add(new Item(key, value)); // adaugam in bucket
	}

	/**
	 * Intoarce valoarea asociata unei chei date
	 * 
	 * @param key
	 *            cheia
	 * @return valoarea asociata
	 */
	public Array getValue(String key) {
		Integer hash = Math.abs(key.hashCode()) % cap;
		Bucket b = data[hash];

		if (b == null)
			return null;

		return b.getValue(key);
	}

	/**
	 * Verifica prezenta unei chei date
	 * 
	 * @param key
	 *            cheia
	 * @return true sau false
	 */
	public boolean contains(String key) {
		return getValue(key) != null;
	}

	/**
	 * Insereaza o pereche (cheie, valoare) in HashTable. Metoda privata
	 * folosita la resize()
	 * 
	 * @param it
	 *            perechea
	 */
	private void insert(Item it) {
		Integer hash = Math.abs(it.getKey().hashCode()) % cap;
		Bucket b = data[hash];

		if (b == null) {
			b = new Bucket();
			data[hash] = b;
		}

		num++;
		b.add(it);
	}

	/**
	 * Mareste capacitatea
	 */
	private void resize() {
		Bucket oldData[] = data;
		int oldCap = cap;
		cap *= RESIZE_FACTOR;
		num = 0;
		data = new Bucket[cap];

		// // reindexam intrarile
		// pentru fiecare bucket
		for (int i = 0; i < oldCap; i++)
			if (oldData[i] != null) {
				Bucket b = oldData[i];
				// pentru fiecare pereche din bucket
				for (int j = 0; j < b.getSize(); j++) {
					Item it = b.get(j);
					insert(it);
				}
			}
	}
}

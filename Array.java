/**
 * 
 * Clasa ce defineste tipul Array. Faciliteaza lucrul cu vectori de intregi
 * sortati crescator. Operatiile de intersectie si reuniune a doi vectori
 * pastreaza ordonarea valorilor.
 * 
 * @author Ramona
 * 
 */
public class Array {
	/**
	 * Vector
	 */
	protected int data[];
	/**
	 * Dimensiunea vectorului
	 */
	private int size;
	/**
	 * Capacitatea maxima a vectorului
	 */
	protected int cap;
	/**
	 * Capacitatea default folosita la initializare
	 */
	protected static final int DEFAULT_CAP = 32;

	/**
	 * Constructor pentru Array. Primeste ca parametru capacitatea vectorului.
	 * 
	 * @param cap
	 *            Capacitatea vectorului
	 */
	public Array(int cap) {
		this.cap = cap;
		this.size = 0;
		data = new int[cap];
	}

	/**
	 * Constructor pentru Array ce foloseste capacitatea default pentru
	 * initializarea.
	 */
	public Array() {
		this(DEFAULT_CAP);
	}

	/**
	 * Adauga o noua intrarea in vector.
	 * 
	 * @param v
	 *            valoarea de adaugat
	 */
	public void add(int v) {
		if (size + 1 > cap)
			resize(cap * 2);

		data[size++] = v;
	}

	/**
	 * Intoarce valoarea de pe o pozitie data.
	 * 
	 * @param index
	 *            Pozitia in vector
	 * @return Valoarea de pe pozitia data ca parametru
	 */
	public int get(int index) {
		return data[index];
	}

	/**
	 * Returneaza dimensiunea vectorului curent.
	 * 
	 * @return Dimensiunea vectorului
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Cauta binar o valoare data. Daca aceasta nu exista intoarce pozitia unde
	 * ar trebui adaugata pentru a mentine valorile ordonate crescator.
	 * 
	 * @param key
	 *            valoarea cautata
	 * @return pozitia valorii cautate sau pozitia pentru adaugat
	 */
	private int binSearch(int key) {
		int lo = 0;
		int hi = size - 1;
		int mid = 0;

		if (size == 0)
			return 0;

		while (lo <= hi) {
			mid = lo + (hi - lo) / 2;
			if (key < data[mid])
				hi = mid - 1;
			else if (key > data[mid])
				lo = mid + 1;
			else
				return mid;
		}

		if (mid == 0 && key > data[mid])
			return 1;

		if (mid == size - 1 && key > data[mid])
			return size;

		return mid;
	}

	/**
	 * Verifica prezenta unei valori data in vector
	 * 
	 * @param el
	 *            valoarea cautata
	 * @return true sau false
	 */
	public boolean binContains(int el) {
		if (size == 0)
			return false;

		int poz = binSearch(el);
		if (poz == size)
			return false;

		if (data[poz] == el)
			return true;
		return false;
	}

	/**
	 * Metoda ce realizeaza intersectia a doi vectori folosind cautare binara.
	 * 
	 * @param m1
	 *            primul vector
	 * @param m2
	 *            al doilea vector
	 * @return intersectia vectorilor dati
	 */
	public Array intersect(Array v2) {
		if (v2 == null)
			return null;

		Array rez = new Array();
		for (int i = 0; i < size; i++) {
			int el = get(i);
			if (v2.binContains(el))
				rez.add(el);
		}

		return rez;
	}

	/**
	 * Metoda ce realizeaza reuniunea a doi vectori prin interclasare.
	 * 
	 * @param m1
	 *            primul vector
	 * @param m2
	 *            al doilea vector
	 * @return reuniunea vectorilor dati
	 */
	public Array reunion(Array v2) {
		Array rez = new Array();
		int i = 0, j = 0;

		if (v2 != null)
			while (i < size && j < v2.getSize())
				if (data[i] < v2.get(j)) {
					rez.add(data[i]);
					i++;
				} else {
					if (data[i] > v2.get(j))
						rez.add(v2.get(j));
					j++;
				}

		while (i < size)
			rez.add(data[i++]);

		if (v2 != null)
			while (j < v2.getSize())
				rez.add(v2.get(j++));

		return rez;
	}

	/**
	 * Redimensiuneaza vectorul curent. Primeste ca parametru noua capacitate
	 * dorita.
	 * 
	 * @param newCap
	 *            Capacitatea dorita.
	 */
	private void resize(int newCap) {
		int newData[] = new int[newCap];

		System.arraycopy(data, 0, newData, 0, size);

		cap = newCap;
		data = newData;
	}

	/**
	 * Metoda ce intoarce reprezentarea unui obiect de tipul Array sub forma de
	 * String.
	 * 
	 * @return Reprezentarea obiectului curent ca String
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < size; i++)
			s += data[i] + " ";

		return s;
	}
}

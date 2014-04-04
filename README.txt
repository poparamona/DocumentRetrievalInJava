Nume: Popa Ramona
grupa: 322CB

Structura de date folosita (implementata) in rezolvarea acestei teme este HashTable.
Detaliile de implementare sunt prezente in descrierea de mai jos a claselor folosite.

Proiectul contine urmatoarele clase:
	* Array - clasa ce defineste tipul Array: un vector liniar de intregi.
	Constructorul poate primi ca parametru capacitatea vectorului, alfel va folosi o valoare predefinita.
	Metodele puse la dispozitie pentru lucrul cu vectori sunt: add, get, binContains,
	getSize, intersect, reunion.
	Operatiile de intersectie si reuniune returneaza un vector sortat crescator ca rezultat (bineinteles,
	cei doi vectori initiali trebuie sa fie si ei sortati). Pentru intersectie am folosit cautarea binara
	iar pentru reuniune algoritmul clasic de interclasare.

	* Item - clasa ce defineste tipul Item: o pereche (cheie, valoare), cheia fiind de tipul String iar
	valoarea Array.
	Metode publice: setKey, setValue, getKey, getValue.

	* Bucket - clasa ce defineste tipul Bucket: un vector liniar cu elemente de tipul Item. Un Bucket
	contine perechile cu aceeasi valoare de hash pentru chei.
	Metode publice: add, get, getSize, getValue.

	* HashTable - clasa ce defineste tipul HashTable: un vector liniar cu elemente de tipul Bucket.
	Metode publice: insert, getValue, contains.

	La inserarea unei noi intrari in HashTable:
			- calculez valoarea de hash (hashValue) pentru cheia data:
					Math.abs(key.hashCode()) % capacitate
			- daca factorul de fill (numarul de intrari/capacitate) este mai mare decat o
			valoare prestabilita (0.75) marim capacitatea si reindexam intrarile deja prezente.
			Daca am folosi o capacitate fixa am avea multe coliziuni (putine Bucket-uri dar de
			dimensiuni mari) iar performanta ar fi puternic afectata.
			- inseram noua intrare in Bucketul corespunzator

	* Dictionary - clasa ce defineste tipul dictionar. Completari fata de scheletul de cod primit:
			- datele sunt retinute intr-un
			- pune la dispozitie urmatoarele metode publice: insert, contains, getValue.

	* Parser - clasa ce defineste tipul Parser: pe baza unui dictionar parseaza si evalueaza o interogare.
	Constructorul primeste ca parametru dictionarul folosit. Singura metoda publica oferita este
	'public Array execute(String str)' ce primeste o interogare si intoarce rezultatul evaluarii.
	Parsarea si evaluare:
			- folosesc Matcher si Pattern din java.util.regex
			- o expresie elementara este de forma (w1 op w2) fiind descrisa de un obiect Pattern
			- cat timp interogarea contine cel putin o expresie elementara:
					- evaluam expresia (rezEval)
					- generam o cheie unica folosind java.security.SecureRandom
					- in stringul evaluat inlocuim expresia elementara cu cheia unica
					- inseram intr-un dictionar temporar perechea (cheie_unica, rezEval)
			- la final interogarea va contine un singur cuvant cheie, rezultatul fiind valoarea
			asociata in dictionar
			- stergem cheile unice si valorile asociate in pasii anteriori din dictionar

	* Main - clasa in care se gaseste metoda main. Contine un obiect de tipul Dictionary si unul de tip
	Parser. Pe baza acestora evaluam interogarile primite de la System.in

Pe sistemul meu am obtinut urmatorii timpi la testare: test7 -> 4s, test8 -> 6s
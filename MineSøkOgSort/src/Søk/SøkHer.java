package S�k;

public class S�kHer {

	/*************************************************************************************************/
	// S�kealgoritmer
	/*************************************************************************************************/

	/**
	 * Metoden gj�r et line�rt s�k i en tabell av usorterte data
	 * 
	 * @param data er data som skal s�kes i
	 * @param min  er startindeks
	 * @param maks er sluttindeks
	 * @param el   er verdien vi s�ker etter
	 * @return sann om elementet ble funnet ellers usann
	 */
	public static <T extends Comparable<T>> boolean linearSoekU(T[] data, int min, int maks, T el) {
		int indeks = min;
		boolean funnet = false;

		while (indeks <= maks && !funnet) {
			if (data[indeks].compareTo(el) == 0)
				funnet = true;
			indeks++;
		} // while
		return funnet;
	}// metode

	/**
	 * Metoden gj�r et line�rt s�k i en sortert tabell av data
	 * 
	 * @param data er data som skal s�kes i
	 * @param min  er startindeks
	 * @param maks er sluttindeks
	 * @param el   verdien vi s�ker etter
	 * @return sann hvis funnet ellers usann
	 */
	public static <T extends Comparable<T>> boolean linearSoekS(T[] data, int min, int maks, T el) {
		int indeks = min;
		boolean funnet = false;

		while (indeks < maks && data[indeks].compareTo(el) < 0) {
			indeks++;
		} // while

		if (data[indeks].compareTo(el) == 0) {
			funnet = true;
		}

		return funnet;

	}// metode

	/**
	 * Metoden gj�r et bin�rt s�k i en *sortert* tabell av data
	 * 
	 * @param data er data som skal s�kes i
	 * @param min  er startindeks
	 * @param maks er sluttindeks
	 * @param el   er elementet vi s�ker etter
	 * @return sann om elementet ble funnet ellers usann.
	 */

	// Alt 1
	public static <T extends Comparable<T>> boolean binaerSoek(T[] data, int min, int maks, T el) {
		if (min > maks) { // basistilfelle, ingen element
			return false;
		}

		int midtpunkt = (min + maks) / 2;
		int resultat = el.compareTo(data[midtpunkt]);

		if (resultat == 0) { // basistilfelle, finner elementet
			return true;
		}
		if (resultat < 0) {// (her vil ogs� fungere med else if)
			return binaerSoek(data, min, midtpunkt - 1, el);
		} else { // resultat > 0
			return binaerSoek(data, midtpunkt + 1, maks, el);
		}
	}//

	// Alt 2
	public static <T extends Comparable<T>> boolean binaerSoek2(T[] data, int min, int maks, T el) {
		boolean funnet;
		int midtpunkt = (min + maks) / 2;
		int resultat = el.compareTo(data[midtpunkt]);

		if (min > maks) { // basistilfelle, ingen element
			funnet = false;
		} else if (resultat == 0) { // basistilfelle, funnet
			funnet = true;
		} else if (resultat < 0) {
			funnet = binaerSoek2(data, min, midtpunkt - 1, el);
		} else {
			funnet = binaerSoek2(data, midtpunkt + 1, maks, el);
		}

		return funnet;

	}

	/* Rekursiv bin�rs�k som returnerer indeks */
	// Alt 3
	public static <T extends Comparable<T>> int binaerSoek3(T[] data, int min, int maks, T el) {
		// Returnerer indeksen til m�lelementet hvis det fins ellers -1
		if (min > maks) { // basistilfelle, ingen element
			return -1;
		}

		int midtpunkt = (min + maks) / 2;
		int resultat = el.compareTo(data[midtpunkt]);
		if (resultat == 0) { // basistilfelle, finner elementet
			return midtpunkt;
		}
		if (resultat < 0) {// ( vil her ogs� fungere med else if)
			return binaerSoek3(data, min, midtpunkt - 1, el);
		} else { // resultat > 0
			return binaerSoek3(data, midtpunkt + 1, maks, el);
		}
	}// metode

	/* Ikke rekursiv bin�rs�k som returnerer indeks */
	// Alt 4
	public static <T extends Comparable<T>> int binaerSoek4(T[] data, int min, int maks, T el) {
		return maks;
		// Returnerer indeksen til m�lelementet hvis det fins ellers -1
		// Fyll ut og kj�r metoden
	}//

	/*************************************************************************************************/
	// Sorteringsalgoritmer
	/*************************************************************************************************/

}

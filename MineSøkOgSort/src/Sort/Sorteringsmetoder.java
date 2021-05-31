package Sort;

public class Sorteringsmetoder {
	/**
	 * Utvalgssortering
	 * 
	 * 
	 * @param data er data som skal sorteres
	 * 
	 */

	public static <T extends Comparable<T>> void utvalgsSortering(T[] data) {
		int minstePos;
		for (int i = 0; i < data.length - 1; i++) {
			minstePos = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j].compareTo(data[minstePos]) < 0) {
					minstePos = j;
				}
			}

			// byte om
			T tmp = data[i];

			data[i] = data[minstePos];

			data[minstePos] = tmp;
		}
	}

	public static <T extends Comparable<T>> void sorteringVedInnsetting(T[] data) {
		for (int i = 1; i < data.length; i++) {
			T nokkel = data[i];
			int p = i;

			while (p > 0 && nokkel.compareTo(data[p - 1]) < 0) {
				data[p] = data[p - 1];
				p--;
			}

			data[p] = nokkel;
		}
	}

	public static <T extends Comparable<T>> void bobleSortering(T[] data) {
		for (int siste = data.length - 1; siste > 0; siste--) {
			for (int i = 0; i < siste; i++) {
				if (data[i].compareTo(data[i + 1]) > 0) {
					T tmp = data[i];
					data[i] = data[i + 1];
					data[i + 1] = tmp;
				}
			}
		}

	}

	private static <T> void swap(T[] tab, int i, int j) {
		T tmp = tab[i];
		tab[i] = tab[j];
		tab[j] = tmp;
	}

	private static <T extends Comparable<T>> int finnPartisjon(T[] data, int min, int maks) {
		T temp, pivot;
		int midten = (min + maks) / 2;
		pivot = data[midten];
		swap(data, midten, min); // bytter om første og midtelementet

		int venstre = min + 1;
		int hoyre = maks;
		while (venstre < hoyre) {// ytre løkke

			/** Søker et element som er > enn pivot */
			/** Sikrer at partisjoneringsprosessen vil fortsette så lenge venstre < hoyre */
			while (venstre < hoyre && data[venstre].compareTo(pivot) <= 0) {
				venstre++;
			}

			/** Søker et element som er <= enn pivot */
			while (data[hoyre].compareTo(pivot) > 0) {
				hoyre--;
			}

			/** bytter elementene desom venstre er mindre enn hoyre */
			if (venstre < hoyre) {
				swap(data, venstre, hoyre);
			}

		} // while – ytre løkke

		/** flytter pivot til riktig og sin endelige plass */

		swap(data, min, hoyre);

		return hoyre;

	}// metode

	public static <T extends Comparable<T>> void kvikkSort(T[] data, int min, int maks) {

		// basis: 0 eller 1 element -> gjer ingenting

		if (min < maks) { // minst to element
			int posPivot = finnPartisjon(data, min, maks);
			// sorter venstre del
			kvikkSort(data, min, posPivot - 1);
			// sorter høgre del
			kvikkSort(data, posPivot + 1, maks);
		}
	}

	private static <T extends Comparable<T>> void flette(T[] tabell, int forste, int midten, int siste) {
		/*
		 * Fletter to sorterte deltabeller, tabell[forste,midten] og
		 * tabell[midten+1,siste] til en sortert tabell Forkrav: forste <= midten <=
		 * siste Deltabellene tabell[forste … midten] og tabell[midten+1 … siste] er
		 * hver sorterte i ikke- avtagende rekkefølge.
		 * 
		 * ResultatTabell[forste … siste] er sortert.
		 * 
		 * Implementasjon : Denne metoden fletter to deltabeller til en hjelpetabell og
		 * kopierer resultatet til den originale tabellen.
		 */

		int storrelse = siste - forste + 1;
		T[] hjelpeTabell = (T[]) (new Comparable[storrelse]);

		int forsteV = forste;
		int sisteV = midten;
		int forsteH = midten + 1;
		int sisteH = siste;

		// indeks i hjelp
		int h = 0;

		while (forsteV <= sisteV && forsteH <= sisteH) {
			if (tabell[forsteV].compareTo(tabell[forsteH]) <= 0) {
				hjelpeTabell[h] = tabell[forsteV];
				forsteV++;
			} else {
				hjelpeTabell[h] = tabell[forsteH];
				forsteH++;
			}
			h++;
		}

		// kopiere resten av venstre del (kan være tom)
		while (forsteV <= sisteV) {
			hjelpeTabell[h] = tabell[forsteV];
			forsteV++;
			h++;
		} // while

		// kopiere resten av høyre del (kan være tom)
		while (forsteH <= sisteH) {
			hjelpeTabell[h] = tabell[forsteH];
			forsteH++;
			h++;
		} // while

		// Kopier resultatet tilbake til den originale tabellen
		h = 0;
		for (int indeks = forste; indeks <= siste; indeks++) {
			tabell[indeks] = hjelpeTabell[h];
			h++;
		}
	}// flette */

	public static <T extends Comparable<T>> void fletteSort(T[] tabell, int forste, int siste) {
		// basis: 1 element - > gjer ingenting
		if (forste < siste) { // minst to element
			int midten = (forste + siste) / 2;
			fletteSort(tabell, forste, midten);
			fletteSort(tabell, midten + 1, siste);
			flette(tabell, forste, midten, siste);
		}
	}

	private static <T extends Comparable<T>> void merge(T[] L, T[] R, T[] A) {

		int nL = L.length;
		int nR = R.length;

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < nL && j < nR) {
			if (L[i].compareTo(R[j]) < 0) {
				A[k] = L[i];
				k++;
				i++;
			} else {
				A[k] = R[j];
				k++;
				j++;
			}
		}

		while (i < nL) {
			A[k] = L[i];
			k++;
			i++;
		}
		while (j < nR) {
			A[k] = R[j];
			k++;
			j++;
		}

	}// merge */

	public static <T extends Comparable<T>> void mergeSort(T[] A) {
		int n = A.length;
		if (n < 2) {
			return;
		}

		int mid = n / 2;
		T[] left = (T[]) (new Comparable[mid]);
		T[] right = (T[]) (new Comparable[n - mid]);

		for (int i = 0; i < mid; i++) {
			left[i] = A[i];
		}
		for (int i = 0; i < n - mid; i++) {
			right[i] = A[mid + i];
		}

		mergeSort(left);
		mergeSort(right);
		merge(left, right, A);
	}

	public static <T extends Comparable<T>> void quickSort(T[] A, int start, int end) {

		// basis: 0 eller 1 element -> gjer ingenting

		if (start < end) { // minst to element
			int posPivot = Partisjon(A, start, end);
			// sorter venstre del
			quickSort(A, start, posPivot - 1);
			// sorter høgre del
			quickSort(A, posPivot + 1, end);
		}
	}

	private static <T extends Comparable<T>> int Partisjon(T[] A, int start, int end) {

		T pivot = A[end];
		int pIndeks = start;

		for (int i = start; i < end; i++) {
			if (A[i].compareTo(pivot) <= 0) {
				swap(A, i, pIndeks);
				pIndeks++;

			}
		}

		swap(A, pIndeks, end);

		return pIndeks;
	}

	// FAIL
//	public static <T extends Comparable<T>> void mergeSort2(T[] A,int start, int end) {
//		int n = A.length;
//		if (n < 2) {
//			return;
//		}
//
//		int mid =  (start + end) / 2;
//		T[] left = (T[]) (new Comparable[mid]);
//		T[] right = (T[]) (new Comparable[n - mid]);
//
//		for (int i = 0; i < mid; i++) {
//			left[i] = A[start];
//		}
//		for (int i = 0; i < n - mid; i++) {
//			right[i] = A[mid + i];
//		}
//
//		mergeSort2(left,start,end);
//		mergeSort2(right,start,end);
//		merge2(left, right,2, A);
//	}
//	
//	private static <T extends Comparable<T>> void merge2(T[] L, T[] R,int start,  T[] A) {
//
//		int nL = L.length;
//		int nR = R.length;
//
//		int i = 0;
//		int j = 0;
//		int k = start;
//
//		while (i < nL && j < nR) {
//			if (L[i].compareTo(R[j]) < 0) {
//				A[k] = L[i];
//				k++;
//				i++;
//			} else {
//				A[k] = R[j];
//				k++;
//				j++;
//			}
//		}
//
//		while (i < nL) {
//			A[k] = L[i];
//			k++;
//			i++;
//		}
//		while (j < nR) {
//			A[k] = R[j];
//			k++;
//			j++;
//		}
//
//	}// merge */

	public static void countSort(Integer[] data, int maxValue) {
		int n = data.length;
		int[] countTble = new int[maxValue + 1];

		for (int i = 0; i < n; i++) {
			++countTble[data[i]];
		}

		for (int i = 1; i <= maxValue; i++) {
			countTble[i] = countTble[i] + countTble[i - 1];
		}
		int[] b = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			b[--countTble[data[i]]] = data[i];
		}

		for (int i = 0; i < n; i++) {
			data[i] = b[i];
		}

	}

	public static void radixSort(Integer[] data) {
		int n = data.length;
		int max = maks(data, 0, n - 1);

		for (int pos = 1; (max / pos) > 0; pos = (pos * 10)) {
			countSort2(data, pos);
		}
	}

	public static void countSort2(Integer[] data, int pos) {
		int n = data.length;
		int[] countTble = new int[10];

		for (int i = 0; i < n; i++) {
			++countTble[(data[i] / pos) % 10];
		}

		for (int i = 1; i <= 9; i++) {
			countTble[i] = countTble[i] + countTble[i - 1];
		}
		int[] b = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			b[--countTble[(data[i] / pos) % 10]] = data[i];
		}

		for (int i = 0; i < n; i++) {
			data[i] = b[i];
		}

	}

	static int maks(Integer[] tab, int start, int slutt) {
		if (start == slutt) {
			return tab[start];
		}

		else {
			int midten = (start + slutt) / 2;

			int maksVenstre = maks(tab, start, midten);
			int maksHogre = maks(tab, midten + 1, slutt);

			if (maksVenstre >= maksHogre) {
				return maksVenstre;
			} else {
				return maksHogre;
			}
		}
	}
	
//	// ingen spørsmål knyttet til denne metoden
//	public static <T extends Comparable<T>> void quickSort(T[] data) {
//	 quickSort(data, 0, data.length – 1);
//	}


	public static void main(String[] args) {
		Integer[] tab = { 7, 3, 1, 9, 2, 4 };
		int end = tab.length - 1;
		// utvalgsSortering(tab);
		// sorteringVedInnsetting(tab);
		// bobleSortering(tab);
		// fletteSort(tab, 0, tab.length - 1);
		// kvikkSort(tab, 0, tab.length - 1);

		// mergeSort(tab);
		quickSort(tab, 0, tab.length - 1);
		// countSort(tab, 9);
		radixSort(tab);

		for (Integer e : tab) {
			System.out.print(e + " ");
		}

		System.out.println();

	}
}

import java.util.Random;
import java.util.Collections;


class Run extends Template {

	public Run() {
		Random rand = new Random();
		double[] array = new double[1000];
		for (int i = 0; i < array.length; i++) array[i] = rand.nextDouble();

		// -------------------- Bubble Sort -------------------- //
		shuffle(array);
		Sorter.bubbleSort(array);

		// -------------------- Insertion Sort -------------------- //
		shuffle(array);
		Sorter.insertionSort(array);

		// -------------------- Merge Sort -------------------- //
		// shuffle(array);
		// Sorter.mergeSort(array);
        // STACK OVERFLOW...

		// -------------------- Quick Sort -------------------- //
		shuffle(array);
		Sorter.quickSort(array);

		// -------------------- Selection Sort -------------------- //
		shuffle(array);
		Sorter.selectionSort(array);

		// -------------------- Shell Sort -------------------- //
		shuffle(array);
		Sorter.shellSort(array);

		// -------------------- Heap Sort -------------------- //
		shuffle(array);
		Sorter.heapSort(array);

		// -------------------- Counting Sort -------------------- //
		shuffle(array);
		Sorter.countingSort(array);

		// -------------------- Radix Sort -------------------- //
		shuffle(array);
		Sorter.radixSort(array);

		// -------------------- Radix Iterative Sort -------------------- //
		shuffle(array);
		Sorter.radixIterativeSort(array);

		// -------------------- Bucket Sort -------------------- //
		shuffle(array);
		Sorter.bucketSort(array);
	}

	public static void shuffle(double[] array) {
		Random random = new Random();
		int count = array.length;
		for (int i = count; i > 1; i--) {
			swap(array, i - 1, random.nextInt(i));
		}
	}

	private static void swap(double[] array, int i, int j) {
		double temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
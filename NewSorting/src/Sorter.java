import java.util.Arrays;


public class Sorter {
	// ---------------------------------------- Bubble Sort ---------------------------------------- //

	public static void bubbleSort(double[] array) { // Method to Run
		for (int i = 0; i < array.length - 1; i++)
			for (int j = 0; j < array.length - i - 1; j++)
				if (array[j] > array[j + 1])
					swap(array, j, j + 1);
	}


	// ---------------------------------------- Insertion Sort ---------------------------------------- //

	public static void insertionSort(double[] array) { // Method to Run
		for (int i = 1; i < array.length; i++) {
			double temp = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > temp) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = temp;
		}
	}


	// ---------------------------------------- Merge Sort ---------------------------------------- //

	public static void mergeSort(double[] array) { // Method to Run
		int length = array.length;

		int middle = length / 2;
		double[] leftArray = new double[middle];
		double[] rightArray = new double[length - middle];

		int i = 0;
		int j = 0;

		for (; i < length; i++) {
			if (i < middle)
				leftArray[i] = array[i];
			else {
				rightArray[j] = array[i];
				j++;
			}
		}
		mergeSort(leftArray);
		mergeSort(rightArray);
		merge(leftArray, rightArray, array);
	}

	private static void merge(double[] leftArray, double[] rightArray, double[] array) {
		int leftSize = array.length / 2;
		int rightSize = array.length - leftSize;
		int i = 0, l = 0, r = 0;

		while (l < leftSize && r < rightSize) {
			if (leftArray[l] < rightArray[r]) {
				array[i] = leftArray[l];
				i++; l++;
			} else {
				array[i] = rightArray[r];
				i++; r++;
			}
		}
		while (l < leftSize) {
			array[i] = leftArray[l];
			i++; l++;
		}
		while (r < rightSize) {
			array[i] = rightArray[r];
			i++; r++;
		}
	}


	// ---------------------------------------- Quick Sort ---------------------------------------- //

	public static void quickSort(double[] array) { // Method to Run
		quickSort(array, 0, array.length - 1);
	}

	private static void quickSort(double[] array, int start, int end) {
		if (end <= start) return;

		int pivot = partition(array, start, end);
		quickSort(array, start, pivot - 1);
		quickSort(array, pivot + 1, end);
	}

	private static int partition(double[] array, int start, int end) {
		double pivot = array[end];
		int i = start - 1;

		for (int j = start; j <= end - 1; j++) {
			if (array[j] < pivot) {
				i++;
				double temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		i++;

		double temp = array[i];
		array[i] = array[end];
		array[end] = temp;
		return i;
	}

	// ---------------------------------------- Selection Sort ---------------------------------------- //

	public static void selectionSort(double[] array) { // Method to Run
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[min] > array[j])
					min = j;
			}

			double temp = array[i];
			array[i] = array[min];
			array[min] = temp;
		}
	}


	// ---------------------------------------- Shell Sort ---------------------------------------- //

	public static void shellSort(double[] array) { // Method to Run
		int h = 1;
		while (h <= array.length / 4) {
			h = h * 4 + 1;
		}

		while (h > 0) {
			for (int outer = h; outer < array.length; outer++) {
				double tmp = array[outer];
				int inner = outer;
				while (inner > h - 1 && array[inner - h ] >= tmp) {
					array[inner] = array[inner - h];
					inner -= h;
				}
				array[inner] = tmp;
			}
			h = (h - 1) / 4;
		}
	}


	// ---------------------------------------- Heap Sort ---------------------------------------- //

	public static void heapSort(double[] array) { // Method to Run
		buildMaxHeap(array);
		for (int i = array.length - 1; i > 0; i--) {
			swap(array, i, 0);
			seep(array, 0, i);
		}
	}
	// create maxHeap tree in array
	private static void buildMaxHeap(double[] arr) {
		for (int i = (arr.length / 2) - 1; i >= 0 ; i--) {
			seep(arr, i, arr.length);
		}
	}
	// seep - Downheap
	private static void seep(double[] arr, int i, int j) {

		while (i <= (j / 2) - 1) {

			// left child
			int childIndex = ((i + 1) * 2) - 1;
			// right child
			if (childIndex + 1 <= j - 1) {
				if (arr[childIndex] < arr[childIndex + 1]) {
					childIndex++;
				}
			}
			// check if seep is neccessary
			if (arr[i] < arr[childIndex]) {
				swap(arr, i, childIndex);
				i = childIndex;
			} else break;
		}
	}



	// ---------------------------------------- Counting Sort ---------------------------------------- //

	public static void countingSort(double[] array) { // Method to Run
		double max = array[0];
		for (int i = 1; i < array.length; i++)
			if (array[i] > max) max = array[i];

		int[] helpArray = new int[(int)max + 1];
		for (int i = 0; i < array.length; i++)
			helpArray[(int)array[i]]++;

		int pos = 0;
		for (int i = 0; i <= max; i++) {
			for (int j = 0; j < helpArray[i]; j++) {
				array[pos] = i;
				pos++;
			}
		}
	}


	// ---------------------------------------- Radix Sort ---------------------------------------- //

	public static void radixSort(double[] array) { // Method to Run
		double max = array[0];
		for (int i = 1; i < array.length; i++)
			if (array[i] > max) max = array[i];

		for (int factor = 1; max / factor > 0; factor *= 10)
			countingsort(array, factor);
	}

	private static void countingsort(double[] array, int factor) {
		int i = array.length;
		double[] output = new double[i];
		int a;
		int[] counting = new int[10];
		Arrays.fill(counting, 0);

		for (a = 0; a < i; a++)
			counting[ (int)(array[a] / factor) % 10 ]++;
		for (a = 1; a < 10; a++)
			counting[a] += counting[a - 1];

		for (a = i - 1; a >= 0; a--) {
			output[counting[ (int)(array[a] / factor) % 10 ] - 1] = array[a];
			counting[ (int)(array[a] / factor) % 10 ]--;
		}

		for (a = 0; a < i; a++)
			array[a] = output[a];
		System.arraycopy(output, 0, array, 0, i);
	}


	// ---------------------------------------- Radix Iterative Sort ---------------------------------------- //

	public static void radixIterativeSort(double[] array) { // Method to Run
		int index;
		double[] countSlot = new double[2];
		double[][] slot  = new double[2][array.length];
		for (int j = 0; j < 32; j++) {
			countSlot[0] = 0;
			countSlot[1] = 0;
			for (int k = 0; k < array.length; k++) {
				index = ((int) array[k] >> j) & 1;
				slot[index][(int) countSlot[index]++] = array[k];
			}
		}
		System.arraycopy(slot[0], 0, array, 0, (int) countSlot[0]);
		System.arraycopy(slot[1], 0, array, (int) countSlot[0], (int) countSlot[1]);
	}


	// ---------------------------------------- Bucket Sort ---------------------------------------- //

	public static void bucketSort(double[] array) { // Method to Run
		double max = array[0];
		for (int i = 1; i < array.length; i++)
			if (array[i] > max) max = array[i];
		double[] bucket = new double[(int)max + 1];

		for (int i = 0; i < array.length; i++)
			bucket[(int)array[i]]++;

		int outPos = 0;
		for (int i = 0; i < bucket.length; i++) {
			for (int j = 0; j < bucket[i]; j++) {
				array[outPos++] = i;
			}
		}
	}

	// ---- Swap ---- //

	private static void swap(double[] arr, int a, int b) {
		double k = arr[a];
		arr[a] = arr[b];
		arr[b] = k;
	}
}
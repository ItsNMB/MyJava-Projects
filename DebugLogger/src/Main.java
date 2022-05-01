import java.util.Random;


public class Main {
    private static Logger logger;

    public static void main(String args[]) {
        logger = new Logger();
        double lastTime = 0, passedTime = 0;
        int size = 500;
        int[] originalArray = new int[size];
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++)
            originalArray[i] = rand.nextInt(1000);
        System.arraycopy(originalArray, 0, array, 0, array.length);

        lastTime = System.nanoTime();
        mergeSort(array);
        passedTime = System.nanoTime() - lastTime;
        logf("Merge Sort took     [ %s ] ms", passedTime/1000000);
        System.arraycopy(originalArray, 0, array, 0, array.length);

        lastTime = System.nanoTime();
        selectionSort(array);
        passedTime = System.nanoTime() - lastTime;
        logf("Selection Sort took [ %s ] ms", passedTime/1000000);
        System.arraycopy(originalArray, 0, array, 0, array.length);

        lastTime = System.nanoTime();
        bubbleSort(array);
        passedTime = System.nanoTime() - lastTime;
        logf("Bubble Sort took    [ %s ] ms", passedTime/1000000);
        System.arraycopy(originalArray, 0, array, 0, array.length);

        lastTime = System.nanoTime();
        insertionSort(array);
        passedTime = System.nanoTime() - lastTime;
        logf("Insertion Sort took [ %s ] ms", passedTime/1000000);
        System.arraycopy(originalArray, 0, array, 0, array.length);
    }

    

    private static void printf(String format, Object... args) {
        System.out.print(String.format(format, args));
    }

    private static void printlnf(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    private static void mergeSort(int[] array) {
        int length = array.length;
        if (length <= 1) return;

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

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

    private static void merge(int[] leftArray, int[] rightArray, int[] array) {

        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0;

        while (l < leftSize && r < rightSize) {
            if (leftArray[l] < rightArray[r]) {
                array[i] = leftArray[l];
                i++;
                l++;
            }
            else {
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        while (l < leftSize) {
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while (r < rightSize) {
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j])
                    min = j;
            }

            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    public static void bubbleSort(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }
}

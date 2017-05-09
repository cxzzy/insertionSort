import static java.util.Arrays.copyOfRange;

public class InsertionSort {

    public static void main(String[] args) throws InterruptedException {

        ////Start insertionSort with 1 thread

        System.out.println("1 Thread: \n");

        //Start with 160.000 integers
        int array[] = new int[160000];
        populateArray(array);

        // Before spliting the array
//        System.out.println("Current array: \n" + Arrays.toString(array));

        long startTime = System.currentTimeMillis();
        // Split array

        int[] sortedArray = insertionSort(array);

        long stopTime = System.currentTimeMillis();
        long elapsedTime1thread = stopTime - startTime;

        System.out.println("Time sorting array with 1 Thread: " + elapsedTime1thread + " ms");

//        System.out.println("Sorted array: " + Arrays.toString(sortedArray));

        ////Start insertionSort with 2 threads

        System.out.println("\n 2 Threads: \n");

        int twoThreadsArray[] = new int[160000];
        populateArray(twoThreadsArray);

        long startTime2Threads = System.currentTimeMillis();

        int[] first = copyOfRange(twoThreadsArray, 0, twoThreadsArray.length / 2);
        int[] second = copyOfRange(twoThreadsArray, first.length, twoThreadsArray.length);

        // sorting the arrays
        Thread sortFirstHalf = new Thread(() -> insertionSort(first));
        Thread sortSecondHalf = new Thread(() -> insertionSort(second));

        sortFirstHalf.start();
        sortSecondHalf.start();

        sortFirstHalf.join();
        sortSecondHalf.join();

        int[] mergedArray = merge(first, second);
        insertionSort(mergedArray);

        long stopTime2Threads = System.currentTimeMillis();
        long elapsedTime2Threads = stopTime2Threads - startTime2Threads;

        System.out.println("Time sorting array with 2 Threads: " + elapsedTime2Threads + " ms");
    }

    private static int[] insertionSort(int[] arr) {
        int j;
        int key;
        int i;

        for (j = 1; j < arr.length; j++) {
            key = arr[j];

            for (i = j - 1; (i >= 0) && (arr[i] < key); i--) {
                arr[i + 1] = arr[i];
            }
            arr[i + 1] = key;
        }
        return arr;
    }

    private static void populateArray(int[] B) {
        for (int i = 0; i < B.length; i++) {
            B[i] = (int) (Math.random() * 10000);
        }
    }

    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i;
        for (i = 0; i < a.length; i++)
            c[i] = a[i];

        for (int j = 0; j < b.length; j++)
            c[i++] = b[j];
        return c;
    }
}
/**
 * Created by wieskueter.com on 5/5/2017.
 */
import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) throws InterruptedException {

        boolean multithreaded = false;

        // Creating arrays
        int array[] = new int[700000];
        populateArray(array);

        // Before
        System.out.println("Before Sorting: ");
        System.out.println(Arrays.toString(array));

        splitArray splitArray = new splitArray();
        splitArray.splitArray(array);

        int[] firstHalf = splitArray.getArray(1);
        int[] secondHalf = splitArray.getArray(2);

        showHalf(firstHalf);
        showHalf(secondHalf);

        if(multithreaded) {
            // sort the array
            Thread sortFirstHalf = new Thread() {
                public void run() {
                    insertionSort(firstHalf);
                }
            };

            // sort the array
            Thread sortSecondHalf = new Thread() {
                public void run() {
                    insertionSort(secondHalf);
                }
            };

            sortFirstHalf.start();
            sortSecondHalf.start();

            sortFirstHalf.join();
            sortSecondHalf.join();

            int mergedArray[] = new int[firstHalf.length + secondHalf.length];

            // Merge sorted arrays back together
            if(!sortFirstHalf.isAlive() && !sortSecondHalf.isAlive()) {

                System.arraycopy(firstHalf,0, mergedArray, 0, firstHalf.length);
                System.arraycopy(secondHalf,0, mergedArray, firstHalf.length, secondHalf.length);

                System.out.println("Merged array: "+ Arrays.toString(mergedArray) );
            }

            insertionSort(mergedArray);

            System.out.println("\nAfter Sorting: ");
            System.out.println(Arrays.toString(mergedArray));
        } else {
            // Normal way
            insertionSort(array);
            System.out.println("\nAfter Sorting: ");
            System.out.println(Arrays.toString(array));
        }
    }

    private static void showHalf(int[] half) {
        // Printing a
        System.out.print("First half : "+ half.length);
        for (int i = 0; i < half.length; i++) {
            System.out.print("\t" + half[i]);
        }
        System.out.println();
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int valueToSort = arr[i];
            int j = i;

            while (j > 0 && arr[j - 1] > valueToSort) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = valueToSort;
        }
    }

    public static void populateArray(int[] B) {
        for (int i = 0; i < B.length; i++) {
            B[i] = (int) (Math.random() * 100);
        }
    }
}
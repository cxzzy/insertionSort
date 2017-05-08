/**
 * Created by wieskueter.com on 5/5/2017.
 */
import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

        // Creating arrays
        int array[] = new int[100];
        splitArray splitArray = new splitArray();

        populateArray(array);
        splitArray.splitArray(array);

        // Printing a
        System.out.print("a : "+ splitArray.getArray(1).length);
        for (int i = 0; i < splitArray.getArray(1).length; i++) {
            System.out.print("\t" + splitArray.getArray(1)[i]);
        }
        System.out.println();

        //
        System.out.println("Before Sorting: ");
        System.out.println(Arrays.toString(array));

        // sort the array
        Thread sortFirstHalf = new Thread() {
            public void run() {
                insertionSort(splitArray.getArray(1));
            }
        };

        // sort the array
        Thread sortSeoondHalf = new Thread() {
            public void run() {
                insertionSort(splitArray.getArray(2));
                System.out.println("Sorted second half: "+ Arrays.toString(array));
            }
        };

        sortFirstHalf.start();
        sortSeoondHalf.start();

        // Normal way
        //insertionSort(array);

        System.out.println("\nAfter Sorting: ");
        System.out.println(Arrays.toString(array));
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
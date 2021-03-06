package helpers;

public class Sort {

    // Sort from left to right
    public static int[] singleThread(int[] arr) {
        int j, key, i;

        for (j = 1; j < arr.length; j++) {
            key = arr[j];

            for (i = j - 1; (i >= 0) && (arr[i] < key); i--) {
                arr[i + 1] = arr[i];
            }

            arr[i + 1] = key;
        }

        return arr;
    }

    // Sort from right to left
    public static void sortRightToLeft(int[] arr) {
        int t, i, j;

        for (i = arr.length - 1; i > 0; i--) {

            for (j = i; j < arr.length && arr[j - 1] <= arr[j]; j++) {

                t = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = t;

            }
        }
    }

    //Add synchronized to make sure that the array will be sorted nicely
    public synchronized static int[] insertionSort2Threaded(int[] arr) {

        Thread t1 = new Thread(() -> {
            singleThread(arr);
        });

        Thread t2 = new Thread(() -> {
            sortRightToLeft(arr);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return arr;
    }

//    int[] insertionSortV2(int[] arr) {
//
//        int [] keys = new int[0];
//        int [] finalArray = new int[0];
//
//        Thread t1 = new Thread(() -> {
//
//            int j;
//
//            keys.length = 0;
//
//            for (j = 1; j < arr.length; j++) {
//                keys.add(0, arr[j]);
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//
//            int i;
//
//            for (i = j - 1; (i >= 0) && (finalArray[i] < keys.get(0)); i--) {
//                arr[i + 1] = arr[i];
//            }
//            finalArray[i + 1] = keys.get(0);
//        });
//
//        return finalArray;
//    }
}
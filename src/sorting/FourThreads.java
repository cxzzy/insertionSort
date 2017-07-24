package sorting;

import helpers.Sort;
import helpers.Utils;

public class FourThreads {

    private static int THREADS = 4;

    // Each thread has a bucket with the index 0-3
    private static int[][] bucket;

    // Min and max values for buckets
    private static int minNumber[] = new int[THREADS];
    private static int maxNumber[] = new int[THREADS];

    public static int[] FourThreads(int[] arr) {

        // Create each bucket
        createBuckets(arr);

        placeInBucket(arr);

        System.out.println("Final sorted bucket: ");
        Utils.printArray( combine() );

        return combine();
    }

    /*
        Create arrays in where each thread puts his number
        Divide the largest number by the amount of threads for 4 threads: 20/ 4
    */
    private static void placeInBucket(int[] array) {
        Thread t1 = new Thread(() -> {
            for(int i=minNumber[0]; i<=maxNumber[0]; i++) {
                pickBucket(array, i);
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i=minNumber[1]; i<=maxNumber[1]; i++) {
                pickBucket(array, i);
            }
        });

        Thread t3 = new Thread(() -> {
            for(int i=minNumber[2]; i<=maxNumber[2]; i++) {
                pickBucket(array, i);
            }
        });

        Thread t4 = new Thread(() -> {
            for(int i=minNumber[3]; i<=array.length-1; i++) {
                pickBucket(array, i);
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Loop through each number and place in bucket
        /*for (int i = 0; i < array.length; i++) {
            if (array[i] <= maxNumber[0]) {
                bucket[0][i] = array[i];
                Sort.singleThread(bucket[0]);
            }

            if (array[i] <= maxNumber[1] && array[i] >= minNumber[1]) {
                bucket[1][i] = array[i];
                Sort.singleThread(bucket[1]);
            }

            if (array[i] <= maxNumber[2] && array[i] >= minNumber[2]) {
                bucket[2][i] = array[i];
                Sort.singleThread(bucket[2]);
            }

            if (array[i] <= maxNumber[3] && array[i] >= minNumber[3]) {
                bucket[3][i] = array[i];
                Sort.singleThread(bucket[3]);
            }
        }*/
    }

    private static void pickBucket(int[] array, int i) {
        if (array[i] <= maxNumber[0]) {
            bucket[0][i] = array[i];
            Sort.singleThread(bucket[0]);
        }
        if (array[i] <= maxNumber[1] && array[i] >= minNumber[1]) {
            bucket[1][i] = array[i];
            Sort.singleThread(bucket[1]);
        }
        if (array[i] <= maxNumber[2] && array[i] >= minNumber[2]) {
            bucket[2][i] = array[i];
            Sort.singleThread(bucket[2]);
        }
        if (array[i] <= maxNumber[3] && array[i] >= minNumber[3]) {
            bucket[3][i] = array[i];
            Sort.singleThread(bucket[3]);
        }
    }

    // @TO-DO dynamically create buckets
    public static void createBuckets(int[] array) {

        bucket = new int[THREADS][array.length];

        // Create buckets
        int max = largestNumber(array);
        System.out.println("Max: " + max);
        System.out.println("Create buckets for:" + max / THREADS);

        //int per_bucket = max / THREADS;
        int per_bucket = array.length/ THREADS;

        System.out.println("Per bucket: "+ per_bucket);

        for (int i = 1; i <= THREADS; i++) {

            int from_bucket = i == 1 ? 0 : (i - 1) * per_bucket;
            int to_bucket = i == THREADS ? max : i * per_bucket-1;

            // [thread_id]
            minNumber[i-1] = from_bucket;
            maxNumber[i-1] = to_bucket;

            //bucket[i-1] = new int[to_bucket];

            System.out.println("Bucket: " + from_bucket + " - " + to_bucket);
        }
    }

    // Find the largest number
    private static int largestNumber(int[] array) {
        int max = 0;

        for (int counter = 0; counter < array.length; counter++) {
            if (array[counter] > max) {
                max = array[counter];
            }
        }

        return max;
    }

    // Combine all sorted buckets
    private static int[] combine() {
        int[] combinedBucket = combineBuckets(bucket[3], bucket[2], bucket[1], bucket[0]);

        return combinedBucket;
    }

    private static int[] combineBuckets(final int[] ...arrays ) {
        int size = 0;
        for ( int[] a: arrays )
            size += a.length;

        int[] res = new int[size];

        int destPos = 0;
        for ( int i = 0; i < arrays.length; i++ ) {
            if ( i > 0 ) destPos += arrays[i-1].length;
            int length = arrays[i].length;
            System.arraycopy(arrays[i], 0, res, destPos, length);
        }

        return res;
    }
}

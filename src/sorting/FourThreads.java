package sorting;

import helpers.EventProfiler;
import helpers.Sort;
import helpers.Utils;

import java.util.Arrays;
import java.util.Collections;

public class FourThreads {

    private static EventProfiler profiler = new EventProfiler(true);

    private static int THREADS = 4;

    // Each thread has a bucket with the index 0-3
    private static int[][] bucket;

    // Min and max values for buckets
    private static int minNumber[] = new int[THREADS];
    private static int maxNumber[] = new int[THREADS];

    public static int[] FourThreads(int[] arr) throws InterruptedException {

        profiler.start();

        // Create each bucket
        createBuckets(arr);

        // Sort for each thread
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                // some code to run in parallel
                for(int X = minNumber[finalI]; X<=maxNumber[finalI]; X++) {
                    pickBucket(arr, X);
                }
            });
            threads[i].start();
            threads[i].join();
        }

        System.out.println("Final sorted bucket with "+ THREADS +" threads:");
        Utils.printArray( combine() );

        profiler.log("Result with four threads");

        return combine();
    }

    private static void createBuckets(int[] array) {

        bucket = new int[THREADS][array.length];

        // Create buckets
        int max = largestNumber(array);
        int per_bucket = array.length/ THREADS;

        /*System.out.println("Max: " + max);
        System.out.println("Create buckets for:" + max / THREADS);
        System.out.println("Per bucket: "+ per_bucket);*/

        for (int i = 1; i <= THREADS; i++) {
            int from_bucket = i == 1 ? 0 : (i - 1) * per_bucket;
            int to_bucket = i == THREADS ? max : i * per_bucket-1;

            // [thread_id]
            minNumber[i-1] = from_bucket;
            maxNumber[i-1] = to_bucket;

            System.out.println("Bucket: " + from_bucket + " - " + to_bucket);
        }
    }

    private static void pickBucket(int[] array, int i) {

        // Pick a bucket to store the number
        for(int t = 0; t < THREADS; t++) {
            if (array[i] <= maxNumber[t] && array[i] >= minNumber[t]) {
                bucket[t][i] = array[i];
                Sort.singleThread(bucket[t]);
            }
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
        Collections.reverse(Arrays.asList(bucket));

        int size = 0;
        for ( int[] a: bucket )
            size += a.length;

        int[] res = new int[size];

        int destPos = 0;
        for ( int i = 0; i < bucket.length; i++ ) {
            if ( i > 0 ) destPos += bucket[i-1].length;
            int length = bucket[i].length;
            System.arraycopy(bucket[i], 0, res, destPos, length);
        }

        return res;
    }
}
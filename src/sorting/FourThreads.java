package sorting;

import helpers.Sort;
import helpers.Utils;

public class FourThreads {

    private int[] finalArray;
    private int THREADS = 4;

    // Each thread has a bucket with the index 0-3
    int[][] bucket = new int[4][100];

    // Min and max values for buckets
    int minNumber[] = new int[4];
    int maxNumber[] = new int[4];

    /*
        Create arrays in where each thread puts his number
        Divide the largest number by the amount of threads for 4 threads: 20/ 4
    */
    public void placeInBucket(int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] < maxNumber[0]) {
                bucket[0][i] = array[i];
                Sort.singleThread(bucket[0]);
            }
            if (array[i] < maxNumber[1] && array[i] > minNumber[1]) {
                bucket[1][i] = array[i];
                Sort.singleThread(bucket[1]);
            }
            if (array[i] < maxNumber[2] && array[i] > minNumber[2]) {
                bucket[2][i] = array[i];
                Sort.singleThread(bucket[2]);
            }
            if (array[i] < maxNumber[3] && array[i] > minNumber[3]) {
                bucket[3][i] = array[i];
                Sort.singleThread(bucket[3]);
            }
        }
    }

    // @TO-DO dynamically create buckets
    public void createBuckets(int[] array) {
        // Create buckets
        int max = largestNumber(array);
        System.out.println("Max: " + max);
        System.out.println("Create buckets for:" + max / THREADS);

        int per_bucket = max / THREADS;

        for (int i = 1; i <= THREADS; i++) {

            int from_bucket = i == 1 ? 0 : (i - 1) * per_bucket + 1;
            int to_bucket = i == THREADS ? max : i * per_bucket;

            // [thread_id]
            minNumber[i-1] = from_bucket;
            maxNumber[i-1] = to_bucket;

            //bucket[i-1] = new int[to_bucket];

            System.out.println("Bucket: " + from_bucket + " - " + to_bucket);
        }
    }

    // Find largest number
    public int largestNumber(int[] array) {
        int max = 0;

        for (int counter = 1; counter < array.length; counter++) {
            if (array[counter] > max) {
                max = array[counter];
            }
        }

        return max;
    }

    public void combine() {
        int[] combinedBucket = combineBuckets(bucket[3], bucket[2], bucket[1], bucket[0]);

        System.out.println("Final sorted bucket: ");
        Utils.printArray(combinedBucket);
    }

    final static public int[] combineBuckets(final int[] ...arrays ) {
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

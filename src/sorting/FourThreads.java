package sorting;

import Helpers.Sort;
import Helpers.Utils;

/**
 * Created by wieskueter.com on 6/15/2017.
 */
public class FourThreads {
    private int[] finalArray;
    private int THREADS = 4;

    // @TO-DO dynamically create buckets
    int[] bucketpart1 = new int[100];
    int[] bucketpart2 = new int[100];
    int[] bucketpart3 = new int[100];
    int[] bucketpart4 = new int[100];

    /*
        Create arrays in where each thread puts his number
        Divide the largest number by the amount of threads for 4 threads: 20/ 4
    */
    public void placeInBucket(int[] array) {

        /*
            1. Place in bucket
            2. Sort
         */
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 24) {
                bucketpart1[i] = array[i];
            }
        }

        System.out.println("Unsorted bucket: ");
        Utils.printArray(bucketpart1);

        Sort.singleThread(bucketpart1);

        System.out.println("Sorted bucket: ");
        Utils.printArray(bucketpart1);
    }

    public void createBuckets(int[] array) {
        // Create buckets
        int max = largestNumber(array);
        System.out.println("Max: " + max);
        System.out.println("Create buckets for:" + max / THREADS);

        int per_bucket = max / THREADS;

        for (int i = 1; i <= THREADS; i++) {

            int from_bucket = i == 1 ? 0 : (i - 1) * per_bucket + 1;
            int to_bucket = i == THREADS ? max : i * per_bucket;

            System.out.println("Bucket: " + from_bucket + " - " + to_bucket);
        }
    }

    // Find largest number
    public int largestNumber(int[] array) {
        int max = 0;

        for (int counter = 1; counter < array.length; counter++)
        {
            if (array[counter] > max)
            {
                max = array[counter];
            }
        }

        return max;
    }

    public void combineBuckets() {

    }
}

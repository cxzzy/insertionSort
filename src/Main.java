import helpers.EventProfiler;
import helpers.Sort;
import helpers.Utils;
import sorting.FourThreads;
import sorting.InsertionSortV2;

public class Main {

    private static EventProfiler profiler = new EventProfiler(true);

    public static void main(String[] args) throws InterruptedException {

        int array[] = Utils.fillArray(100);
        Utils.shuffleArray(array);

        System.out.println("Before sort: ");
        Utils.printArray(array);

        //singleThread(array);
        twoThreads(array);
        //fourThreads(array);

        System.out.println("After sort: ");
        Utils.printArray(array);
    }

    //InsertionSort with 1 thread
    public static void singleThread(int[] array) throws InterruptedException {

        Thread serialThread = new Thread(() -> Sort.singleThread(array));

        //Start timer
        profiler.start();

        serialThread.start();
        serialThread.join();

        profiler.log("Result with 1 Thread");
    }

    /*
        InsertionSort with multiple threads
    */
    public static void consumerProducer(int[] array) throws InterruptedException {

        InsertionSortV2 insertionSortV2 = new InsertionSortV2();

        Utils.shuffleArray(array);

        Thread t1 = new Thread(() -> insertionSortV2.producer(array));
        Thread t2 = new Thread(insertionSortV2::consumer);

        //Start timer
        profiler.start();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        profiler.log("Result with producer consumer");
    }

    /*
        InsertionSort with 2 threads
    */
    public static void twoThreads(int[] array) throws InterruptedException {

        Thread parallelThread = new Thread(() -> Sort.insertionSort2Threaded(array));

        //Start timer
        profiler.start();

        parallelThread.start();
        parallelThread.join();

        profiler.log("Result with 2 Threads");
    }

    /*
        Four threaded solution
    */
    public static void fourThreads(int[] array) {
        FourThreads fourThreads = new FourThreads();

        fourThreads.createBuckets(array);
        fourThreads.placeInBucket(array);
    }
}
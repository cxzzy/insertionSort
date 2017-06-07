import sorting.InsertionSortV2;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Sort sort = new Sort();
        InsertionSortV2 insertionSortV2 = new InsertionSortV2();
        EventProfiler profiler = new EventProfiler(true);

        //InsertionSort with 1 thread

        int array[] = Utils.fillArray(100);
        Utils.shuffleArray(array);

        Thread serialThread = new Thread(() -> sort.singleThread(array));

        //Start timer
        profiler.start();

        serialThread.start();

        serialThread.join();

        Utils.printArray(array);

        profiler.log("Result with 1 Thread");

        //InsertionSort with 2 threads
//
//        Utils.shuffleArray(array);
//
//        Thread parallelThread = new Thread(() -> sort.insertionSort2Threaded(array));
//
//        //Start timer
//        profiler.start();
//
//        parallelThread.start();
//
//        parallelThread.join();
//
//        profiler.log("Result with 2 Threads");

        //InsertionSort with multiple threads

        Utils.shuffleArray(array);

        Utils.printArray(array);

        Thread t1 = new Thread(() -> insertionSortV2.producer(array));
        Thread t2 = new Thread(insertionSortV2::consumer);

        //Start timer
        profiler.start();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        Utils.printArray(array);

        profiler.log("Result with producer consumer");
    }
}
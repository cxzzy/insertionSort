public class Main {

    public static void main(String[] args) throws InterruptedException {

        Sort sort = new Sort();
        EventProfiler profiler = new EventProfiler(true);

        //InsertionSort with 1 thread

        int array[] = Utils.fillArray(100000);
        Utils.shuffleArray(array);

        Thread serialThread = new Thread(() -> sort.singleThread(array));

        profiler.start();

        serialThread.start();

        serialThread.join();

        profiler.log("Result with 1 Thread");

        //InsertionSort with 2 threads

        Utils.shuffleArray(array);

        Thread parallelThread = new Thread(() -> sort.insertionSort2Threaded(array));

        profiler.start();

        parallelThread.start();

        parallelThread.join();

        profiler.log("Result with 2 Threads");
    }
}
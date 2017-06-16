import sorting.Consumer;
import sorting.Producer;
import sorting.Sorting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Sort sort = new Sort();
        EventProfiler profiler = new EventProfiler(true);

        //InsertionSort with 1 thread

        int array[] = Utils.fillArray(100);
        Utils.shuffleArray(array);

        Producer producer = new Producer();
        Consumer consumer = new Consumer(array);
        Sorting sorting = new Sorting();

//        Thread serialThread = new Thread(() -> sort.singleThread(array));
//
//        //Start timer
//        profiler.start();
//
//        serialThread.start();
//
//        serialThread.join();
//
//        Utils.printArray(array);
//
//        profiler.log("Result with 1 Thread");

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

//        Utils.shuffleArray(array);
//
//        Utils.printArray(array);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(consumer);

        executorService.shutdown();

        System.out.println(sorting.sortArray);

        Utils.printArray(sorting.sortArray);

//        Thread t1 = new Thread(() -> consumer.run());
//        Thread t2 = new Thread(() -> producer.consumer(array));
//
//        //Start timer
//        profiler.start();
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();

        profiler.log("Result with producer consumer");
    }
}
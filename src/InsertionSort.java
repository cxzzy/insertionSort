public class InsertionSort {

    public static void main(String[] args) throws InterruptedException {

        //TODO: Add the EventProfiler from ParallelMin example

        ////Start insertionSort with 1 thread
        System.out.println("1 Thread: \n");

        //Start with 100.000.000 integers
        int array[] = Utils.fillArray(100000);
        Utils.shuffleArray(array);

        Thread serialThread = new Thread(() -> insertionSort(array));

        long startTime = System.currentTimeMillis();

        serialThread.start();

        serialThread.join();

        long stopTime = System.currentTimeMillis();
        long elapsedTime1thread = stopTime - startTime;

        System.out.println("Time sorting array with 1 Thread: " + elapsedTime1thread + " ms");

        System.out.println("2 Threads: \n");

        Utils.shuffleArray(array);

        Thread parallelThread = new Thread(() -> {
            try {
                insertionSort2Threaded(array);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long startTime1 = System.currentTimeMillis();

        parallelThread.start();

        parallelThread.join();

        long stopTime1 = System.currentTimeMillis();
        long elapsedTime2thread = stopTime1 - startTime1;

        System.out.println("Time sorting array with 2 Thread: " + elapsedTime2thread + " ms");

    }

    private static int[] insertionSort(int[] arr) {
        int j;
        int key;
        int i;

        for (j = 1; j < arr.length; j++) {
            key = arr[j];

            for (i = j - 1; (i >= 0) && (arr[i] < key); i--) {
                arr[i + 1] = arr[i];
            }
            arr[i + 1] = key;
        }
        return arr;
    }

    private static int[] insertionSort2Threaded(int[] arr) throws InterruptedException {

        Thread t1 = new Thread(() -> {

            int j;
            int key;
            int i;

            for (j = 1; j < arr.length; j++) {
                key = arr[j];

                for (i = j - 1; (i >= 0) && (arr[i] < key); i--) {
                    arr[i + 1] = arr[i];
                }

                arr[i + 1] = key;
            }
        });

        Thread t2 = new Thread(() -> {

            int k;
            int key2;
            int l;

            for (k = arr.length - 1; k > 0; k--) {
                key2 = arr[k];

                for (l = k - 1; (l < 0) && (arr[l] >= key2); l++) {
                    arr[l + 1] = arr[l];
                }

                arr[l + 1] = key2;
            }

        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return arr;
    }
}
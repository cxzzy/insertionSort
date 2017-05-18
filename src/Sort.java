class Sort {

    int[] singleThread(int[] arr) {
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


    //Add synchronized to make sure that the array will be sorted nicely
    int[] insertionSort2Threaded(int[] arr) {

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

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return arr;
    }
}
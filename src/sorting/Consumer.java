package sorting;

public class Consumer implements Runnable {

    private int array[];
    private int j;
    Sorting sorting = new Sorting();

    public Consumer(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {

//        while (true) {

            for (j = 1; j < array.length; j++) {

                int i = array[j];

                sorting.sortArray.add(i);
            }
//        }

    }
}
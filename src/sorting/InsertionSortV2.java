package sorting;

public class InsertionSortV2 {

    private int[] finalArray;
    private int key;

    private int getKey() {
        return key;
    }

    private void setKey(int key) {
        this.key = key;
    }

    //This works
    public void producer(int[] array) {

        int j;

        for (j = 1; j < array.length; j++) {
            setKey(array[j]);
        }
    }

    //TODO not working as it should. This is the sorting part
    public int[] consumer() {

        int i;

        for (i = getKey() - 1; (i >= 0) && (finalArray[i] < getKey()); i--) {
            finalArray[i + 1] = finalArray[i];
        }

        finalArray[i + 1] = getKey();

        return finalArray;
    }
}
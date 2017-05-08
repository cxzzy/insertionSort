/**
 * Created by wieskueter.com on 5/8/2017.
 */
public class splitArray {

    public int[] a, b;

    public void main(String[] args) {
        this.a = a;
        this.b = b;
    }

    public void splitArray(int[] array) {

        System.out.println("Split array");

        int arraySize = array.length/2;

        a = new int[arraySize];
        b = new int[arraySize];

        // Splitting the array in two
        for (int i = 0; i < arraySize; i++) {
            a[i] = array[2 * i];
            b[i] = array[2 * i + 1];
        }
    }

    public int[] getArray(int part) {
        int[] returnPart;

        if(part == 1) {
            returnPart = this.a;
        } else {
            returnPart = this.b;
        }
        return returnPart;
    }
}

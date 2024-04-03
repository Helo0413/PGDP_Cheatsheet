package ArraysAndSwitches;

import java.util.Arrays;

public class RecursiveSorting {

    /**	Implementation of the MergeSort algorithm
     *
     * @param array Any Integer-Array
     * @return The passed Integer-Array, but sorted in ascending order
     */
    public static int[] mergeSort(int[] array) {
        if(array.length <= 1) return array;
        int cut = array.length/2;
        int[] left = new int[cut];
        int[] right = new int[array.length-cut];

        int count = 0;

        for (int i = 0; i < array.length; i++) {
            if(i < cut){
                left[i] = array[i];
            }else {
                right[count] = array[i];
                count++;
            }
        }

        left = mergeSort(left);
        right =  mergeSort(right);

        return merge(left, right);


    }

    // Hilfsmethode (muss nicht verwendet werden, könnte aber hilfreich sein)
    public static int[] merge(int[] first, int[] second) {

        int i, j, k;
        i = j = k = 0;

        int[] merge = new int[first.length+second.length];

        while(i < first.length && j < second.length){
            if(first[i] < second[j]){
                merge[k++] = first[i++];

            } else {
                merge[k++] = second[j++];

            }
        }

        while(k< merge.length){
            if(j <second.length){
            merge[k++] = second[j++];

            }
            if(i< first.length){
                merge[k++] = first[i++];

            }
        }

        return merge;
    }



    /**	Implementation of the StoogeSort algorithm
     *
     * @param array Any Integer-Array
     * @return The passed Integer-Array, but sorted in ascending order
     */
    public static void stoogeSort(int[] array) {
        stoogeSort(array, 0, array.length);
    }

    public static void stoogeSort(int[] array, int from, int to) {
        int length = to-from;
        if(length == 2 && array[from] > array[from+1]) swap(array, from, from+1);

        if(length<= 2)return;


        int third = from + length/3;
        int secondTwoThird = to-length/3;


        stoogeSort(array, from, secondTwoThird);
        stoogeSort(array,third, to);
        stoogeSort(array,from,secondTwoThird);


    }



    /**	Implementation of the SelectionSort algorithm in a recursive way
     *
     * @param a Any Integer-Array
     * @return The passed Integer-Array, but sorted in ascending order
     */
    public static void selectionSortRec(int[] a) {
        selectionSortRec(a, a.length - 1);
    }

    public static void selectionSortRec(int[] a, int to) {

      if(to <= 0) return;

      int indexOfL = findIndexOfLargest(a, to);
      swap(a, indexOfL, to);


      selectionSortRec(a, to-1);



       /* if(to <= 0) {
            return;
        }
        //e.g a.length 0-10
        int indexOfLargest = findIndexOfLargest(a, to); // finds the index with largest number within the index 0- length-1
        swap(a, indexOfLargest, to); // puts the largest nmr in the biggest index so 12 (Ind3) -> Ind 10
        selectionSortRec(a, to - 1); // recalls this method but decreases by 1 the position for the largest index,
        // so only the rest of the array is compared (Ind 0-9)
*/
    }

    // Hilfsmethode (muss nicht verwendet werden, könnte aber hilfreich sein)
    public static int findIndexOfLargest(int[] a, int to) {

        if( to <= 0) return 0;

        int largestBefore = findIndexOfLargest(a, to-1);
        if(a[to] > a[largestBefore]){
            return to;
        }else {
            return largestBefore;
        }




        //example a = {8,9,7}
        // call recursively till to == 0 -> LargestBefore = 0 for to = 1
        // since index 1 = 9 -> the Index 1 is returned
        // the method then compares indexes 2 (to,  the biggest one, first call of the method) with Index LargestBefore = 1
        // since that is not true, the index of LargestBefore is returned
        // basically a comparison of index 0 with all indexes from 0 -> length and every time a bigger number is found, its index
        // is used as a reference point for comparison instead
    }



    // Hilfsmethode (muss nicht verwendet werden, könnte aber hilfreich sein)
    public static void swap(int[] a, int firstPos, int secondPos) {
        int tmp = a[firstPos];
        a[firstPos] = a[secondPos];
        a[secondPos] = tmp;
    }

    // Für Experimente
    public static void main(String[] args) {
        int[] a = {3, 4, 9, 2, 5, 0, 2, 1, 6, 4, -3};

        System.out.println(Arrays.toString(mergeSort(a)));

    }

}

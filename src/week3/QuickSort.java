package week3;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    private int partition(int[] arr, int l, int r){
           //a[l][elements < p] [ a[i] ]  [elements >p]
            //i should be the index of a bound
           int p = arr[l];
           int i = l+1;
        for (int j = l + 1; j <= r; j++) {
            if (arr[j] < p) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i - 1, l);
        //return pivot index
        return i - 1;
    }

    //add random pivot
    void random(int[] arr, int l, int r) {
        Random random = new Random();
        int pivot = random.nextInt(r - l) + l;
        swap(arr, l, pivot);
    }

    private void sort(int[] arr){
        sort(arr, 0, arr.length-1);
    }

    void sort(int[] arr, int l, int r) {
        if (l >= r) return;
        //choose a rondom a point and put it for left head.
        random(arr, l, r);
        int pIdx = partition(arr, l, r);

        sort(arr, l, pIdx - 1);
        sort(arr, pIdx + 1, r);

    }

    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        // int[] arr = new int[]{4,5,2,1,10};
        int[] arr = new int[]{10, 5, 3, 2, 1, 2};
        q.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

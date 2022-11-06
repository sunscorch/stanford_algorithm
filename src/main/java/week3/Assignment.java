package week3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import java.util.stream.Stream;

public class Assignment {

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

    //pviot choice option:
    //choose most left, 0
    //choose most right, 1
    //choose media, 2
    void choosePivot(int[] arr,int l, int r, int option){
        if (option == 0) return;
        if (option == 1){
            swap(arr, l, r);
        }else if(option == 2){
            int mid = (l+r)/2;
            int[] array = new int[]{arr[l], arr[r], arr[mid]};
            Arrays.sort(array);
            if(array[1] == arr[l]){
               //do nothing
            }else if(array[1] == arr[mid]){
                swap(arr, l, mid);

            }else{
                swap(arr, l, r);
            }
        }
    }


    private void sort(int[] arr){
        long res = sort(arr, 0, arr.length-1);
        System.out.println("cnt is " + res );
    }

    long sort(int[] arr, int l, int r) {
        if (l >= r) return 0;
        //choose a rondom a point and put it on left head.
        //random(arr, l, r);
       //choosePivot(arr,l,r,0);
       //choosePivot(arr,l,r,1);
        choosePivot(arr,l,r,2);
        //.cnt += r - l;

        int pIdx = partition(arr, l, r);
        System.out.println("pviot index is " +pIdx);

        long left  =sort(arr, l, pIdx-1 );
        long right =sort(arr, pIdx + 1, r);
        System.out.println("("+ l+ "  " + r + ")" + "cnt is " + (left+right+r-l));
        return left+right+r-l;

    }
    private static int[] getArrayFromFile(String fileName){
        int [] res = new int[0];
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {


            res = stream
                    .mapToInt(Integer::parseInt)
                    .toArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        Assignment q = new Assignment();
       //int[] arr = new int[]{ 2, 1};
        int[] arr = getArrayFromFile("src//main/java/week3/Quicksort.txt");
        q.sort(arr);
      // System.out.println(Arrays.toString(arr));
    }
}

package week2.inversions;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class FindInversions {

    private static  long  mergeAndCount(int[] array, int l, int r, int mid){
        int[] left = Arrays.copyOfRange(array, l, mid+1);
        int[] right = Arrays.copyOfRange(array, mid+1, r+1);

        int i =0;
        int j = 0;
        int k = l;
        long count = 0L;

        while(i < left.length && j < right.length){
            if(left[i] > right[j]){
                array[k++] = right[j++];
                ///[1,2,3, 4] [0,1,2,5]
                //if left[i] > right[j], left[i], left[i+1]... left[size-1] will greater than right[j],
                // all together we have size-1-i+1 pairs
                count += left.length - i;
            }else{
                array[k++] = left[i++];
            }
        }

        while (i < left.length)
            array[k++] = left[i++];

        while (j < right.length)
            array[k++] = right[j++];

        return count;
    }

    static long mergeSortAndCount(int[] array, int l, int r){
        long res = 0L;

        if(l < r){
            int mid = (l + r)/2;
            long lCount = mergeSortAndCount(array, l , mid);
            long rCount = mergeSortAndCount(array, mid+1 ,r );
            long curCount = mergeAndCount(array,l, r, mid);
            res +=  lCount+rCount+curCount;
        }
        return res;
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
        System.out.println("cores is "+Runtime.getRuntime().availableProcessors());
        int[] arr = getArrayFromFile("src/week2/inversions/IntegerArray.txt");

        long res = mergeSortAndCount(arr, 0, arr.length-1);
        //System.out.println(Arrays.toString(arr));
        System.out.println("inversion count is " + res);
        //inversion count is 2407905288


    }
}

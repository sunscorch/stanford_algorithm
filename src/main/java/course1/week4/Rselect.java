package course1.week4;

import java.util.Random;

//random selection
public class Rselect {
    //assume we have a pivot and all the left side element <=p　ｐ
    //right side element > p
    //if i == p return array[i]
    //if i < p, Rselect(left, p-1, i)
    //if i > p , Rselect(P+1, right, i)
    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private int partition(int[] arr, int l, int r){
        //choose some pviot;
        Random random = new Random();

        int pivot = random.nextInt(r - l) + l;
        swap(arr, l, pivot);
        int i = l+1;

        for(int j = l+1; j <= r; j++){
            if(arr[j] < arr[l]){
                swap(arr, i, j);
                i++;
            }
        }
        //
        swap(arr, i-1, l);
        return i-1;

    }
    private int select(int[] arr, int l, int r, int i){
        //cornner case for 1 ith elem in [1]
        if(l == r) return arr[l];

        int pIdx = partition(arr, l, r);
        int pValue = arr[pIdx];
        if(pIdx == i) return pValue;
        else if (pIdx < i) {
            //on the right side of the pviot
            // [....]p[...i...]
            return select(arr, pIdx+1, r, i);

        }else{
            //on the left side of the pviot
            // [..i..]p[......]
            return select(arr, l, pIdx-1, i);
        }

    }

    private int select(int[] arr, int i){

        return select(arr, 0, arr.length-1,i-1);
    }

    public static void main(String[] args) {
        int [] arr = new int[]{3,2,1,5,6,4};
        Rselect rs = new Rselect();
        int res = rs.select(arr, 4);
        System.out.println("res is " + res);
        int [] arr1 = new int[]{1};
        res = rs.select(arr1, 1);
        System.out.println("res is " + res);

    }
}

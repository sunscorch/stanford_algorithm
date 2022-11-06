package week4;

import java.util.ArrayList;
import java.util.Arrays;

//https://www.ics.uci.edu/~eppstein/161/960130.html
/*
*
* */
public class Dselect {


    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    int pviot(int[] arr, int k){
        if(arr.length == 1) return arr[0];
        else if(arr.length <= 10){
            Arrays.sort(arr);
            return arr[k-1];
        }
        //partition the arr into n/5 groups
        int gSize = arr.length/5;
        ArrayList<Integer> midians = new ArrayList();

        for(int i = 0; i < gSize; i++){
            int[] s = Arrays.copyOfRange(arr, i*5, (i+1)*5);
           System.out.println("sub array is " + Arrays.toString(s));
            int mid = pviot(s, 3);
            midians.add(mid);
        }


        int lastSegLenth = arr.length % 5;
        if(lastSegLenth != 0){
            int[] s = Arrays.copyOfRange(arr, gSize*5, gSize*5+lastSegLenth );
            System.out.println("sub array is " + Arrays.toString(s));
            int mid = pviot(s, lastSegLenth/2);
            midians.add(mid);
        }

       // for(int i = gSize*5; i < arr.length; i++) midians.add(arr[i]);
        System.out.println("median is " + midians);

        int midIdx = arr.length/5 == 0? arr.length/10:arr.length/10+1;
        return  pviot(midians.stream().mapToInt(i -> i).toArray(), midIdx);

    }

    int select(int[] arr, int k){
        //T(n/5)
       int  pviot = pviot(arr, arr.length/2);
        System.out.println("pviot is " +pviot);
        //naviely copy the array to partition the array [left][pviot][right..]
        //use extra memory here
        //may improve
       ArrayList<Integer> left = new ArrayList<>();
       ArrayList<Integer> right = new ArrayList<>();
        ArrayList<Integer> mid = new ArrayList<>();
       for(int i: arr){
           if(i < pviot) left.add(i);
           else if (i > pviot) {
               right.add(i);
           }else{
              mid.add(i);
           }
       }

        //T(n - n/10*3) = T(7/10*n),we can  exclude at least 3/10*N element.
       if(k > left.size() + mid.size()){
           int[]  rightSubArr = right.stream().mapToInt(i -> i).toArray();
           return select(rightSubArr, k - left.size() - mid.size());
       } else if (k <= left.size()) {
           System.out.println("left is " + left);
           int[] leftSubArr = left.stream().mapToInt(i -> i).toArray();
           return select(leftSubArr, k);
       }else{
           return pviot;
       }

    }

    public static void main(String[] args) {
      //  int[] arr = new int[]{0,1,3,4,5,6,7,8,9,1,1,1,1};
        int[] arr  = new int[]{9,10,11,4,5,6,7,12,13,14,0,1,3};

        Dselect dselect = new Dselect();
        int res = dselect.select(arr, 4);
        System.out.println("res is "+ res );
    }


}

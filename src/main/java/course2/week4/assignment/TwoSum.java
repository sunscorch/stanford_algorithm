package course2.week4.assignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.IntStream;

public class TwoSum {

    private static long[] arr;
    private static Map<Long, ArrayList<Integer>> map = new HashMap<>();

    TwoSum(long[] arr){
        this.arr = arr;
        for(int i = 0; i < arr.length; i++ ){
            long d = arr[i];
            ArrayList<Integer> k = this.map.getOrDefault(d, new ArrayList<>());
            k.add(i);
            this.map.put(d,k);
        }
    }

    /*
    private  static boolean verify(int target){
        Set<Long> set = new HashSet<>();

        for(long d: arr){
            long k = target - d;
           // System.out.println("if outer k is " + k + " d is " + d);

            if(k != d){
                if(map.containsKey(k) &&
                        (map.get(k).size() > 1 || map.get(d).size()>1)) return false;
                else if(map.containsKey(k) && map.get(k).size() == 1 && !set.contains(k)){
                    set.add(k);
                    set.add(d);
                  //  System.out.println("k is " + k + " d is " + d);
                }
            }else{
                // k == d
                if(map.containsKey(k)&& map.get(k).size() == 2 && !set.contains(k)){
                    set.add(k);
                }else if(map.containsKey(k) && map.get(k).size() > 2) return false;
            }
        }
      //  System.out.println("set is " + set);
        return (set.size() > 0);
    }
    */

    private  static boolean verify(int target){
        for(long d: arr){
            long k = target - d;
            if(k != d){
                if(map.containsKey(k)) return true;
            }else{
                if(map.get(k).size() >=2) return true;
            }

        }
        return false;
    }

    static long[]  readFromFile(String filename) throws FileNotFoundException {
        int i = 0;
        long[] arr = new long[1000000];
        try (Scanner s = new Scanner(new FileReader(filename))) {
            while (s.hasNext()) {
                arr[i++] = Long.valueOf(s.nextLine());
            }
            return arr;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        long[] arr = TwoSum.readFromFile("src/main/java/course2/week4/assignment/2sum.txt");
        TwoSum ts = new TwoSum(arr);


        int[] range = IntStream.rangeClosed(-10000, 10000).toArray();


        int[] r = Arrays.stream(range).parallel().filter(x -> TwoSum.verify(x)).toArray();
        System.out.println(Arrays.toString(r));
        System.out.println(r.length);

    }
}

package course2.week3.assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Assignment {
    //min heap
    public PriorityQueue<Integer> q2 = new PriorityQueue<>((n1,n2)-> n1-n2);
    //max heap
    public PriorityQueue<Integer> q1 = new PriorityQueue<>((n1,n2)-> n2-n1);

    ArrayList<Integer> findMidInStream(int[] arr){
        ArrayList<Integer> res = new ArrayList<>();
        int count = 0;
        for(int d: arr){
            count++;
            //corner case for 1st element
            if(q1.size() == 0 ){
                q1.add(d);
                res.add(calculateMid(count));
                continue;
            }
            //corner case for 2nd element
            if(q2.size() == 0){
                q1.add(d);
                q2.add(q1.poll());
                res.add(calculateMid(count));
                continue;
            }

          if(q1.size() == q2.size()){
               if(d > q1.peek()) q2.add(d);
               else{
                   q1.add(d);
                   q2.add(q1.poll());
               }
           }else if(q1.size() < q2.size()){
                if(d < q2.peek()) q1.add(d);
                else{
                    q2.add(d);
                    q1.add(q2.poll());
                }
            }
            res.add(calculateMid(count));
        }
        return res;
    }


    Integer calculateMid(int count){
        if(count == 1) return q1.peek();
        else if(count %2 == 0) return q1.peek();
        else{
            return  q2.peek();
        }
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
        Assignment a = new Assignment();
        int[] arr = getArrayFromFile("src/main/java/course2/week3/assignment/Median.txt");
        ArrayList<Integer> r = a.findMidInStream(arr);
        Integer sum = r.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum%10000);
    }
}

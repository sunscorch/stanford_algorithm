package course1.week4.KargerMinCut;

import org.apache.commons.lang.SerializationUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
1)  Initialize contracted graph CG as copy of original graph
2)  While there are more than 2 vertices.
      a) Pick a random edge (u, v) in the contracted graph.
      b) Merge (or contract) u and v into a single vertex (update
         the contracted graph).
      c) Remove self-loops
3) Return cut represented by two vertices.

*/
public class MinCut {

    private Graph contract(Graph graph){
        //get random u
        String[] arrayU =  graph.keySet().stream()
                                .toArray(String[] ::new);
        int rnd = new Random().nextInt(arrayU.length);
        String u = arrayU[rnd];

        //get random v
        rnd = new Random().nextInt(graph.get(u).size());
        String v = graph.get(u).get(rnd);

        String newKey = u+"-"+v;
        ArrayList<String> mergedList = graph.get(u);
        mergedList.addAll(graph.get(v));

        mergedList.removeIf( name -> (name.equals(u) ||
                                      name.equals(v)));

        graph.put(newKey, mergedList);

        graph.remove(u);
        graph.remove(v);

        //for other Vertices , we remove (u, v)from  its adjacelist as u v is merged into new one ertices
        for(ArrayList<String> l : graph.values()){

         for(int i = 0; i < l.size(); i++){
             if(l.get(i).equals(u) || l.get(i).equals(v)){
                 l.set(i,newKey);
             }

         }

        }

        return graph;
    }

    public int minCut(Graph graph){
        int n = graph.size();
        int min = Integer.MAX_VALUE;
        for(long i = 0; i<n; i++){
            Graph g = (Graph) SerializationUtils.clone(graph);

            while(g.size() > 2){
                g = contract(g);
            }

            Set<String> s = g.keySet();
            String[] arr = s.stream().toArray(String[] ::new);
            int curMin = g.get(arr[0]).size();
            min = Math.min(min, curMin);
        }
        return min;


    }

    private Graph buildGraph(String fileName){
        Graph g = new Graph();
        File f = new File(fileName);
        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr);) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] curLine = line.split("\t");
                String key = curLine[0];
                ArrayList<String> value = Stream.of(curLine).collect(
                            Collectors.toCollection(ArrayList::new));
               value.remove(0);
               g.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return g;
    }

    public static void main(String[] args) {

         MinCut minCut = new MinCut();
         Graph g1 = minCut.buildGraph("src/main/java/course1/week4/KargerMinCut/kargerMinCut.txt");

        System.out.println(minCut.minCut(g1));
    }
}

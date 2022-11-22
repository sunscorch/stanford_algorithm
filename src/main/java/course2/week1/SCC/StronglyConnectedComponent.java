package course2.week1.SCC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class StronglyConnectedComponent {

    private BitSet visited ;
    private Stack<Integer> stack = new Stack<>();

    //dfs for the first around
    private void dfs1(Graph g, int nodeId){
        this.visited.set(nodeId-1);
        //System.out.println("node id is " + nodeId);
        LinkedList<Integer> nextNodes = g.get(nodeId);
        if(nextNodes == null) {
            this.stack.push(nodeId);
            return;
        }

        for(int node: nextNodes){
            if(!this.visited.get(node-1)){
                dfs1(g, node);
            }
        }

        this.stack.push(nodeId);
    }

    //dfs for the second round.
    private void dfs2(Graph g, int nodeId, HashSet<Integer> group){
        this.visited.set(nodeId-1);
        group.add(nodeId);

        LinkedList<Integer> nextNodes = g.get(nodeId);
        if(nextNodes == null) {
            return;
        }
        for(int node: nextNodes){
            if(!this.visited.get(node-1)){
                dfs2(g, node, group);

            }
        }
    }

    public HashSet<HashSet<Integer>> findScc(Graph g){
        this.visited = new BitSet(g.size());
        for(int nodeId : g.keySet()){

            if(!this.visited.get(nodeId-1)){
                dfs1(g, nodeId);
            }
        }

        this.visited.clear();

        Graph reversedGraph = g.getReverseGraph();
        HashSet<HashSet<Integer>> res = new HashSet<>();

        //System.out.println("stack is " + this.stack);

        while(!this.stack.isEmpty()){
            int curNode = stack.pop();
            if(!this.visited.get(curNode-1)){
                HashSet<Integer> group = new HashSet<>();
                dfs2(reversedGraph, curNode, group);
                res.add(group);
            }

        }
        return res;
    }

    private Graph buildGraph(String path){
        File f = new File(path);
        Graph g = new Graph();
        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr);) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] curLine = line.split("\\s+");
                int x = Integer.parseInt(curLine[0]);
                int y = Integer.parseInt(curLine[1]);
                g.addEdge(x,y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return g;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

/* for testing small set
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        graph.addEdge(6, 7);
        System.out.println("gragh is " + graph);
        StronglyConnectedComponent scc = new StronglyConnectedComponent();
        HashSet<HashSet<Integer>> res = scc.findScc(graph);
        System.out.println("res is " + res);

*/

        //important note
        // use java vm options: -Xss16m to avoid the issue
        //I guess the problem here is I use HashSet<Integer> group to store temp group, as this can be huge, we encounter stackoverflow
        // or the dfs path may be too deep

        StronglyConnectedComponent scc = new StronglyConnectedComponent();
        Graph g = scc.buildGraph("src/main/java/course2/week1/SCC/SCC.txt");

        HashSet<HashSet<Integer>> res = scc.findScc(g);
        Integer[] arr = res.stream().map(t -> t.size()).toArray(Integer[]::new);
        Arrays.sort(arr,Comparator.reverseOrder());
        Arrays.stream(arr).limit(5).forEach(System.out::println);


    }
}

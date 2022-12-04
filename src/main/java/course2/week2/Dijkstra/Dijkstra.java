package course2.week2.Dijkstra;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Dijkstra {
    BitSet visited;
    int[] parent;
    Graph graph;
    int[] minCost;
    //PQ TO STORE <v, minCost[v]>
    //PQ CAN ALSO STORE <v, w>,  this is also correct based on greedy algo, we always choose the closest Edge
    PriorityQueue<Pair<Integer,Integer>> queue = new PriorityQueue<>((n1, n2)-> n1.getValue() - n2.getValue());

    Dijkstra(Graph graph){
        this.graph = graph;
        this.visited = new BitSet(graph.size()+1);
        parent = new int[graph.size()+1];
        Arrays.fill(parent,-1);

        minCost = new int[graph.size()+1];
        Arrays.fill(minCost, Integer.MAX_VALUE);
    }

    public void findMinCost4AllVertex(int source){
        minCost[source] = 0;
        Pair<Integer, Integer> sourceNode = new Pair(source,0);
        queue.add(sourceNode);
        parent[0] = -1;

        while(!queue.isEmpty()){
            //find out the min in the q and mark it as visited
            Pair<Integer,Integer> curMin = this.queue.poll();
            visited.set(curMin.getKey());
            LinkedList<AdjacentNode> adjacentNodes = this.graph.get(curMin.getKey());
            for(AdjacentNode vertex : adjacentNodes){
                int id = vertex.vertex;
                int w = vertex.weight;

                //from the current min vertex poped from the heap,
                //we check every its adjacent vertex,
                // update minCost[adjacent] = min{minCost[adjacent], minCost[curmin]+w}
                //push this adjacent node to Q

                if(!visited.get(id) &&
                        minCost[curMin.getKey()] + w < minCost[id]){

                    minCost[id] = minCost[curMin.getKey()] + w;
                    parent[id] = curMin.getKey();
                    this.queue.add(new Pair<>(id, minCost[id]));
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        //example from https://www.cs.usfca.edu/~galles/visualization/Dijkstra.html
        g.addEdge(0,1,5);
        g.addEdge(0,2,9);
        g.addEdge(0,3,2);

        g.addEdge(1,0,5);
        g.addEdge(1,2,1);
        g.addEdge(1,3,4);

        g.addEdge(2,0,9);
        g.addEdge(2,1,1);
        g.addEdge(2,4,8);

        g.addEdge(3,0,2);
        g.addEdge(3,1,4);
        g.addEdge(3,5,9);

        g.addEdge(4,2,8);
        g.addEdge(4,6,4);

        g.addEdge(5,3,9);

        g.addEdge(6,4,4);

        g.addEdge(7,null,null);

        Dijkstra d = new Dijkstra(g);
        d.findMinCost4AllVertex(0);
        System.out.println(Arrays.toString(d.minCost));
        //[0, 5, 6, 2, 14, 11, 18, 2147483647]
        System.out.println(Arrays.toString(d.parent));

    }

}

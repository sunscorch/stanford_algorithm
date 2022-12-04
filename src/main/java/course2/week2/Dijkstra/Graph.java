package course2.week2.Dijkstra;


import java.util.HashMap;
import java.util.LinkedList;

public class Graph extends HashMap<Integer, LinkedList<AdjacentNode>> {

    Graph addEdge(Integer u, Integer v, Integer weight){
        LinkedList<AdjacentNode> adjacentNodes = this.getOrDefault(u, new LinkedList<AdjacentNode>());
        if(v != null){
            adjacentNodes.add(new AdjacentNode(v,weight));
        }
        this.put(u, adjacentNodes);

        return this;
    }


}

package course2.week2.MinSpaningTree;


//write some min spaning tree to understand the shortest path algo
/*

Create a set mstSet that keeps track of vertices already included in MST.
Assign a key value to all vertices in the input graph. Initialize all key values as INFINITE.
Assign the key value as 0 for the first vertex so that it is picked first.
While mstSet doesn’t include all vertices
Pick a vertex u which is not there in mstSet and has a minimum key value.
Include u in the mstSet.
Update the key value of all adjacent vertices of u. To update the key values, iterate through all adjacent vertices.
For every adjacent vertex v, if the weight of edge u-v is less than the previous key value of v, update the key value as the weight of u-v


* Use a boolean array mstSet[] to represent the set of vertices included in MST.
* If a value mstSet[v] is true, then vertex v is included in MST, otherwise not.
* Array minCost[] is used to store key values of all vertices.
* Another array parent[] to store indexes of parent nodes in MST.
* The parent array is the output array, which is used to show the constructed MST.
* */


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.BitSet;

//use adjaceent matrix to represent a graph
public class PrimAlgo {
    int[][] graph;
    //num of vertex
    int vNum;

    //among all the edge u->v
    // minCost[v] min cost to reach vertex v;
    int[] minCost;
    BitSet visited;
    int[] parent;

    //among the unvisited vertex, find out the lowest weight edge
    //vertex 0 should be
    private int reachVertexWithLowestCost(){
        int resIdx = -1;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < vNum; i++){
            if(visited.get(i) == false && min > minCost[i]){
                resIdx = i;
                min = minCost[i];
            }
        }
        return resIdx;
    }
    void printMST()
    {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < vNum; i++)
            System.out.println(parent[i] + " - " + i + "\t"
                    + this.graph[i][parent[i]]);
    }
    public int[] primMST(int[][] g){
        //do some init process
        this.graph = g;
        this.vNum = g.length;
        this.minCost = new int[vNum];
        this.visited = new BitSet(vNum);
        this.parent = new int[vNum];

        //
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[0] = 0;

        parent[0] = -1;


        //we just need to find (vertexSize -1) edge
        for(int count = 0; count < vNum - 1; count++){
            int u = reachVertexWithLowestCost();
            visited.set(u);
            //for u's adjacent vertex(unvisited), update their u->v's cost to the lower one
            for(int v = 0; v < vNum; v++){
                if(graph[u][v] != 0 && visited.get(v) == false &&
                   graph[u][v] < minCost[v]){
                    parent[v] = u;
                    minCost[v] = graph[u][v];
                }
            }


        }
        return parent;
        //O(v*v)

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        PrimAlgo mst = new PrimAlgo();
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };
        mst.primMST(graph);
        mst.printMST();


    }



}

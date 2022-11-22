package course2.week1.SCC;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph extends HashMap<Integer,LinkedList<Integer>> {


    Graph(int size){
        //pre allocate some space to improve performance.
        super(size);

    }
    Graph(){

    }
    Graph addEdge(int x, int y){
        LinkedList<Integer> list = this.getOrDefault(x, new LinkedList<>());
        list.add(y);
        this.put(x, list);
        return this;
    }

/*
    public Graph getReverseGraph(){
        Graph res = new Graph(this.size());
        for(int i = 0; i < this.size(); i++){
            res.add(new LinkedList<>());
        }

        for(int i = 0; i < this.size(); i++){
            LinkedList<Integer> verticeList = this.get(i);
            for(int vertice: verticeList){
                res.get(vertice).add(i);

            }
        }
        return res;

    }
*/

    public Graph getReverseGraph(){
        Graph res = new Graph(this.size());
        for (Map.Entry<Integer, LinkedList<Integer>> entry : this.entrySet()) {
            LinkedList<Integer> vertices = entry.getValue();
            Integer k = entry.getKey();
            for(Integer vertice: vertices){
                LinkedList<Integer> list = res.getOrDefault(vertice,new LinkedList<>());
                list.add(k);
                res.put(vertice, list);
            }

        }
        return res;
    }


}
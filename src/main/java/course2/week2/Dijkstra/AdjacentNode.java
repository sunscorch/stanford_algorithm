package course2.week2.Dijkstra;

public class AdjacentNode {
    int vertex;
    int weight;

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public AdjacentNode(int vertex, int weight){
        setVertex(vertex);
        setWeight(weight);
    }

    @Override
    public String  toString(){
        String formatted = String.format("[%s,%s]", vertex, weight);
        return formatted;

    }
}

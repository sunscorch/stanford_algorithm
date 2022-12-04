package course2.week2.Dijkstra;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Assignment {
    static Graph readFile(String path){
        File f = new File(path);
        Graph g = new Graph();
        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr);) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] curLine = line.split("\t");
                int u = Integer.parseInt(curLine[0]);
                for(int i = 1; i < curLine.length; i++){
                    String[] pair= curLine[i].split(",");
                    int v = Integer.parseInt(pair[0]);
                    int w = Integer.parseInt(pair[1]);
                    g.addEdge(u,v,w);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return g;
    }

    public static void main(String[] args) {
        Graph g = readFile("src/main/java/course2/week2/Dijkstra/dijkstraData.txt");
        Dijkstra d = new Dijkstra(g);
        d.findMinCost4AllVertex(1);
        int[] arr = d.minCost;
        int[] res = new int[]{arr[7], arr[37], arr[59], arr[82], arr[99], arr[115], arr[133], arr[165], arr[188], arr[197]};
        System.out.println(Arrays.toString(res).replaceAll(" ",""));




    }
}

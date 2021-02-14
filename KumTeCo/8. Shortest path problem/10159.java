import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    final static int INF = 1000000000;
    int size;

    int[][] edges;

    public Graph(int size){
        this.size = size;

        edges = new int[size][];
        for(int i = 0 ; i < size ; i++){
            edges[i] = new int[size];
        }
    }

    public void addEdge(int bigger, int smaller){
        bigger--; smaller--;
        edges[bigger][smaller] = 1;
    }

    private long[][] createDistances(){
        long[][] distances = new long[size][];

        for(int i = 0 ; i < size ; i++){
            distances[i] = new long[size];
            for(int j = 0 ; j < size ; j++){
                if(i == j) continue;
                if(edges[i][j] == 0)
                    distances[i][j] = INF;
                else
                    distances[i][j] = 1;
            }
        }

        return distances;
    }

    private long countUnknown(int x, long[][] distances){
        long count = size - 1;
        for(int i = 0 ; i < size ; i++){
            if(distances[i][x] != INF && distances[i][x] != 0)
                count--;
            else if(distances[x][i] != INF && distances[x][i] != 0)
                count--;
        }
        return count;
    }

    public void floydWarshall(){
        long[][] distances = createDistances();

        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                for(int k = 0 ; k < size ; k++){
                    long sum = distances[j][i] + distances[i][k];
                    if(sum < distances[j][k]){
                        distances[j][k] = sum;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < size ; i++){
            sb.append(countUnknown(i, distances)).append("\n");
        }
        System.out.print(sb.toString());
    }
}


public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Graph graph = new Graph(N);

        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int bigger = Integer.parseInt(st.nextToken());
            int smaller = Integer.parseInt(st.nextToken());

            graph.addEdge(bigger, smaller);
        }

        graph.floydWarshall();

    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    long INF = 1000000000;
    int size;

    int[][] edges;

    public Graph(int size){
        this.size = size;

        edges = new int[size][];
        for(int i = 0 ; i < size ; i++){
            edges[i] = new int[size];
        }
    }

    public void addEdge(int origin, int destination, int weight){
        origin--; destination--;
        edges[origin][destination] = weight;
    }

    private long[][] createDistances(){
        long[][] distances = new long[size][];
        for(int i = 0 ; i < size ; i++){
            distances[i] = new long[size];
            for(int j = 0 ; j < size ; j++){
                if(i == j) continue;
                if(edges[i][j] != 0){
                    distances[i][j] = edges[i][j];
                }
                else{
                    distances[i][j] = INF;
                }
            }
        }

        return distances;
    }

    public long floydWarshall(){
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

        long min = Long.MAX_VALUE;
        for(int start = 0 ; start < size ; start++){
            for(int end = 0 ; end < size ; end++){
                if(start == end) continue;

                long dist1 = distances[start][end];
                long dist2 = distances[end][start];
                if(dist1 != INF && dist2 != INF){
                    min = Math.min(min, dist1 + dist2);
                }
            }
        }

        if(min == Long.MAX_VALUE)
            return -1;

        return min;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(V);

        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.addEdge(a, b, c);
        }

        long result = graph.floydWarshall();
        System.out.println(result);
    }
}
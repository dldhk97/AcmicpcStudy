import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    final static long INF = 1000000000;
    int size;

    long[][] edges;
    public Graph(int size){
        this.size = size;

        edges = new long[size][];
        for(int i = 0 ; i < size ; i++){
            edges[i] = new long[size];
            Arrays.fill(edges[i], INF);
        }
    }

    public void addEdge(int origin, int destination, int weight){
        origin--; destination--;
        edges[origin][destination] = Math.min(edges[origin][destination], weight);
    }

    private long[][] initDistances(){
        long[][] distances = new long[size][];
        for(int i = 0 ; i < size ; i++){
            distances[i] = new long[size];
            for(int j = 0 ; j < size ; j++){
                if(i == j) continue;
                distances[i][j] = edges[i][j];
            }
        }
        return distances;
    }

    public long[][] floydWarshall(){
        long[][] distances = initDistances();

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

        return distances;
    }

    public void print(long[][] distances){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                long value = distances[i][j] == INF ? 0 : distances[i][j];
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        Graph graph = new Graph(n);

        for(int i = 0 ; i < m ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int origin = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.addEdge(origin, destination, weight);
        }

        long[][] distances = graph.floydWarshall();
        graph.print(distances);
    }
}
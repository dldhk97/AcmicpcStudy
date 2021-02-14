import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    final static long INF = 1000000000;
    private int size;
    int[][] edges;

    public Graph(int size){
        this.size = size;

        edges = new int[size][];
        for(int i = 0 ; i < size ; i++){
            edges[i] = new int[size];
        }
    }

    public void addEdge(int from, int back){
        from--; back--;

        edges[from][back] = 1;
    }

    private long[][] createDistances(){
        long[][] distances = new long[size][];
        for(int i = 0 ; i < size ; i++){
            distances[i] = new long[size];
            for(int j = 0 ; j < size ; j++){
                if(i == j) continue;
                if(edges[i][j] == 1)
                    distances[i][j] = 1;
                else
                    distances[i][j] = INF;
            }
        }
        return distances;
    }

    long[][] distances;
    public void floydWarshall(){
        distances = createDistances();

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
    }

    public int solve(int front, int back){
        front--; back--;
        if(distances[front][back] != INF && distances[front][back] != 0){
            return -1;
        }
        if(distances[back][front] != INF && distances[back][front] != 0){
            return 1;
        }
        return 0;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(n);

        for(int i = 0 ; i < k ; i++){
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            graph.addEdge(front, back);
        }

        graph.floydWarshall();

        int s = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < s ; i++){
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            int result = graph.solve(front, back);
            sb.append(result).append("\n");
        }

        System.out.print(sb.toString());
    }
}
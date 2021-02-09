import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    LinkedList<Node>[] edges;

    public Graph(int size){
        this.size = size;

        edges = new LinkedList[size + 1];
        for(int i = 0 ; i < size + 1 ; i++){
            edges[i] = new LinkedList<>();
        }
    }

    public void addEdge(int origin, int destination, int weight){
        edges[origin].add(new Node(destination, weight));
    }

    private boolean isCycleExists(long[] distances){
        for(int i = 1 ; i <= size ; i++){
            for(Node adj : edges[i]){
                long val = distances[i] == Long.MAX_VALUE ? 0 : distances[i];
                long sum = val + adj.weight;

                if(sum < distances[adj.vertex]){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean bellmanFord(){
        long[] distances = new long[size + 1];
        Arrays.fill(distances, Long.MAX_VALUE);

        for(int i = 0 ; i < size - 1 ; i++){
            for(int j = 1 ; j <= size ; j++){
                for(Node adj : edges[j]){
                    long val = distances[j] == Long.MAX_VALUE ? 0 : distances[j];
                    long sum = val + adj.weight;

                    if(sum < distances[adj.vertex]){
                        distances[adj.vertex] = sum;
                    }
                }
            }
        }

        if(isCycleExists(distances)){
            return true;
        }

        return false;
    }
}

class Node{
    int vertex;
    long weight;

    public Node(int vertex, long weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(TC-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            Graph graph = new Graph(N);

            for(int i = 0 ; i < M ; i ++){
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                graph.addEdge(S, E, T);
                graph.addEdge(E, S, T);
            }

            for(int i = 0 ; i < W ; i ++){
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                graph.addEdge(S, E, -T);
            }

            String result = graph.bellmanFord() ? "YES" : "NO";
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}
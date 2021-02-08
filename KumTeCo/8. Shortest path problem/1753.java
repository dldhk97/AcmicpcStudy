import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;

    LinkedList<Edge>[] edges;

    public Graph(int size){
        this.size = size;

        edges = new LinkedList[size + 1];
        for(int i = 0 ; i < size + 1 ; i++){
            edges[i] = new LinkedList<>();
        }
    }

    public void addEdge(int origin, int destination, int weight){
        edges[origin].add(new Edge(destination, weight));
    }

    private void initializeDistances(int[] distances, int start){
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
    }

    void dijkstra(int start){
        // Initialize distances
        int[] distances = new int[size + 1];
        initializeDistances(distances, start);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));               // Visit start node

        while(!pq.isEmpty()){
            Edge polled = pq.poll();

            // Find adjacent nodes
            for(Edge e : edges[polled.destination]){
                int sum = distances[polled.destination] + e.weight;

                // If accumulated distance is smaller than adjacent distance
                if(distances[e.destination] > sum){
                    distances[e.destination] = sum;
                    pq.add(new Edge(e.destination, sum));
                }
            }
        }

        print(distances);
    }

    private void print(final int[] distances){
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i < distances.length ; i++){
            if(distances[i] == Integer.MAX_VALUE)
                sb.append("INF");
            else
                sb.append(distances[i]);
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}

class Edge implements Comparable<Edge>{
    int destination, weight;

    public Edge(int destination, int weight){
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e){
        return this.weight - e.weight;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        Graph graph = new Graph(V);

        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.addEdge(u, v, w);
        }

        graph.dijkstra(K);
    }
}
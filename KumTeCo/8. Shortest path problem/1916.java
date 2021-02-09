import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;

    LinkedList<Node>[] edges;

    public Graph(int size){
        this.size = size;

        edges = new LinkedList[size + 1];
        for(int i = 0 ; i <= size ; i++){
            edges[i] = new LinkedList<>();
        }
    }

    public void addEdge(int origin, int destination, int weight){
        edges[origin].add(new Node(destination, weight));
    }

    public int dijkstra(int origin, int destination){
        int[] distances = new int[size + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[origin] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(origin, 0));

        while(!pq.isEmpty()){
            Node polled = pq.poll();

            for(Node n : edges[polled.destination]){
                int sum = distances[polled.destination] + n.weight;

                if(sum < distances[n.destination]){
                    distances[n.destination] = sum;
                    pq.add(n);
                }
            }
        }

        return distances[destination];
    }
}

class Node implements Comparable<Node>{
    int destination, weight;

    public Node(int destination, int weight){
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node n) {
        return this.weight - n.weight;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Graph graph = new Graph(N);

        for(int i = 1 ; i <= M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.addEdge(o, d, w);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int origin = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());

        int result = graph.dijkstra(origin, destination);
        System.out.println(result);
    }
}
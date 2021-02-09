import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;

    LinkedList<Node>[] edges;
    LinkedList<Node>[] edges2;

    public Graph(int size){
        this.size = size;

        edges = new LinkedList[size + 1];
        for(int i = 0 ; i <= size ; i++){
            edges[i] = new LinkedList<>();
        }

        edges2 = new LinkedList[size + 1];
        for(int i = 0 ; i <= size ; i++){
            edges2[i] = new LinkedList<>();
        }
    }

    public void addEdge(int origin, int destination, int weight){
        edges[origin].add(new Node(destination, weight));
        edges2[destination].add(new Node(origin, weight));
    }

    public int solve(int origin){
        int[] distances = dijkstra(origin, edges);
        int[] reversedDistances = dijkstra(origin, edges2);

        int max = Integer.MIN_VALUE;
        for(int i = 1 ; i <= size ; i++){
            int sum = distances[i] + reversedDistances[i];
            max = Math.max(max, sum);
        }
        return max;
    }

    private int[] dijkstra(int origin, LinkedList<Node>[] edges){
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

        return distances;
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N);

        for(int i = 1 ; i <= M ; i++){
            st = new StringTokenizer(br.readLine());
            int origin = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.addEdge(origin, destination, weight);
        }

        int result = graph.solve(X);
        System.out.println(result);
    }
}
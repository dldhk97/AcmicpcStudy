import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    LinkedList<Integer>[] edges;

    public Graph(int size){
        this.size = size;

        edges = new LinkedList[size + 1];
        for(int i = 0 ; i < size + 1 ; i++){
            edges[i] = new LinkedList<>();
        }
    }

    public void addEdge(int origin, int destination){
        edges[origin].add(destination);
    }

    public void dijkstra(int start, int K){
        int[] distances = new int[size + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(start);

        while(!pq.isEmpty()){
            int polled = pq.poll();

            for(int e : edges[polled]){
                int sum = distances[polled] + 1;
                if(sum < distances[e]){
                    distances[e] = sum;
                    pq.add(e);
                }
            }
        }

        print(distances, K);
    }

    private void print(int[] distances, int K){
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i < size + 1 ; i++){
            if(distances[i] == K){
                sb.append(i);
                sb.append("\n");
            }
        }

        if(sb.length() == 0){
            sb.append("-1");
        }

        System.out.print(sb.toString());
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N);

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.addEdge(A, B);
        }

        graph.dijkstra(X, K);

    }
}
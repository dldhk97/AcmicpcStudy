import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class SPFA {
    final static long INF = 1000000000;
    int size;

    LinkedList<Node>[] edges;

    public SPFA(int size){
        this.size = size;

        edges = new LinkedList[size];
        for(int i = 0 ; i < size ; i++){
            edges[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, long weight){
        source--; destination--;
        edges[source].add(new Node(destination, weight));
    }

    public void solve(int start){
        long[] distances = new long[size];
        Arrays.fill(distances, INF);

        boolean[] containChecker = new boolean[size];
        int[] cycleChecker = new int[size];

        distances[start] = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            int polled = queue.poll();
            containChecker[polled] = false;

            for(Node adj : edges[polled]){
                long sum = distances[polled] + adj.weight;
                if(sum < distances[adj.destination]){
                    distances[adj.destination] = sum;

                    if(!containChecker[adj.destination]){
                        cycleChecker[adj.destination]++;
                        if(cycleChecker[adj.destination] >= size){
                            System.out.println(-1);
                            return;
                        }
                        queue.offer(adj.destination);
                        containChecker[adj.destination] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < size ; i++){
            if(i == start) continue;

            long value = distances[i];
            if(value == INF)
                value = -1;

            sb.append(value).append("\n");
        }
        System.out.print(sb.toString());
    }
}

class Node {
    int destination;
    long weight;

    public Node(int destination, long weight){
        this.destination = destination;
        this.weight = weight;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        SPFA spfa = new SPFA(N);

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            spfa.addEdge(A, B, C);
        }

        spfa.solve(0);
    }
}
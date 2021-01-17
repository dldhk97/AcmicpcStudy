import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    long valSum = 0;

    PriorityQueue<Edge> pq;
    int[] parents;

    public Graph(int size){
        this.size = size;
        pq  = new PriorityQueue<>();
        parents = new int[size];

        for(int i = 0 ; i < size ; i++){
            parents[i] = i;
        }
    }

    public void addEdge(int from, int to, int value){
        Edge edge1 = new Edge(from, to, value);
        pq.add(edge1);
    }

    public int findRoot(int x){
        if(parents[x] == x){
            return x;
        }
        return parents[x] = findRoot(parents[x]);
    }

    public void union(int a, int b){
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);

        if(aRoot == bRoot){
            return;
        }

        parents[aRoot] = b;
    }

    public void doKruskal(){
        int max = 0;
        while(!pq.isEmpty()){
            Edge polled = pq.poll();

            int fromRoot = findRoot(polled.from);
            int toRoot = findRoot(polled.to);

            if(fromRoot == toRoot)
                continue;

            union(polled.from, polled.to);

            if(max < polled.value)
                max = polled.value;

            valSum += polled.value;
        }

        System.out.println(valSum - max);
    }

}

class Edge implements Comparable<Edge>{
    int from, to, value;
    public Edge(int from, int to, int value){
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    public int compareTo(Edge o){
        return this.value - o.value;
    }
}



public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N + 1);

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            graph.addEdge(x, y, z);
        }

        graph.doKruskal();
    }
}
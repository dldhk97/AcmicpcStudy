import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    LinkedList<Edge>[] edges;
    long valSum = 0;

    public Graph(int size){
        this.size = size;
        edges = new LinkedList[size];
        for(int i = 0 ; i < size ; i++){
            edges[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to, int value){
        Edge edge1 = new Edge(from, to, value);
        edges[from].add(edge1);

        Edge edge2 = new Edge(to, from, value);
        edges[to].add(edge2);

        valSum += value;
    }

    public long getReducedValue(){
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new ArrayDeque<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        queue.add(1);

        long mstValSum = 0;

        while(!queue.isEmpty()){
            int polled = queue.poll();

            visited[polled] = true;

            for(Edge e : edges[polled]){
                if(!visited[e.to]){
                    pq.add(e);
                }
            }

            while(!pq.isEmpty()){
                Edge e = pq.poll();

                if(!visited[e.to]){
                    queue.add(e.to);
                    mstValSum += e.value;
                    break;
                }
            }
        }

        return valSum - mstValSum;
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

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if(m == 0 && n == 0){
                break;
            }

            Graph graph = new Graph(m + 1);

            for(int i = 0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                graph.addEdge(x, y, z);
            }

            System.out.println(graph.getReducedValue());
        }


    }
}
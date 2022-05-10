import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph {
    private int size;
    private LinkedList<Edge>[] edges;

    public Graph(int size){
        this.size = size;
        edges = new LinkedList[size];
        for(int i = 0 ; i < size ; i++){
            edges[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to, int value){
        edges[from].add(new Edge(from, to, value));
        edges[to].add(new Edge(to, from, value));
    }

    public List<Edge> makeMST(){
        List<Edge> mst = new ArrayList<>();

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(1);

        while(!queue.isEmpty()){
            int polled = queue.poll();

            visited[polled] = true;

            // 현재 노드에서 방문된 적 없는 노드로 가는 간선을 모두 큐에 추가
            for(Edge e : edges[polled]){
                if(!visited[e.to]){
                    priorityQueue.add(e);
                }
            }

            // 우선순위 큐에서 가장 작은 간선 poll
            while(!priorityQueue.isEmpty()){
                Edge e = priorityQueue.poll();

                // 간선의 도착지가 방문되지 않았다면
                if(!visited[e.to]){
                    queue.add(e.to);        // 해당 노드와 연결된 간선을 탐색하기 위해 큐에 추가
                    mst.add(e);             // 결과 배열에 추가
                    break;
                }
            }
        }

        return mst;
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
    public int compareTo(Edge o) {
        return this.value - o.value;
    }

}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(V + 1);

        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.addEdge(a, b, c);
        }

        List<Edge> mst = graph.makeMST();

        long sum = 0;
        for(Edge e : mst){
            sum += e.value;
        }
        System.out.println(sum);
    }
}
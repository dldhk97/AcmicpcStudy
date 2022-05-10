import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    LinkedList<Integer>[] adj;

    public Graph(int size){
        this.size = size;

        adj = new LinkedList[size];
        for(int i = 0 ; i < size ; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to){
        adj[from].add(to);
        adj[to].add(from);
    }

    public int bfs(){
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(1);

        int cnt = 0;
        while(!queue.isEmpty()){
            int polled = queue.poll();

            if(visited[polled])
                continue;

            visited[polled] = true;
            cnt++;

            for(int adjIdx : adj[polled]){
                queue.add(adjIdx);
            }
        }

        return cnt;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Graph graph = new Graph(N + 1);

        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.addEdge(a, b);
        }

        int cnt = graph.bfs() - 1;
        System.out.println(cnt);



    }
}
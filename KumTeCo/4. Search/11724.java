import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class BFSGraph{
    LinkedList<Integer>[] adj;
    int size;

    public BFSGraph(int size){
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

    public void bfs(){
        boolean[] visited = new boolean[size];

        Queue<Integer> queue = new ArrayDeque<>();

        int cnt = 0;
        for(int i = 1 ; i < size ; i++){
            if(visited[i]){
                continue;
            }

            queue.add(i);

            while(!queue.isEmpty()){
                int polled = queue.poll();
                if(visited[polled]){
                    continue;
                }

                visited[polled] = true;

                for(int adjNode : adj[polled]){
                    queue.add(adjNode);
                }
            }
            cnt++;
        }

        System.out.println(cnt);
    }

}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BFSGraph bfsGraph = new BFSGraph(N + 1);


        for(int i = 0 ; i  < M ; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            bfsGraph.addEdge(from, to);
        }

        bfsGraph.bfs();


    }
}
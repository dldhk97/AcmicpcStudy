import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class DFSGraph{
    private int size;
    private LinkedList<Integer>[] adj;
    private StringBuilder sb;

    public DFSGraph(int size){
        this.size = size;
        adj = new LinkedList[size];

        for(int i = 0 ; i < size ; i++){
            adj[i] = new LinkedList<>();
        }

        sb = new StringBuilder();
    }

    public void addEdge(int from, int to){
        adj[from].add(to);
        adj[to].add(from);
    }

    public void DFSUtil(int from, boolean[] visited){
        visited[from] = true;

        sb.append(from);
        sb.append(" ");

        adj[from].sort(Integer::compareTo);

        for(int x : adj[from]){
            if(!visited[x]){
                DFSUtil(x, visited);
            }
        }
    }

    public void DFS(int from){
        boolean[] visited = new boolean[size];

        DFSUtil(from, visited);

        System.out.println(sb.toString());
    }
}

class BFSGraph{
    private int size;
    private LinkedList<Integer>[] adj;
    private StringBuilder sb = new StringBuilder();

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

    public void BFS(int from){
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(from);

        while(!queue.isEmpty()){
            int x = queue.poll();
            if(!visited[x]){
                visited[x] = true;

                sb.append(x);
                sb.append(" ");

                adj[x].sort(Integer::compareTo);

                for(int adjEdge : adj[x]){
                    queue.add(adjEdge);
                }
            }
        }

        System.out.println(sb.toString());
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        DFSGraph dfsGraph = new DFSGraph(N + 1);
        BFSGraph bfsGraph = new BFSGraph(N + 1);

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            dfsGraph.addEdge(from, to);

            bfsGraph.addEdge(from, to);
        }

        dfsGraph.DFS(V);
        bfsGraph.BFS(V);
    }
}
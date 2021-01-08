import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class BFSGraph {
    private int size;
    private LinkedList<Integer>[] adj;

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
        Queue<int[]> queue = new ArrayDeque<>();
        int[] parents = new int[size];

        int[] root = {1, 1};
        queue.add(root);

        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int nodeIdx = node[0];
            int nodeParentIdx = node[1];

            if(visited[nodeIdx]){
                continue;
            }

            visited[nodeIdx] = true;

            parents[nodeIdx] = nodeParentIdx;

            for(int adjNodeIdx : adj[nodeIdx]){
                int[] adjNode = {adjNodeIdx, nodeIdx};
                queue.add(adjNode);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2 ; i < size ; i++ ){
            sb.append(parents[i]);
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        BFSGraph graph = new BFSGraph(N + 1);

        for(int i = 0 ; i < N - 1 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.addEdge(from, to);
        }

        graph.bfs();

    }
}
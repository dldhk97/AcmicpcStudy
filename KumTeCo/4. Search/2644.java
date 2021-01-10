import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class BFSGraph{
    int size;
    LinkedList<Integer>[] adj;

    public BFSGraph(int size){
        this.size = size;

        adj = new LinkedList[size];
        for(int i = 0 ; i < size ; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void add(int from, int to){
        adj[from].add(to);
        adj[to].add(from);
    }

    public void bfs(int from, int target){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[size];

        queue.add(from);

        int[] weights = new int[size];
        int relationshipCnt = 0;

        while(!queue.isEmpty()){
            int polled = queue.poll();

            if(visited[polled]){
                continue;
            }

            visited[polled] = true;

            if(weights[polled] != 0){
                weights[polled] = Integer.min(relationshipCnt, weights[polled]);
            }
            else {
                weights[polled] = relationshipCnt;
            }

            if(polled == target){
                break;
            }

            for(int adjNode : adj[polled]){
                if(!visited[adjNode]){
                    queue.add(adjNode);
                    weights[adjNode] = weights[polled] + 1;
                }

            }
            relationshipCnt++;
        }

        if(visited[target]){
            System.out.println(weights[target]);
        }
        else{
            System.out.println(-1);
        }

    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        BFSGraph bfsGraph = new BFSGraph(n + 1);

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            bfsGraph.add(from, to);
        }

        bfsGraph.bfs(a, b);



    }
}
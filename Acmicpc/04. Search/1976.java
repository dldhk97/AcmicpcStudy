import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    LinkedList<Integer>[] edges;

    public Graph(int size){
        this.size = size;

        edges  = new LinkedList[size];
        for(int i = 0 ; i < size ; i++){
            edges[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to){
        edges[from].add(to);
    }

    boolean[] visited;
    public void bfs(int startPoint){
        visited = new boolean[size];

        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(startPoint);

        while(!queue.isEmpty()){
            int polled = queue.poll();
            if(visited[polled])
                continue;

            visited[polled] = true;
            for(int i : edges[polled]){
                queue.add(i);
            }
        }
    }

    public boolean solve(int[] arr){
        bfs(arr[0]);

        for(int i : arr){
            if(!visited[i])
                return false;
        }
        return true;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Graph graph = new Graph(N + 1);

        for(int from = 1 ; from <= N ; from++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int to = 1 ; st.hasMoreTokens() ; to++){
                int isEdgeExists = Integer.parseInt(st.nextToken());
                if(isEdgeExists == 1){
                    graph.addEdge(from, to);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[M];
        for(int i = 0 ; st.hasMoreTokens() ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        String answer = graph.solve(arr) ? "YES" : "NO";
        System.out.println(answer);
    }
}
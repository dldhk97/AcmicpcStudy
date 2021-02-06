import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    int[] visited;
    LinkedList<Integer>[] edges;

    public Graph(int size){
        this.size = size;

        edges = new LinkedList[size + 1];
        for(int i = 0 ; i <= size ; i++){
            edges[i] = new LinkedList<>();
        }

        visited = new int[size + 1];
    }

    public void addEdge(int from, int to){
        edges[from].add(to);
        edges[to].add(from);
    }

    public boolean solve(int startPoint){
        if(visited[startPoint] != 0)
            return true;

        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] {startPoint, 1});

        while(!queue.isEmpty()){
            int[] polled = queue.poll();
            int index = polled[0];
            int color = polled[1];

            if(visited[index] != 0){
                if(visited[index] != color)
                    return false;
                continue;
            }

            visited[index] = color;

            for(int nextIdx : edges[index]){
                if(visited[nextIdx] != 0)
                    continue;
                queue.add(new int[] {nextIdx, color * -1});

            }
        }

        return true;
    }
}


public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        while(K-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            Graph graph = new Graph(V);

            for(int i = 1 ; i <= E ; i++){
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.addEdge(from, to);
            }

            boolean bipartite = true;
            for(int i = 1 ; i <= V ; i++){
                if(!graph.solve(i)){
                    bipartite = false;
                    break;
                }
            }

            if(bipartite){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
    }
}
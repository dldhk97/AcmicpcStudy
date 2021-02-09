import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    LinkedList<Node>[] edges;

    public Graph(int size){
        this.size = size;

        edges = new LinkedList[size + 1];
        for(int i = 0 ; i < size + 1 ; i++){
            edges[i] = new LinkedList<>();
        }
    }

    public void addEdge(int origin, int destination, int weight){
        edges[origin].add(new Node(destination, weight));
    }

    private boolean isCycleExists(long[] distances){
        for(int i = 1 ; i <= size ; i++){
            for(Node adj : edges[i]){
                if(distances[i] == Long.MAX_VALUE) continue;

                long sum = distances[i] + adj.weight;
                if(sum < distances[adj.vertex]){
                    return true;
                }
            }
        }
        return false;
    }

    public String bellmanFord(int start){
        long[] distances = new long[size + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[start] = 0;

        // Size - 1만큼 돌면서
        for(int i = 0 ; i < size - 1 ; i++){
            // 모든 노드에 대해
            for(int j = 1 ; j <= size ; j++){
                // 인접 노드 검사
                for(Node adj : edges[j]){
                    if(distances[j] == Long.MAX_VALUE) continue;

                    long sum = distances[j] + adj.weight;
                    if(sum < distances[adj.vertex]){
                        distances[adj.vertex] = sum;
                    }
                }
            }
        }

        // 갱신을 모두 한 이후에 갱신할 노드가 존재한다면, 음수 사이클 존재
        // 음수 사이클이 있으면 끝없이 갱신이 가능하므로
        boolean isCycleExists = isCycleExists(distances);
        boolean isStartIsolated = edges[start].size() <= 0;

        // 시작점에서 출발하는 점이 있고 사이클이 생긴 경우(= 1에서 출발해 도시로 가는 과정에서 시간을 무한이 오래 전으로 되돌릴 수 있다면)
        if(!isStartIsolated && isCycleExists)
            return "-1";

        StringBuilder sb = new StringBuilder();
        for(int i = 2 ; i < size + 1 ; i++){
            long value = distances[i] == Long.MAX_VALUE ? -1 : distances[i];
            sb.append(value);
            sb.append("\n");
        }
        return sb.toString();
    }
}

class Node{
    int vertex;
    long weight;
    public Node(int vertex, long weight){
        this.vertex = vertex;
        this.weight = weight;
    }

}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N);

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.addEdge(A, B, C);
        }

        String result = graph.bellmanFord(1);
        System.out.println(result);
    }
}
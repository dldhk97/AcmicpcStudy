import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    static final int INF = 100000;
    int size;

    List<int[]> points;
    LinkedList<Node>[] edges;

    public Graph(int size){
        this.size = size;

        points = new ArrayList<>();
        edges = new LinkedList[size];
    }

    public void addNode(int x, int y){
        points.add(new int[]{x, y});
    }

    private LinkedList<Node>[] initEdges(){
        // init edges as INF
        for(int x = 0 ; x < size ; x++){
            edges[x] = new LinkedList<>();
            for(int y = 0 ; y < size ; y++){
                long value = x == y ? 0 : INF;
                edges[x].add(new Node(y, value));
            }
        }

        // create all edge using points
        for(int x = 0 ; x < size ; x++){
            int[] n = points.get(x);
            for(int y = x + 1 ; y < size ; y++){
                int[] m = points.get(y);

                long weight = Math.abs(n[0] - m[0]) + Math.abs(n[1] - m[1]);
                if(weight < edges[x].get(y).weight || weight < edges[y].get(x).weight){
                    edges[x].get(y).weight = weight;
                    edges[y].get(x).weight = weight;
                }
            }
        }

        return edges;
    }

    public long[][] floydWarshall(){
        LinkedList<Node>[] edges = initEdges();

        long[][] distances = new long[size][];
        for(int x = 0 ; x < size ; x++){
            distances[x] = new long[size];
            for(int y = 0 ; y < size ; y++){
                distances[x][y] = edges[x].get(y).weight;
            }
        }

        // 거쳐가는 노드
        for(int i = 0 ; i < size ; i++){
            // 시작 노드
            for(int j = 0 ; j < size ; j++){
                // 도착 노드
                for(int k = 0 ; k < size ; k++){
                    long sum = distances[j][i] + distances[i][k];
                    if(sum < distances[j][k]){
                        distances[j][k] = sum;
                    }
                }
            }
        }

        return distances;
    }

    public boolean solve(long[][] distances){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[size];

        queue.add(0);

        while(!queue.isEmpty()){
            int polled = queue.poll();
            if(visited[polled]) continue;

            if(polled == size - 1){
                return true;
            }

            visited[polled] = true;

            for(Node adj : edges[polled]){
                if(visited[adj.vertex]) continue;

                long weight = distances[polled][adj.vertex];
                if(weight > 1000) continue;

                queue.add(adj.vertex);
            }
        }

        return false;
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

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());

            Graph graph = new Graph(n + 2);

            StringTokenizer st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            graph.addNode(startX, startY);

            for(int i = 0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph.addNode(x, y);
            }

            st = new StringTokenizer(br.readLine());

            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            graph.addNode(endX, endY);

            long[][] distances = graph.floydWarshall();
            String result = graph.solve(distances) ? "happy" : "sad";
            sb.append(result).append("\n");
        }
        System.out.print(sb.toString());
    }
}
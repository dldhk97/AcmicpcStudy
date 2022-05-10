import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    int[][] edges;

    static final int INF = 100000000;

    public Graph(int size){
        this.size = size;

        edges = new int[size][];
        for(int x = 0 ; x < size ; x++){
            edges[x] = new int[size];
            for(int y = 0 ; y < size ; y++){
                edges[x][y] = INF;
            }
        }
    }

    public void addEdge(int origin, int destination){
        edges[origin][destination] = 1;
    }

    private void printDistances(int[][] distances){
        StringBuilder sb = new StringBuilder();
        for(int y = 0 ; y < size ; y++){
            for(int x = 0 ; x < size ; x++){
                if(distances[x][y] == INF)
                    sb.append("0");
                else
                    sb.append("1");
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public void floydWarshall(){
        int[][] distances = new int[size][];
        for(int i = 0 ; i < size ; i++){
            distances[i] = Arrays.copyOf(edges[i], edges.length);
        }

        // 거쳐가는 노드
        for(int i = 0 ; i < size ; i++){
            // 시작 노드
            for(int j = 0 ; j < size ; j++){
                // 도착 노드
                for(int k = 0 ; k < size ; k++){
                    int sum = distances[j][i] + distances[i][k];
                    if(sum < distances[j][k]){
                        distances[j][k] = sum;
                    }
                }
            }
        }

        printDistances(distances);
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Graph graph = new Graph(N);

        for(int y = 0 ; y < N ; y++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < N ; x++){
                int value = Integer.parseInt(st.nextToken());

                if(value == 0) continue;
                graph.addEdge(x, y);
            }
        }

        graph.floydWarshall();
    }
}
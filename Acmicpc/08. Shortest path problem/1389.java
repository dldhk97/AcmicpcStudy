import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


class Graph{
    final int INF = 25000000;

    int size;

    int[][] edges;

    public Graph(int size){
        this.size = size;

        edges = new int[size + 1][];
        for(int i = 0 ; i < size + 1 ; i++){
            edges[i] = new int[size + 1];
            for(int j = 0 ; j < size + 1 ; j++){
                if(i == j) continue;
                edges[i][j] = INF;
            }
        }
    }

    public void addEdge(int origin, int destination){
        edges[origin][destination] = Math.min(edges[origin][destination], 1);
        edges[destination][origin] = Math.min(edges[destination][origin], 1);
    }

    public void floydWarshall(){
        int[][] distances = new int[size + 1][];
        for(int i = 0 ; i < size + 1 ; i++){
            distances[i] = new int[size + 1];
            distances[i] = Arrays.copyOf(edges[i], edges[i].length);
        }

        // 거쳐가는 노드
        for(int i = 0 ; i < size + 1 ; i++){
            // 출발 노드
            for(int j = 0 ; j < size + 1 ; j++){
                // 도착 노드
                for(int k = 0 ; k < size + 1 ; k++){
                    int sum = distances[j][i] + distances[i][k];
                    if(sum < distances[j][k]){
                        distances[j][k] = sum;
                    }
                }
            }
        }

        int minSum = Integer.MAX_VALUE;
        int minSumIdx = -1;
        for(int x = 1 ; x < size + 1 ; x++){
            int sum = 0;
            for(int y = 1 ; y < size + 1 ; y++){
                sum += distances[y][x];
            }
            if(sum < minSum){
                minSum = sum;
                minSumIdx = x;
            }
        }
        System.out.println(minSumIdx);

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

            graph.addEdge(A, B);
        }

        graph.floydWarshall();
    }
}
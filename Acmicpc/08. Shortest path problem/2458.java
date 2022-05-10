import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    final static int INF = 1000000000;
    int size;

    int[][] edges;
    public Graph(int size){
        this.size = size;

        edges = new int[size][];
        for(int i = 0 ; i < size ; i++){
            edges[i] = new int[size];
        }
    }

    public void addEdge(int a, int b){
        a--; b--;
        edges[a][b] = 1;
    }

    private int[][] createDistances(){
        int[][] distances = new int[size][];
        for(int i = 0 ; i < size ; i++){
            distances[i] = new int[size];
            for(int j = 0 ; j < size ; j++){
                if(i == j) continue;
                distances[i][j] = INF;
            }
        }

        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                if(edges[i][j] == 1)
                    distances[i][j] = 1;
            }
        }

        return distances;
    }

    private int countChildren(int x, int[][] distances){
        int cnt = 0;

        for(int i = 0 ; i < size ; i++){
            if(distances[i][x] == 0 || distances[i][x] == INF) continue;
            cnt++;
        }
        return cnt;
    }

    private int countParent(int x, int[][] distances){
        int cnt = 0;

        for(int i = 0 ; i < size ; i++){
            if(distances[x][i] == 0 || distances[x][i] == INF) continue;
            cnt++;
        }
        return cnt;
    }

    public int floydWarshall(){
        int[][] distances = createDistances();

        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                for(int k = 0 ; k < size ; k++){
                    int sum = distances[j][i] + distances[i][k];
                    if(sum < distances[j][k]){
                        distances[j][k] = sum;
                    }
                }
            }
        }

        int cnt = 0;
        for(int i = 0 ; i < size ; i++){
            int parentCnt = countParent(i, distances);
            int childrenCnt = countChildren(i, distances);

            int sum = childrenCnt + parentCnt;
            if(sum == size - 1){
                cnt++;
            }
        }

        return cnt;
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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.addEdge(a, b);
        }

        int result = graph.floydWarshall();
        System.out.println(result);
    }
}
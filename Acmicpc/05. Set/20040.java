import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    int[] parents;

    boolean isGameOver = false;
    int unionCnt = 0;

    public Graph(int size){
        this.size = size;

        parents = new int[size];
        for(int i = 0 ; i < size ; i++){
            parents[i] = i;
        }
    }

    public int findRoot(int x){
        if(x == parents[x])
            return x;
        return parents[x] = findRoot(parents[x]);
    }

    public void union(int a, int b){
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);

        if(!isGameOver){
            unionCnt++;

            if(aRoot == bRoot){
                isGameOver = true;
                return;
            }

            parents[aRoot] = bRoot;
        }
    }

    public void printUnionCnt(){
        if(isGameOver){
            System.out.println(unionCnt);
        }
        else{
            System.out.println(0);
        }
    }

}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(n);

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.union(a, b);
        }

        graph.printUnionCnt();


    }
}
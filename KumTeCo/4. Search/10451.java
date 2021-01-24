import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    int[] parents;

    public Graph(int size){
        this.size = size;
        parents = new int[size];
    }

    public void addEdge(int from, int to){
        parents[from] = to;
    }

    public int getCycles(){
        int cycleCnt = 0;
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i = 1 ; i < size ; i++){
            if(!visited[i])
                queue.add(i);

            while(!queue.isEmpty()){
                int polled = queue.poll();

                if(visited[polled])
                {
                    cycleCnt++;
                    continue;
                }

                visited[polled] = true;
                queue.add(parents[polled]);
            }
        }

        return cycleCnt;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            Graph graph = new Graph(N + 1);

            for(int i = 1 ; st.hasMoreTokens() ; i++){
                int x = Integer.parseInt(st.nextToken());
                graph.addEdge(i, x);
            }

            System.out.println(graph.getCycles());
        }


    }
}
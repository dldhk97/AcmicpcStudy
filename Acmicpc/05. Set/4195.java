import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    final static int size = 200000;
    int[] parents;
    HashMap<String, Integer> indexes = new HashMap<>();
    int[] cnts;

    public Graph(){
        parents = new int[size + 1];
        cnts = new int[size + 1];

        for(int i = 1 ; i <= size ; i++){
            parents[i] = i;
        }
    }

    public int getIndex(String name){
        if(indexes.containsKey(name))
            return indexes.get(name);

        int index = indexes.size() + 1;
        indexes.put(name, index);
        return index;
    }

    public int findParent(int x){
        if(parents[x] == x)
            return x;
        return parents[x] = findParent(parents[x]);
    }

    public int union(int a, int b){
        int aRoot = findParent(a);
        int bRoot = findParent(b);

        if(aRoot != bRoot){
            int master = bRoot;
            int slave = aRoot;

            if(cnts[aRoot] == 0 && cnts[bRoot] == 0){
                cnts[bRoot] = 2;
            }
            else if(cnts[aRoot] != 0 && cnts[bRoot] != 0){
                cnts[bRoot] += cnts[aRoot];
            }
            else if(cnts[aRoot] != 0){
                master = aRoot; slave = bRoot;
                cnts[aRoot]++;
                parents[bRoot] = aRoot;
            }
            else{
                cnts[bRoot]++;
            }

            parents[slave] = master;

            return cnts[master];
        }

        return cnts[aRoot];
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            int F = Integer.parseInt(br.readLine());

            Graph graph = new Graph();

            StringBuilder sb = new StringBuilder();

            for(int i = 1 ; i <= F ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String first = st.nextToken();
                String second = st.nextToken();

                int firstIdx = graph.getIndex(first);
                int secondIdx = graph.getIndex(second);

                int cnt = graph.union(firstIdx, secondIdx);
                sb.append(cnt);
                sb.append("\n");
            }
            System.out.print(sb.toString());
        }
    }
}
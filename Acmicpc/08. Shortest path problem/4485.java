import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Graph{
    int size;
    int[][] map;

    final int[] directionX = {0, 0, -1, 1};
    final int[] directionY = {-1, 1, 0, 0};

    public Graph(int size){
        this.size = size;

        map = new int[size][];

        for(int i = 0 ; i < size ; i++){
            map[i] = new int[size];
        }
    }

    public void addMapData(int x, int y, int weight){
        map[x][y] = weight;
    }

    public int dijkstra(int startX, int startY){
        int[][] distances = new int[size][];

        for(int a = 0 ; a < size ; a++){
            distances[a] = new int[size];
            for(int b = 0 ; b < size ; b++){
                distances[a][b] = Integer.MAX_VALUE;
            }
        }

        distances[startX][startY] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startX, startY, map[startX][startY]));

        while(!pq.isEmpty()){

            Node polled = pq.poll();

            for(int i = 0 ; i < 4 ; i++){
                int newX = polled.x + directionX[i];
                int newY = polled.y + directionY[i];

                if((0 <= newX && newX < size) && (0 <= newY && newY < size)){

                    int sum = distances[polled.x][polled.y] + map[newX][newY];
                    if(sum < distances[newX][newY]){
                        distances[newX][newY] = sum;
                        pq.add(new Node(newX, newY, map[newX][newY]));
                    }
                }
            }
        }

        int min = Math.min(distances[size - 1][size - 2], distances[size - 2][size - 1]);

        return min + map[0][0] + map[size - 1][size - 1];
    }
}

class Node implements Comparable<Node>{
    int x, y, weight;

    public Node(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node n) {
        return this.weight - n.weight;
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0)
                break;

            Graph graph = new Graph(N);

            for(int y = 0 ; y < N ; y++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int x = 0 ; x < N ; x++){
                    int value = Integer.parseInt(st.nextToken());
                    graph.addMapData(x, y, value);
                }
            }

            int result = graph.dijkstra(0, 0);
            sb.append("Problem " + cnt + ": " + result + "\n");
            cnt++;
        }
        System.out.print(sb.toString());

    }
}
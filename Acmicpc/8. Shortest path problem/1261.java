import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int INF = 1000000000;
    static int[] dX = {0, 0, -1, 1};
    static int[] dY = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[M][N];

        for(int y = 0 ; y < N ; y++){
            String line = br.readLine();
            for(int x = 0 ; x < M ; x++){
                int value = line.charAt(x) - '0';
                map[x][y] = value;
            }
        }

        int[][] distances = new int[M][N];
        for(int i = 0 ; i < M ; i++){
            Arrays.fill(distances[i], INF);
        }

        distances[0][0] = 0;        // startPoint

        boolean[][] visited = new boolean[M][N];

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) ->{
            return o1[2] - o2[2];
        });

        queue.add(new int[] {0, 0, 0});

        while(!queue.isEmpty()){
            int[] polled = queue.poll();
            int x = polled[0]; int y = polled[1];

            if(visited[x][y]) continue;
            visited[x][y] = true;

            for(int i = 0 ; i < 4 ; i++){
                int newX = x + dX[i];
                int newY = y + dY[i];

                if((0 <= newX && newX < M) && (0 <= newY && newY < N)){
                    int weight = map[newX][newY];

                    distances[newX][newY] = Math.min(distances[newX][newY], distances[x][y] + weight);
                    queue.add(new int[] {newX, newY, distances[newX][newY]});
                }
            }
        }

        System.out.println(distances[M - 1][N - 1]);
    }
}
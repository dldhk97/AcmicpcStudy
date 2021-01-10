import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void print(int M, int N, int[][] map){
        StringBuilder sb = new StringBuilder();
        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < M ; x++){
                sb.append(map[x][y]);
                sb.append("\t");
            }
            sb.append("\n");
        }
        sb.append("\n");
        System.out.print(sb.toString());
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        int maxHeight = Integer.MIN_VALUE;

        for(int y = 0 ; y < N ; y++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < N ; x++){
                int height = Integer.parseInt(st.nextToken());
                map[x][y] = height;

                maxHeight = Integer.max(height, maxHeight);
            }
        }

        Queue<int[]> queue;
        int[][] visited;

        int[] directionX = {0, 0, -1, 1};
        int[] directionY = {-1, 1, 0, 0};

        int maxSafeZoneCnt = 0;

        for(int h = 1 ; h <= maxHeight ; h++){
            queue = new ArrayDeque<>();
            visited = new int[N][N];

            int safeZoneCnt = 0;

            for(int y = 0 ; y < N ; y++){
                for(int x = 0 ; x < N ; x++){
                    if((visited[x][y] != 0) || (map[x][y] < h))
                        continue;

                    int[] point = {x, y};
                    queue.add(point);

                    while(!queue.isEmpty()){
                        int[] polledPoint = queue.poll();
                        int polledX = polledPoint[0]; int polledY = polledPoint[1];

                        if((visited[polledX][polledY] != 0) || (map[polledX][polledY] < h))
                            continue;

                        visited[polledX][polledY] = 1;

                        for(int i = 0 ; i < 4 ; i++){
                            int adjX = polledX + directionX[i];
                            int adjY = polledY + directionY[i];

                            if((adjX >= 0 && adjX <= N - 1) && (adjY >= 0 && adjY <= N - 1)){
                                int[] adjPoint = {adjX, adjY};
                                queue.add(adjPoint);
                            }
                        }
                    }
                    safeZoneCnt++;
                }
            }

//            print(N, N, visited);
            maxSafeZoneCnt = Integer.max(safeZoneCnt, maxSafeZoneCnt);
        }

        System.out.println(maxSafeZoneCnt);

    }
}
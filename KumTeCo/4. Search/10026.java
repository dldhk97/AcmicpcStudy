import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int N;
    static char[][] MAP;
    static int[] DIRECTION_X = {0, 0, -1, 1};
    static int[] DIRECTION_Y = {-1, 1, 0, 0};

    public static void print(int M, int N, int[][] arr){
        StringBuilder sb = new StringBuilder();
        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < M ; x++){
                sb.append(arr[x][y] + "\t");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void bfs(int[] point, Queue<int[]> queue, int[][] visited, boolean isAbnormal){
        int x = point[0]; int y = point[1];
        queue.add(point);

        while(!queue.isEmpty()){
            int[] polledPoint = queue.poll();
            int polledX = polledPoint[0]; int polledY = polledPoint[1];

            if(visited[polledX][polledY] == 1)
                continue;

            char polledColor = MAP[polledX][polledY];
            char currentColor = MAP[x][y];

            if(isAbnormal){
                if(polledColor == 'G')
                    polledColor = 'R';
                if(currentColor == 'G')
                    currentColor = 'R';
            }

            if(polledColor != currentColor)
                continue;

            visited[polledX][polledY] = 1;

            for(int i = 0 ; i < 4 ; i++){
                int adjX = polledX + DIRECTION_X[i];
                int adjY = polledY + DIRECTION_Y[i];

                if((adjX >= 0 && adjX <= N - 1) && (adjY >= 0 && adjY <= N - 1)){
                    int[] adjPoint = {adjX, adjY};
                    queue.add(adjPoint);
                }
            }
        }


    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        MAP = new char[N][N];

        for(int y = 0 ; y < N ; y++){
            String line = br.readLine();
            int x = 0;
            for(char c : line.toCharArray()){
                MAP[x][y] = c;
                x++;
            }
        }

        int[][] normalVisited = new int[N][N];
        Queue<int[]> normalQueue = new ArrayDeque<>();
        int[][] abnormalVisited = new int[N][N];
        Queue<int[]> abnormalQueue = new ArrayDeque<>();

        int normalRegionCnt = 0;
        int abnormalRegionCnt = 0;

        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < N ; x++){
                int[] point = {x, y};
                if(normalVisited[x][y] != 1){
                    bfs(point, normalQueue, normalVisited, false);
                    normalRegionCnt++;
//                    print(N, N, normalVisited);
                }
                if(abnormalVisited[x][y] != 1){
                    bfs(point, abnormalQueue, abnormalVisited, true);
                    abnormalRegionCnt++;
//                    print(N, N, abnormalVisited);
                }
            }
        }

        System.out.println(normalRegionCnt + " " + abnormalRegionCnt);

    }
}
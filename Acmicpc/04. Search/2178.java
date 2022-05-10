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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[M][N];
        for(int y = 0 ; y < N ; y++){
            String line = br.readLine();
            int x = 0;
            for(char c : line.toCharArray()){
                map[x++][y] = c - '0';
            }
        }

        int[][] visited = new int[M][N];
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] moved = new int[M][N];

        int[] directionX = {0, 0, -1, 1};
        int[] directionY = {-1, 1, 0, 0};

        int[] startPoint = {0, 0};
        queue.add(startPoint);

        while(!queue.isEmpty()){
            int[] polledPoint = queue.poll();
            int x = polledPoint[0];
            int y = polledPoint[1];

            if(visited[x][y] == 1 || map[x][y] == 0){
                continue;
            }

            visited[x][y] = 1;
            moved[x][y]++;

//            print(M, N, moved);

            if(x == M - 1 && y == N - 1){
                break;
            }

            for(int i = 0 ; i < 4 ; i++){
                int adjX = x + directionX[i];
                int adjY = y + directionY[i];

                if((adjX >= 0 && adjX <= M - 1) && (adjY >= 0 && adjY <= N - 1)){
                    if(visited[adjX][adjY] == 0 && map[adjX][adjY] == 1){
                        int[] adjPoint = {adjX, adjY};
                        queue.add(adjPoint);
                        if(moved[adjX][adjY] <= 0){
                            moved[adjX][adjY] = moved[x][y];
                        }
                        else{
                            if(moved[adjX][adjY] > moved[x][y]){
                                moved[adjX][adjY] = moved[x][y];
                            }
                        }
                    }
                }
            }
        }

        System.out.println(moved[M - 1][N - 1]);

    }
}
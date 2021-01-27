import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main{

    static int N, M;
    static int[][] map;
    static int[][] tempMap;
    static boolean[][] tempVisited;
    static int MAX_SAFE_ZONES = 0;

    static int[] directionX = {0, 0, -1, 1};
    static int[] directionY = {-1, 1, 0, 0};

    static void virusDFS(int x, int y){
        if(tempVisited[x][y])
            return;
        if(tempMap[x][y] == 1)
            return;

        tempMap[x][y] = 2;
        tempVisited[x][y] = true;

        for(int i = 0 ; i < 4 ; i++){
            int newX = x + directionX[i];
            int newY = y + directionY[i];

            if((0 <= newX && newX < M) && (0 <= newY && newY < N)){
                virusDFS(newX, newY);
            }
        }

    }

    static int countSafeZone(){
        int cnt = 0;
        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < M ; x++){
                if(tempMap[x][y] == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    static void printMap(int[][] map){
        StringBuilder sb = new StringBuilder();
        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < M ; x++){
                sb.append(map[x][y]);
                sb.append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    static int[][] copyMap(int[][] map){
        int[][] newMap = new int[M][N];
        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < M ; x++){
                newMap[x][y] = map[x][y];
            }
        }
        return newMap;
    }

    static void solve(int leftWallCnt){
        if(leftWallCnt <= 0){
            tempMap = copyMap(map);
            tempVisited = new boolean[M][N];

            for(int y = 0 ; y < N ; y++){
                for(int x = 0 ; x < M ; x++){
                    if(tempMap[x][y] == 2)
                        virusDFS(x, y);
                }
            }

            int safeZones = countSafeZone();
            if(MAX_SAFE_ZONES < safeZones){
                MAX_SAFE_ZONES = safeZones;
            }

            return;
        }

        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < M ; x++){
                if(map[x][y] != 0)
                    continue;

                map[x][y] = 1;
                solve(leftWallCnt - 1);
                map[x][y] = 0;
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for(int y = 0 ; y < N ; y++){
            st = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < M ; x++){
                int value = Integer.parseInt(st.nextToken());
                map[x][y] = value;
            }
        }

        solve(3);

        System.out.println(MAX_SAFE_ZONES);

    }

}
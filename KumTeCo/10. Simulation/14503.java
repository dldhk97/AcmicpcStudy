import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dX = {0, 0, -1, 1};
    static int[] dY = {-1, 1, 0, 0};

    static void print(int iX, int iY){
        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < M ; x++){
                String val = "○";
                if(x == iX && y == iY)
                    val = "▲";
                else if(map[x][y] == 1)
                    val = "■";
                else if(visited[x][y])
                    val = "※";

                System.out.print(val + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());
        int startDir = Integer.parseInt(st.nextToken());

        for(int y = 0 ; y < N ; y++){
            st = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < M ; x++){
                int value = Integer.parseInt(st.nextToken());
                map[x][y] = value;
            }
        }

        visited = new boolean[M][N];

        int x = startX;
        int y = startY;
        int dir = startDir;

        int cleanCount = 0;

        while(true){
            if(!visited[x][y]){
                visited[x][y] = true;
                cleanCount++;
            }

            // a
            int leftDir = rotateLeft(dir);
            int[] movePosition = getMovePosition(leftDir);
            int leftX = x + movePosition[0]; int leftY = y + movePosition[1];

            if(isAvailablePos(leftX, leftY)){
                if(map[leftX][leftY] == 0 && !visited[leftX][leftY]){
                    dir = leftDir;
                    x = leftX; y = leftY;
                    continue;
                }
            }

            // c, d
            if(isFourDirectionUnavailable(x, y)){
                int[] backwardPos = getBackwardPosition(dir);
                int newX = x + backwardPos[0]; int newY = y + backwardPos[1];

                if(!isAvailablePos(newX, newY))
                    break;

                if(map[newX][newY] != 0)
                    break;

                x = newX; y = newY;

                continue;
            }

            // b
            dir = leftDir;
        }

        System.out.println(cleanCount);
    }

    static boolean isAvailablePos(int x, int y){
        return (0 <= x && x < M) && (0 <= y && y < N);
    }

    static int rotateLeft(int dir){
        if(dir == 0)
            return 3;
        return dir - 1;
    }

    static int[] getMovePosition(int dir){
        if(dir == 0)
            return new int[] {0, -1};
        if(dir == 1)
            return new int[] {1, 0};
        if(dir == 2)
            return new int[] {0, 1};

        return new int[] {-1, 0};
    }

    static int[] getBackwardPosition(int dir){
        if(dir == 0)
            return new int[] {0, 1};
        if(dir == 1)
            return new int[] {-1, 0};
        if(dir == 2)
            return new int[] {0, -1};

        return new int[] {1, 0};
    }

    static boolean isFourDirectionUnavailable(int x, int y){
        int unavailableCnt = 0;
        for(int i = 0 ; i < 4 ; i++){
            int newX = x + dX[i]; int newY = y + dY[i];

            if(!isAvailablePos(newX, newY)){
                unavailableCnt++;
            }
            else if(map[newX][newY] == 1 || visited[newX][newY]){
                unavailableCnt++;
            }
        }

        return unavailableCnt == 4;
    }

    static boolean isBackwardAvailable(int x, int y, int dir){
        int[] backwardPos = getBackwardPosition(dir);
        int newX = x + backwardPos[0]; int newY = y + backwardPos[1];

        if(!isAvailablePos(newX, newY))
            return false;

        return map[newX][newY] == 0;
    }

}
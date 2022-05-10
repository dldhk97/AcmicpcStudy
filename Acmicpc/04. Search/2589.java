import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int H, W;
    static int[][] map;
    static int MAXIMUM_COST = 0;

    static int[][] tempCost;
    static boolean[][] tempVisited;

    static int[] directionX = {0, 0, -1, 1};
    static int[] directionY = {-1, 1, 0, 0};

    static void bfs(int[] startPoint){

        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(startPoint);

        while(!queue.isEmpty()){
            int[] polled = queue.poll();
            int x = polled[0];
            int y = polled[1];

            tempVisited[x][y] = true;

            for(int i = 0 ; i < 4 ; i++){
                int newX = x + directionX[i];
                int newY = y + directionY[i];

                if((0 <= newX && newX < W) && (0 <= newY && newY < H)){
                    if(map[newX][newY] == 0)
                        continue;

                    if(tempVisited[newX][newY])
                        continue;

                    tempVisited[newX][newY] = true;

                    int nextPointCost = tempCost[newX][newY];
                    if(nextPointCost > 0)
                        tempCost[newX][newY] = Math.min(nextPointCost, tempCost[x][y] + 1);
                    else
                        tempCost[newX][newY] = tempCost[x][y] + 1;

                    if(MAXIMUM_COST < tempCost[newX][newY])
                        MAXIMUM_COST = tempCost[newX][newY];



                    int[] newPoint = {newX, newY};

                    queue.add(newPoint);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[W][H];

        for(int y = 0 ; y < H ; y++){
            char[] chars = br.readLine().toCharArray();
            for(int x = 0 ; x < W ; x++){
                int value = chars[x] == 'L' ? 1 : 0;
                map[x][y] = value;
            }
        }

        for(int y = 0 ; y < H ; y++){
            for(int x = 0 ; x < W ; x++){
                if(map[x][y] == 0)
                    continue;

                tempCost = new int[W][H];
                tempVisited = new boolean[W][H];

                int[] startPoint = {x, y};

                bfs(startPoint);
            }
        }

        System.out.println(MAXIMUM_COST);
    }
}
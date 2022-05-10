import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static char[][] MAP = new char[6][12];
    static Boolean CHAIN_SENSOR = false;

    static int[] dX = {0, 0, -1, 1};
    static int[] dY = {-1, 1, 0, 0};

    static void gravity(int x){
        for(int y = 10 ; y >= 0 ; y--){
            if(MAP[x][y] == '.') continue;
            if(MAP[x][y + 1] != '.') continue;
            MAP[x][y + 1] = MAP[x][y];
            MAP[x][y] = '.';
            y = 11;

        }
    }

    static void bfs(int[] startPoint){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(startPoint);

        boolean[][] visited = new boolean[6][12];
        List<int[]> deletePoints = new ArrayList<>();

        char color = MAP[startPoint[0]][startPoint[1]];

        while(!queue.isEmpty()){
            int[] polled = queue.poll();
            int x = polled[0]; int y = polled[1];

            if(MAP[x][y] != color) continue;
            if(visited[x][y]) continue;

            visited[x][y] = true;
            deletePoints.add(new int[] {x, y});

            for(int i = 0 ; i < 4 ;i++){
                int newX = x + dX[i];
                int newY = y + dY[i];

                if((0 <= newX && newX < 6) && (0 <= newY && newY < 12)){
                    if(MAP[newX][newY] != color) continue;

                    queue.add(new int[]{newX, newY});
                }
            }
        }

        if(deletePoints.size() >= 4){
            CHAIN_SENSOR = true;

            for(int[] point : deletePoints){
                MAP[point[0]][point[1]] = '.';
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int y = 0 ; y < 12 ; y++){
            String str = br.readLine();
            for(int x = 0 ; x < 6 ; x++){
                MAP[x][y] = str.charAt(x);
            }
        }

        int numberOfChains = 0;

        while(true){

            for (int y = 11; y >= 0; y--) {
                for (int x = 5; x >= 0; x--) {
                    if (MAP[x][y] == '.') continue;

                    bfs(new int[] {x, y});
                }
            }

            for(int x = 0 ; x < 6 ; x++){
                gravity(x);
            }

            if (CHAIN_SENSOR){
                numberOfChains++;
                CHAIN_SENSOR = false;
            }
            else{
                break;
            }

        }

        System.out.println(numberOfChains);
    }
}
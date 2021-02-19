import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class TomatoBox {
    int M, N, H;

    int[][][] map;

    int countOfTomatoes = 0;
    int countOfRotten = 0;

    public TomatoBox(int M, int N, int H){
        this.M = M;
        this.N = N;
        this.H = H;

        map = new int[M][N][H];
    }

    public void addTomato(int x, int y, int z, int value){
        map[x][y][z] = value;

        if(value == 0)
            countOfTomatoes++;
        else if(value == 1)
            countOfRotten++;
    }

    boolean[][][] visited;
    int[] directionX = {0, 0, -1, 1, 0, 0};
    int[] directionY = {0, 0, 0, 0, -1, 1};
    int[] directionZ = {-1, 1, 0, 0, 0, 0};

    void bfs(Queue<int[]> queue){
        while(!queue.isEmpty()){
            int[] p = queue.poll();
            int x = p[0]; int y = p[1]; int z = p[2];

            if(visited[x][y][z] || map[x][y][z] == -1) continue;

            visited[x][y][z] = true;

            for(int i = 0 ; i < 6 ; i++){
                int newX = x + directionX[i];
                int newY = y + directionY[i];
                int newZ = z + directionZ[i];

                if((0 <= newX && newX < M) && (0 <= newY && newY < N) && (0 <= newZ && newZ < H)){
                    if(map[newX][newY][newZ] == -1) continue;

                    if(map[newX][newY][newZ] == 0){
                        map[newX][newY][newZ] = map[x][y][z] + 1;
                    }
                    else{
                        map[newX][newY][newZ] = Math.min(map[newX][newY][newZ], map[x][y][z] + 1);
                    }

                    int[] newPoint = {newX, newY, newZ};
                    queue.add(newPoint);
                }
            }
        }
    }

    public int solve(){
        if(countOfTomatoes == 0){
            return 0;   // 이미 모든 토마토가 익음
        }

        visited = new boolean[M][N][H];

        Queue<int[]> queue = new ArrayDeque<>();

        for(int z = 0 ; z < H ; z++){
            for(int y = 0 ; y < N ; y++){
                for(int x = 0 ; x < M ; x++){
                    if(visited[x][y][z] || map[x][y][z] != 1) continue;

                    int[] point = {x, y, z};
                    queue.add(point);
                }
            }
        }

        bfs(queue);

        // 익지 않은 토마토 탐색 & 경과일 계산
        int days = 0;
        for(int z = 0 ; z < H ; z++){
            for(int y = 0 ; y < N ; y++){
                for(int x = 0 ; x < M ; x++){
                    if(map[x][y][z] == 0) return -1;        // 익지 않은 토마토가 존재
                    days = Math.max(days, map[x][y][z]);
                }
            }
        }

        return days - 1;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        TomatoBox box = new TomatoBox(M, N, H);

        for(int h = 0 ; h < H ; h++){
            for(int y = 0 ; y < N ; y++){
                st = new StringTokenizer(br.readLine());

                for(int x = 0 ; x < M ; x++){
                    int value = Integer.parseInt(st.nextToken());
                    box.addTomato(x, y, h, value);
                }
            }
        }

        int result = box.solve();
        System.out.println(result);
    }
}
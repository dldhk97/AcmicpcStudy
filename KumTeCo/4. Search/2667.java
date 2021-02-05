import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;



public class Main{

    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] directionX = {0, 0, -1, 1};
    static int[] directionY = {-1, 1, 0, 0};

    static List<Integer> cnts = new ArrayList<>();

    static void dfs(int x, int y){
        if(visited[x][y] || map[x][y] == 0)
            return;

        visited[x][y] = true;
        int cnt = cnts.get(cnts.size() - 1);
        cnts.set(cnts.size() - 1, cnt + 1);

        for(int i = 0 ; i < 4 ; i++){
            int newX = x + directionX[i];
            int newY = y + directionY[i];

            if((0 <= newX && newX < N) && 0 <= newY && newY < N){
                dfs(newX, newY);
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for(int y = 0 ; y < N ; y++){
            char[] line = br.readLine().toCharArray();
            for(int x = 0 ; x < N ; x++){
                int value = line[x] - '0';

                map[x][y] = value;
            }
        }

        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < N ; x++){
                if(visited[x][y] || map[x][y] == 0)
                    continue;

                cnts.add(0);
                dfs(x, y);
            }
        }

        cnts.sort(Integer::compareTo);

        StringBuilder sb = new StringBuilder();

        sb.append(cnts.size());
        sb.append("\n");

        for(int i : cnts){
            sb.append(i);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
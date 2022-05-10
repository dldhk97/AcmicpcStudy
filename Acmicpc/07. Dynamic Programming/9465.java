import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int[][] map;
    static int[][] cost;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());

            map = new int[2][n + 1];
            cost = new int[2][n + 1];

            for(int i = 0 ; i < 2 ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 1 ; j <= n ; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            cost[0][1] = map[0][1];
            cost[1][1] = map[1][1];

            for(int i = 2 ; i <= n ; i++){
                cost[0][i] = Math.max(cost[1][i - 2], cost[1][i - 1]) + map[0][i];
                cost[1][i] = Math.max(cost[0][i - 2], cost[0][i - 1]) + map[1][i];
            }

            int maxValue = Math.max(cost[0][n], cost[1][n]);

            sb.append(maxValue);
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
}
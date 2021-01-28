import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int[][][] dp = new int[51][51][51];

    static int solve(int a, int b, int c){
        if(a <= 0 || b <= 0 || c <= 0)
            return 1;

        if(dp[a][b][c] != 0)
            return dp[a][b][c];

        if(a > 20 || b > 20 || c > 20){
            return dp[a][b][c] = solve(20, 20, 20);
        }

        if(a < b && b < c)
            return dp[a][b][c] = solve(a, b, c - 1) + solve(a, b - 1, c - 1) - solve(a, b - 1, c);

        return dp[a][b][c] = solve(a - 1, b, c) + solve(a - 1, b - 1, c) + solve(a - 1, b, c - 1) - solve(a - 1, b - 1, c - 1);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1)
                break;

            sb.append("w(" + a + ", " + b + ", " + c + ") = ");
            int result = solve(a, b, c);
            sb.append(result);
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11052
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] dp = new int[N + 1];

    for(int i = 1 ; i <= N ; i++) {
      dp[i] = Integer.parseInt(st.nextToken());
    }

    for(int i = 2 ; i <= N ; i++) {
      for(int j = 1 ; j < i ; j++) {
        int x = dp[j] + dp[i - j];
        dp[i] = Math.max(dp[i], x);
      }
    }

    System.out.println(dp[N]);
  }
}

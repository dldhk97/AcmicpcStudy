import java.io.*;
import java.util.*;

public class Main {
  private static boolean[][] dp = new boolean[31][40001];
  private static int N;
  private static int[] beads;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    beads = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      beads[i] = Integer.parseInt(st.nextToken());
    }

    canMeasure(0, 0);

    StringBuilder result = new StringBuilder();

    int M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      int weight = Integer.parseInt(st.nextToken());

      if(dp[N][weight])
        result.append("Y ");
      else
        result.append("N ");
    }

    System.out.println(result.toString().trim());
  }

  private static void canMeasure(int weightIdx, int weight){
    if(dp[weightIdx][weight])
      return;

    dp[weightIdx][weight] = true;

    if(weightIdx >= N)
      return;

    canMeasure(weightIdx + 1, weight + beads[weightIdx]);
    canMeasure(weightIdx + 1, weight);
    canMeasure(weightIdx + 1, Math.abs(weight - beads[weightIdx]));
  }
}
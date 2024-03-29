import java.io.*;
import java.util.*;

public class Solution {

  // Complete the maxSubsetSum function below.
  static int maxSubsetSum(int[] arr) {
    int[] dp = new int[arr.length];

    dp[0] = Math.max(0, arr[0]);
    dp[1] = Math.max(dp[0], arr[1]);

    for(int i = 2 ; i < arr.length ; i++){
      dp[i] = Math.max(0, arr[i]);
      dp[i] = Math.max(dp[i], arr[i] + dp[i - 2]);
      dp[i] = Math.max(dp[i], dp[i - 1]);
    }

    return dp[arr.length - 1];
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[] arr = new int[n];

    String[] arrItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int arrItem = Integer.parseInt(arrItems[i]);
      arr[i] = arrItem;
    }

    int res = maxSubsetSum(arr);

    bufferedWriter.write(String.valueOf(res));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}

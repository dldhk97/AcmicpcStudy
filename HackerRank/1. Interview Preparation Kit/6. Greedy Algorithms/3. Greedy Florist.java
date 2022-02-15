import java.io.*;
import java.util.*;

public class Solution {

  // Complete the getMinimumCost function below.
  static int getMinimumCost(int k, int[] c) {
    int leftFlowers = c.length;
    int[] buyCount = new int[k];

    Arrays.sort(c);

    int totalPrice = 0;
    for(int i = 0 ; leftFlowers > 0 ; i = (i + 1) % k){
      totalPrice += (buyCount[i] + 1) * c[leftFlowers - 1];

      buyCount[i]++;
      leftFlowers--;
    }

    return totalPrice;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] nk = scanner.nextLine().split(" ");

    int n = Integer.parseInt(nk[0]);

    int k = Integer.parseInt(nk[1]);

    int[] c = new int[n];

    String[] cItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int cItem = Integer.parseInt(cItems[i]);
      c[i] = cItem;
    }

    int minimumCost = getMinimumCost(k, c);

    bufferedWriter.write(String.valueOf(minimumCost));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}

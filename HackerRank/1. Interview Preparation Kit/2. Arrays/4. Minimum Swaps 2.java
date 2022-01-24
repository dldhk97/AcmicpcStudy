import java.io.*;
import java.util.*;

public class Solution {

  // Complete the minimumSwaps function below.
  static int minimumSwaps(int[] arr) {
    int count = 0;

    for(int i = 0 ; i < arr.length - 1 ; i++){
      if(arr[i] == i + 1)
        continue;

      if(arr[i] != i + 1){
        int temp = arr[i];
        arr[i] = arr[temp - 1];
        arr[temp - 1] = temp;
        i--;

        count++;
      }
    }

    return count;
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

    int res = minimumSwaps(arr);

    bufferedWriter.write(String.valueOf(res));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}

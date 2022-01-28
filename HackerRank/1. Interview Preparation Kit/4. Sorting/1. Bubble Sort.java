import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'countSwaps' function below.
   *
   * The function accepts INTEGER_ARRAY a as parameter.
   */

  public static void countSwaps(List<Integer> a) {
    int n = a.size();
    int swapCount = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - 1; j++) {
        // Swap adjacent elements if they are in decreasing order
        if (a.get(j) > a.get(j + 1)) {
          swap(a, j, j + 1);
          swapCount++;
        }
      }
    }

    System.out.println("Array is sorted in " + swapCount + " swaps.");
    System.out.println("First Element: " + a.get(0));
    System.out.println("Last Element: " + a.get(a.size() - 1));
  }

  private static void swap(List<Integer> a, int index1, int index2){
    int temp = a.get(index1);
    a.set(index1, a.get(index2));
    a.set(index2, temp);
  }

}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    Result.countSwaps(a);

    bufferedReader.close();
  }
}

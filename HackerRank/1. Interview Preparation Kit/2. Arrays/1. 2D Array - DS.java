import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'hourglassSum' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts 2D_INTEGER_ARRAY arr as parameter.
   */

  public static int hourglassSum(List<List<Integer>> arr) {
    int max = Integer.MIN_VALUE;

    for(int y = 0 ; y < arr.size() - 2 ; y++){
      for(int x = 0 ; x < arr.get(0).size() - 2 ; x++){
        int sum = 0;

        for(int i = 0 ; i < 3 ; i++)
          sum += arr.get(y).get(x + i);

        sum += arr.get(y + 1).get(x + 1);

        for(int i = 0 ; i < 3 ; i++)
          sum += arr.get(y + 2).get(x + i);

        max = Math.max(max, sum);
      }
    }

    return max;
  }

}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    List<List<Integer>> arr = new ArrayList<>();

    IntStream.range(0, 6).forEach(i -> {
      try {
        arr.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    int result = Result.hourglassSum(arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

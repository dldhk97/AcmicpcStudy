import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'maxMin' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER k
   *  2. INTEGER_ARRAY arr
   */

  public static int maxMin(int k, List<Integer> arr) {
    arr.sort(Integer::compareTo);

    int result = Integer.MAX_VALUE;
    for(int i = 0 ; i < arr.size() - k + 1 ; i++)
      result = Math.min(result, arr.get(i + k - 1) - arr.get(i));

    return result;
  }
}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    int k = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
          try {
            return bufferedReader.readLine().replaceAll("\\s+$", "");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        })
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(toList());

    int result = Result.maxMin(k, arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

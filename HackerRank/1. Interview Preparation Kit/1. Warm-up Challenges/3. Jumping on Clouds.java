import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'jumpingOnClouds' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER_ARRAY c as parameter.
   */

  public static int jumpingOnClouds(List<Integer> c) {
    int[] costs = new int[c.size()];
    Arrays.fill(costs, Integer.MAX_VALUE);

    costs[0] = 0;

    for(int i = 1 ; i < c.size() ; i++){
      if(c.get(i) == 1)
        continue;
      if(i == 1)
        costs[1] = 1;
      else
        costs[i] = Math.min(costs[i - 1], costs[i - 2]) + 1;
    }
    return costs[c.size() - 1];
  }

}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    int result = Result.jumpingOnClouds(c);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

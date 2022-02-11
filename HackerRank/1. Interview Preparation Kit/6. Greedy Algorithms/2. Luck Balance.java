import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'luckBalance' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER k
   *  2. 2D_INTEGER_ARRAY contests
   */

  public static int luckBalance(int k, List<List<Integer>> contests) {
    int result = 0;

    PriorityQueue<List<Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.get(0) - o1.get(0));

    for(List<Integer> contest : contests){
      if(contest.get(1) == 0){
        result+= contest.get(0);
        continue;
      }

      pq.add(contest);
    }

    while(!pq.isEmpty() && k > 0){
      List<Integer> contest = pq.poll();
      result += contest.get(0);
      k--;
    }

    while(!pq.isEmpty())
      result -= pq.poll().get(0);

    return result;
  }

}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int k = Integer.parseInt(firstMultipleInput[1]);

    List<List<Integer>> contests = new ArrayList<>();

    IntStream.range(0, n).forEach(i -> {
      try {
        contests.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    int result = Result.luckBalance(k, contests);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'whatFlavors' function below.
   *
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY cost
   *  2. INTEGER money
   */

  public static void whatFlavors(List<Integer> cost, int money) {
    List<int[]> costs = new ArrayList<>();
    for(int i = 0 ; i < cost.size() ; i++)
      costs.add(i, new int[]{i, cost.get(i)});

    costs.sort(Comparator.comparingInt(o -> o[1]));

    for(int i = 0 ; i < costs.size() ; i++){
      int leftIdx = i;
      int rightIdx = costs.size() - 1;

      while(leftIdx <= rightIdx){
        int midIdx = (leftIdx + rightIdx) / 2;
        int sum = costs.get(i)[1] + costs.get(midIdx)[1];

        if(sum == money){
          if(i == midIdx){
            leftIdx++;
            continue;
          }

          int first = Math.min(costs.get(i)[0] + 1, costs.get(midIdx)[0] + 1);
          int second = Math.max(costs.get(i)[0] + 1, costs.get(midIdx)[0] + 1);
          System.out.println(first + " " + second);
          return;
        }

        if(sum < money)
          leftIdx = midIdx + 1;
        else
          rightIdx = midIdx - 1;
      }
    }
  }
}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int t = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, t).forEach(tItr -> {
      try {
        int money = Integer.parseInt(bufferedReader.readLine().trim());

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.whatFlavors(cost, money);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
  }
}

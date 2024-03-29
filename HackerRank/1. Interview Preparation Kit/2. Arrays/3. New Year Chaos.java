import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'minimumBribes' function below.
   *
   * The function accepts INTEGER_ARRAY q as parameter.
   */

  public static void minimumBribes(List<Integer> q) {
    int count = 0;
    for(int i = 0 ; i < q.size() ; i++){
      int current = q.get(i);
      int index = i + 1;

      if(current - 2 > index){
        System.out.println("Too chaotic");
        return;
      }

      for(int j = Math.max(0, q.get(i) - 2) ; j < i ; j++){
        if(q.get(j) > q.get(i))
          count++;
      }
    }

    System.out.println(count);
  }


}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int t = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, t).forEach(tItr -> {
      try {
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.minimumBribes(q);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
  }
}

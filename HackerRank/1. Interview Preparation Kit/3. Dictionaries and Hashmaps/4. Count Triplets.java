import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class Solution {

  // Complete the countTriplets function below.
  static long countTriplets(List<Long> arr, long r) {
    Map<Long, Long> middle = new HashMap();
    Map<Long, Long> high = new HashMap();

    long result = 0;
    for(long key : arr){
      if(high.containsKey(key))
        result += high.get(key);

      if(middle.containsKey(key))
        high.put(key * r, high.getOrDefault(key * r, 0L) + middle.get(key));

      middle.put(key * r, middle.getOrDefault(key * r, 0L) + 1);
    }

    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(nr[0]);

    long r = Long.parseLong(nr[1]);

    List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Long::parseLong)
        .collect(toList());

    long ans = countTriplets(arr, r);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

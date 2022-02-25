import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'pairs' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER k
   *  2. INTEGER_ARRAY arr
   */

  public static int pairs(int k, List<Integer> arr) {
    arr.sort(Integer::compareTo);

    int result = 0;

    for(int i = 0 ; i < arr.size() - 1 ; i++){
      int left = i + 1;
      int right = arr.size() - 1;

      while(left <= right){
        int mid = (left + right) / 2;
        int diff = arr.get(mid) - arr.get(i);
        if(diff == k){
          result++;
          break;
        }

        if(diff < k){
          left = mid + 1;
        }
        else{
          right = mid - 1;
        }
      }
    }

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

    List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    int result = Result.pairs(k, arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

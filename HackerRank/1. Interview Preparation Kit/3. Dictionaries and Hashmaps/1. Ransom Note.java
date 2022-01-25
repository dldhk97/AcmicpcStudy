import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'checkMagazine' function below.
   *
   * The function accepts following parameters:
   *  1. STRING_ARRAY magazine
   *  2. STRING_ARRAY note
   */

  public static void checkMagazine(List<String> magazine, List<String> note) {
    // Write your code here
    Map<String, Integer> map = new HashMap<>();
    for(String s : magazine)
      map.put(s, map.getOrDefault(s, 0) + 1);

    for(String s : note){
      if(!map.containsKey(s) || map.get(s) <= 0){
        System.out.println("No");
        return;
      }
      map.put(s, map.get(s) - 1);
    }
    System.out.println("Yes");
  }
}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int m = Integer.parseInt(firstMultipleInput[0]);

    int n = Integer.parseInt(firstMultipleInput[1]);

    List<String> magazine = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .collect(toList());

    List<String> note = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .collect(toList());

    Result.checkMagazine(magazine, note);

    bufferedReader.close();
  }
}

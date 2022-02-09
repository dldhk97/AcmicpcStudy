import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Result {

  /*
   * Complete the 'isValid' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts STRING s as parameter.
   */

  public static String isValid(String s) {
    Map<Character, Integer> frequencyMap = new HashMap<>();

    for(char c : s.toCharArray())
      frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);

    Map<Integer, Integer> freqValueMap = new HashMap<>();
    for(int i : frequencyMap.values()){
      freqValueMap.put(i, freqValueMap.getOrDefault(i, 0) + 1);
      if(freqValueMap.size() > 2)
        return "NO";
    }

    if(freqValueMap.size() <= 1)
      return "YES";

    List<Integer> keys = new ArrayList<>(freqValueMap.keySet());
    int big = Math.max(keys.get(0), keys.get(1));
    int small = Math.min(keys.get(0), keys.get(1));

    if(freqValueMap.get(big) <= 1 && big - small <= 1)
      return "YES";

    if(freqValueMap.get(small) <= 1)
      return "YES";

    return "NO";
  }
}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String s = bufferedReader.readLine();

    String result = Result.isValid(s);

    bufferedWriter.write(result);
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

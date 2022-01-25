import java.io.*;
import java.util.*;
import java.util.stream.*;

class Result {

  /*
   * Complete the 'sherlockAndAnagrams' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts STRING s as parameter.
   */

  public static int sherlockAndAnagrams(String s) {
    Map<String, Integer> map = new HashMap<>();

    for(int i = 0 ; i < s.length() ; i++){
      List<Character> list = new ArrayList<>();

      for(int j = i ; j < s.length() ; j++){
        list.add(s.charAt(j));
        list.sort(Comparator.comparingInt(o -> o));

        StringBuilder sb = new StringBuilder();
        for(char c : list)
          sb.append(c);

        map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
      }
    }

    int result = 0;
    for(int i : map.values()){
      if(i > 1)
        result += (i * (i - 1)) / 2;
    }

    return result;
  }
}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, q).forEach(qItr -> {
      try {
        String s = bufferedReader.readLine();

        int result = Result.sherlockAndAnagrams(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
    bufferedWriter.close();
  }
}

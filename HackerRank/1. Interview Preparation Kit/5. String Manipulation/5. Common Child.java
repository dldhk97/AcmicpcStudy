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
   * Complete the 'commonChild' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. STRING s1
   *  2. STRING s2
   */

  public static int commonChild(String s1, String s2) {
    int length = s1.length();
    int[][] LCS = new int[length + 1][length + 1];

    int result = 0;

    for(int y = 0 ; y < length ; y++){
      for(int x = 0 ; x < length ; x++){
        LCS[y + 1][x + 1] = Math.max(LCS[y][x + 1], LCS[y + 1][x]);

        if(s1.charAt(x) == s2.charAt(y)){
          LCS[y + 1][x + 1] = LCS[y][x] + 1;
          result = Math.max(result, LCS[y + 1][x + 1]);
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

    String s1 = bufferedReader.readLine();

    String s2 = bufferedReader.readLine();

    int result = Result.commonChild(s1, s2);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

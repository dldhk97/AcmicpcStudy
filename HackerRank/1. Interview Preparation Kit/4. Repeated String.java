import java.io.*;

class Result {

  /*
   * Complete the 'repeatedString' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts following parameters:
   *  1. STRING s
   *  2. LONG_INTEGER n
   */

  public static long repeatedString(String s, long n) {
    // Write your code here
    long countOfA = 0;
    for(char c : s.toCharArray()){
      if(c == 'a')
        countOfA++;
    }

    long multi = n / s.length();
    long left = n - multi * s.length();

    long countOfLeftA = 0;
    for(int i = 0 ; i < left ; i++){
      if(s.charAt(i) == 'a')
        countOfLeftA++;
    }
    return countOfA * multi + countOfLeftA;
  }

}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String s = bufferedReader.readLine();

    long n = Long.parseLong(bufferedReader.readLine().trim());

    long result = Result.repeatedString(s, n);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

import java.io.*;
import java.util.Stack;
import java.util.stream.*;

class Result {

  /*
   * Complete the 'alternatingCharacters' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts STRING s as parameter.
   */

  public static int alternatingCharacters(String s) {
    Stack<Character> stack = new Stack<>();

    for(char c : s.toCharArray())
      stack.push(c);

    char lastChar = stack.pop();

    int result = 0;
    while(!stack.isEmpty()){
      char popped = stack.pop();

      if(lastChar == popped)
        result++;
      else
        lastChar = popped;
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

        int result = Result.alternatingCharacters(s);

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

import java.io.*;
import java.util.*;

public class Solution {

  // Complete the substrCount function below.
  static long substrCount(int n, String s) {
    long result = 0;

    List<int[]> boundaries = new ArrayList<>();
    char lastChar = s.charAt(0);
    int startIdx = 0;
    int sameCharCnt = 0;

    for(int i = 1 ; i < n ; i++){
      if(lastChar == s.charAt(i)){
        sameCharCnt++;
        continue;
      }

      boundaries.add(new int[]{startIdx, startIdx + sameCharCnt, s.charAt(i - 1) - 'a'});
      startIdx = i;
      sameCharCnt = 0;
      lastChar = s.charAt(i);

      if(i == n - 1){
        boundaries.add(new int[]{startIdx, startIdx + sameCharCnt, s.charAt(n - 1) - 'a'});
      }
    }

    if(sameCharCnt > 0)
      boundaries.add(new int[]{startIdx, startIdx + sameCharCnt, s.charAt(n - 1) - 'a'});

    for(int i = 0 ; i < boundaries.size() ; i++){
      int[] boundary = boundaries.get(i);
      int length = boundary[1] - boundary[0] + 1;

      if(0 < i && i < boundaries.size() - 1){
        if(length == 1){
          int[] head = boundaries.get(i - 1);
          int[] tail = boundaries.get(i + 1);

          result += 1;
          if(head[2] == tail[2]){
            int headLength = head[1] - head[0] + 1;
            int tailLength = tail[1] - tail[0] + 1;
            int wingLength = Math.min(headLength, tailLength);

            result += wingLength;
          }
          continue;
        }
      }

      for(int j = 1 ; j <= length ; j++)
        result += j;
    }

    return result;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    String s = scanner.nextLine();

    long result = substrCount(n, s);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}

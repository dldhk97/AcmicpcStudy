import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String stringA = br.readLine();
    String stringB = br.readLine();

    int result = solve(stringA, stringB);
    System.out.println(result);
  }

  private static int solve(String stringA, String stringB){
    int[][] LCS = new int[stringA.length() + 1][stringB.length() + 1];

    createLcsTable(LCS, stringA, stringB);

    return getLcs(LCS);
  }

  private static void createLcsTable(int[][] LCS, String stringA, String stringB){
    int aLength = stringA.length();
    int bLength = stringB.length();

    for(int i = 1 ; i <= aLength ; i++){
      for(int j = 1 ; j <= bLength ; j++){
        if(stringA.charAt(i - 1) == stringB.charAt(j - 1)){
          LCS[i][j] = LCS[i - 1][j - 1] + 1;
        }
      }
    }
  }


  private static int getLcs(int[][] LCS){
    int max = 0;
    for(int i = 1 ; i < LCS.length ; i++){
      for(int j = 1 ; j < LCS[0].length ; j++){
        max = Math.max(max, LCS[i][j]);
      }
    }
    return max;
  }
}
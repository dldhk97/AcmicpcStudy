import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String stringA = br.readLine();
    String stringB = br.readLine();
    String stringC = br.readLine();

    int result = solve(stringA, stringB, stringC);

    System.out.println(result);
  }

  private static int solve(String stringA, String stringB, String stringC){
    int[][][] LCS = new int[stringA.length() + 1][stringB.length() + 1][stringC.length() + 1];
    createLcsTable(LCS, stringA, stringB, stringC);
    return getLcsLength(LCS);
  }

  private static void createLcsTable(int[][][] LCS, String stringA, String stringB, String stringC){
    int aLength = stringA.length();
    int bLength = stringB.length();
    int cLength = stringC.length();

    for(int i = 1 ; i <= aLength ; i++){
      for(int j = 1 ; j <= bLength ; j++){
        for(int k = 1 ; k <= cLength ; k++){
          if(stringA.charAt(i - 1) == stringB.charAt(j - 1) &&
              stringA.charAt(i - 1) == stringC.charAt(k - 1)) {
            LCS[i][j][k] = LCS[i - 1][j - 1][k - 1] + 1;
          }
          else{
            int max = Math.max(LCS[i - 1][j][k], LCS[i][j - 1][k]);
            LCS[i][j][k] = Math.max(max, LCS[i][j][k - 1]);
          }
        }
      }
    }
  }

  private static int getLcsLength(int[][][] LCS){
    int max = 0;

    for(int z = 1 ; z < LCS.length ; z++){
      for(int y = 1 ; y < LCS[0].length ; y++){
        for(int x = 1 ; x < LCS[0][0].length ; x++){
          max = Math.max(max, LCS[z][y][x]);
        }
      }
    }
    return max;
  }
}
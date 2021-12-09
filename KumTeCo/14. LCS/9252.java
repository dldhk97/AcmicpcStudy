import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String stringA = br.readLine();
    String stringB = br.readLine();

    String result = solve(stringA, stringB);

    System.out.println(result.length());
    if(result.length() > 0)
      System.out.println(result);
  }

  private static String solve(String stringA, String stringB){
    int[][] LCS = new int[stringA.length() + 1][stringB.length() + 1];
    createLcsTable(LCS, stringA, stringB);
    return getLcs(LCS, stringA, stringB);
  }

  private static void createLcsTable(int[][] LCS, String stringA, String stringB){
    int aLength = stringA.length();
    int bLength = stringB.length();

    for(int i = 1 ; i <= aLength ; i++){
      for(int j = 1 ; j <= bLength ; j++){
        if(stringA.charAt(i - 1) == stringB.charAt(j - 1))
          LCS[i][j] = LCS[i - 1][j - 1] + 1;
        else
          LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
      }
    }
  }

  private static String getLcs(int[][] LCS, String stringA, String stringB){
    Stack<Character> stack = new Stack<>();

    int y = LCS.length - 1;
    int x = LCS[0].length - 1;

    while(LCS[y][x] != 0){
      if(LCS[y][x] == LCS[y - 1][x]){
        y--;
      }
      else if(LCS[y][x] == LCS[y][x - 1]) {
        x--;
      }
      else{
        stack.push(stringA.charAt(y - 1));
        x--;
        y--;
      }
    }

    StringBuilder sb = new StringBuilder();
    while(!stack.isEmpty()){
      sb.append(stack.pop());
    }
    return sb.toString();
  }
}
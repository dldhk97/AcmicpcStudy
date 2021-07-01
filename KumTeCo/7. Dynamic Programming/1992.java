import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{

  static int N;

  static int[][] MAP;

  static String dp(int startX, int startY, int length){
    if(length <= 1)
      return String.valueOf(MAP[startY][startX]);

    int halfLength = length / 2;

    String partA = dp(startX, startY, halfLength);
    String partB = dp(startX + halfLength, startY, halfLength);
    String partC = dp(startX, startY + halfLength, halfLength);
    String partD = dp(startX + halfLength, startY + halfLength, halfLength);

    String assemble = partA + partB + partC + partD;

    if(assemble.contains("0") && assemble.contains("1")){
      return "(" + assemble + ")";
    }
    else if(assemble.contains("0")){
      return "0";
    }
    else{
      return "1";
    }
  }

  static public void printMap(){
    for(int i = 0 ; i < N ; i++){
      for(int j = 0 ; j < N ; j++){
        System.out.print(MAP[i][j] + ", ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    MAP = new int[N][N];

    for(int i = 0 ; i < N ; i++){
      String s = br.readLine();

      for(int j = 0 ; j < N ; j++){
        MAP[i][j] = s.charAt(j) - '0';
      }
    }

    String result = dp(0, 0, N);
    System.out.println(result);
  }

}
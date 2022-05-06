import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2527
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    for(int i = 0 ; i < 4 ; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[][] squareData = new int[2][4];
      for(int j = 0 ; j < 4 ; j++)
        squareData[0][j] = Integer.parseInt(st.nextToken());

      for(int j = 0 ; j < 4 ; j++)
        squareData[1][j] = Integer.parseInt(st.nextToken());

      System.out.println(solve(squareData));
    }
  }

  private static char solve(int[][] squareData){
    int xl = Math.max(squareData[0][0], squareData[1][0]);
    int xr = Math.min(squareData[0][2], squareData[1][2]);
    int yl = Math.max(squareData[0][1], squareData[1][1]);
    int yh = Math.min(squareData[0][3], squareData[1][3]);

    int xDiff = xr - xl;
    int yDiff = yh - yl;

    if(xDiff < 0 || yDiff < 0)
      return 'd';
    if(xDiff > 0 && yDiff > 0)
      return 'a';
    if(xDiff == 0 && yDiff == 0)
      return 'c';
    return 'b';
  }


}
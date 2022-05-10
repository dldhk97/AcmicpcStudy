import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2578
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Map<Integer, int[]> numberPos = new HashMap<>();

    // read
    for(int y = 0 ; y < 5 ; y++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < 5 ; x++){
        numberPos.put(Integer.parseInt(st.nextToken()), new int[]{x, y});
      }
    }

    boolean[][] board = new boolean[5][5];
    int bingoCnt = 0;
    int calledCnt = 0;

    for(int y = 0 ; y < 5 ; y++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      for(int x = 0 ; x < 5 ; x++){
        int calledNumber = Integer.parseInt(st.nextToken());
        int[] pos = numberPos.get(calledNumber);
        board[pos[1]][pos[0]] = true;
        calledCnt++;

        bingoCnt += checkNewBingo(board, pos[0], pos[1]);

        if(bingoCnt >= 3){
          System.out.println(calledCnt);
          return;
        }
      }
    }
  }

  private static final boolean[][] bingoLines = new boolean[4][5];    // direction(0=x, 1=y, 2=diagonal, 3=rDiagonal), unique position
  private static int checkNewBingo(boolean[][] board, int posX, int posY){
    int bingoCnt = 0;
    if(findHorizontalLine(board, posY)){
      if(!bingoLines[0][posY]){
        bingoLines[0][posY] = true;
        bingoCnt++;
      }
    }

    if(findVerticalLine(board, posX)){
      if(!bingoLines[1][posX]){
        bingoLines[1][posX] = true;
        bingoCnt++;
      }
    }

    // each diagonal line has only 1 unique pos
    if(findNormalDiagonalLine(board, posX, posY)){
      if(!bingoLines[2][0]){
        bingoLines[2][0] = true;
        bingoCnt++;
      }
    }

    if(findReverseDiagonalLine(board, posX, posY)){
      if(!bingoLines[3][0]){
        bingoLines[3][0] = true;
        bingoCnt++;
      }
    }

    return bingoCnt;
  }

  private static boolean findHorizontalLine(boolean[][] board, int posY){
    for(int x = 0 ; x < 5 ; x++){
      if(!board[posY][x])
        return false;
    }
    return true;
  }

  private static boolean findVerticalLine(boolean[][] board, int posX){
    for(int y = 0 ; y < 5 ; y++){
      if(!board[y][posX])
        return false;
    }
    return true;
  }

  private static boolean findNormalDiagonalLine(boolean[][] board, int posX, int posY){
    if(posX != posY)
      return false;

    for(int i = 0 ; i < 5 ; i++){
      if(!board[i][i])
        return false;
    }
    return true;
  }

  private static boolean findReverseDiagonalLine(boolean[][] board, int posX, int posY){
    if(4 - posX != posY)
      return false;

    for(int i = 0 ; i < 5 ; i++){
      if(!board[i][4 - i])
        return false;
    }
    return true;
  }
}
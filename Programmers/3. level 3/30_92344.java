import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/92344
class Solution {
  public int solution(int[][] board, int[][] skill) {
    long[][] accBoard = new long[board.length][board[0].length];

    for(int[] s : skill){
      boolean isAttack = s[0] == 1;
      int y1 = s[1]; int x1 = s[2];
      int y2 = s[3]; int x2 = s[4];
      int degree = s[5];

      int value = isAttack ? -degree : degree;

      accBoard[y1][x1] += value;
      if(y2 + 1 < accBoard.length)
        accBoard[y2 + 1][x1] += (-value);
      if(x2 + 1 < accBoard[0].length)
        accBoard[y1][x2 + 1] += (-value);
      if(y2 + 1 < accBoard.length && x2 + 1 < accBoard[0].length)
        accBoard[y2 + 1][x2 + 1] += value;
      int a = 3;
    }

    // vertical accumulation
    for(int x = 0 ; x < accBoard[0].length; x++){
      for(int y = 0 ; y < accBoard.length - 1 ; y++){
        accBoard[y + 1][x] += accBoard[y][x];
      }
    }

    // horizontal accumulation
    for(int y = 0 ; y < accBoard.length ; y++){
      for(int x = 0 ; x < accBoard[0].length - 1; x++){
        accBoard[y][x + 1] += accBoard[y][x];
      }
    }

    int alive = 0;
    for(int y = 0 ; y < accBoard.length ; y++){
      for(int x = 0 ; x < accBoard[0].length; x++){
        if(board[y][x] + accBoard[y][x] > 0) {
          alive++;
        }
      }
    }

    return alive;
  }
}
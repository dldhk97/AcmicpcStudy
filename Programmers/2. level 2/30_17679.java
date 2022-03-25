import java.util.ArrayDeque;
import java.util.Queue;

// https://programmers.co.kr/learn/courses/30/lessons/17679
class Solution {

  public int solution(int m, int n, String[] board) {
    char[][] b = convertIntoArr(board);

    int sum = 0;
    int deleted;

    do{
      deleted = scanAndDelete(b);
      sum += deleted;

      gravity(b);
    }while(deleted > 0);

    return sum;
  }

  private char[][] convertIntoArr(String[] board){
    char[][] arr = new char[board.length][board[0].length()];

    for(int y = 0 ; y < board.length ; y++)
      for(int x = 0 ; x < board[0].length() ; x++)
        arr[y][x] = board[y].charAt(x);

    return arr;
  }

  int[] dX = {0, 1, 1, 0};
  int[] dY = {0, 0, 1, 1};

  private int scanAndDelete(char[][] board){
    int deletedCnt = 0;

    boolean[][] deleted = new boolean[board.length][board[0].length];

    Queue<int[]> deleteTarget = new ArrayDeque<>();

    for(int y = 0 ; y < board.length - 1 ; y++){
      for(int x = 0 ; x < board[0].length - 1 ; x++){
        if(board[y][x] == '-')
          continue;
        if(canBeDeleted(x, y, board)){
          for(int i = 0 ; i < 4 ; i++){
            int nextX = x + dX[i];
            int nextY = y + dY[i];

            if(deleted[nextY][nextX])
              continue;
            deleted[nextY][nextX] = true;
            deleteTarget.add(new int[]{nextX, nextY});
          }
        }
      }
    }

    while(!deleteTarget.isEmpty()){
      int[] polled = deleteTarget.poll();
      int x = polled[0]; int y = polled[1];
      deletedCnt++;
      board[y][x] = '-';
    }

    return deletedCnt;
  }

  private boolean canBeDeleted(int x, int y, char[][] board){
    char c = board[y][x];
    return (c == board[y + 1][x]) && (c == board[y][x + 1]) && (c == board[y + 1][x + 1]);
  }

  private void gravity(char[][] board){
    for(int x = 0 ; x < board[0].length ; x++){
      boolean changed = false;
      for(int y = board.length - 2 ; y >= 0; y--){
        if(board[y + 1][x] == '-' && board[y][x] != '-'){
          board[y + 1][x] = board[y][x];
          board[y][x] = '-';
          changed = true;
        }
      }
      if(changed)
        x--;
    }
  }
}
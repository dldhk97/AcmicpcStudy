// https://programmers.co.kr/learn/courses/30/lessons/92345
class Solution {
  private static int[] dX = {0, 0, -1, 1};
  private static int[] dY = {-1, 1, 0, 0};

  private static final int WIN = 1;
  private static final int LOSE = 0;

  public int solution(int[][] board, int[] aloc, int[] bloc) {
    return dfs(board, aloc[1], aloc[0], bloc[1], bloc[0])[1];
  }

  private int[] dfs(int[][] board, int x1, int y1, int x2, int y2){
    if(checkLose(board, x1, y1)){
      return new int[]{LOSE, 0};
    }

    boolean canWin = false;
    int max = 0;
    int min = 1000000000;

    for(int i = 0 ; i < 4 ; i++){
      int nextX = x1 + dX[i];
      int nextY = y1 + dY[i];

      if(!checkBoundary(board, nextX, nextY) || board[nextY][nextX] == 0)
        continue;

      board[y1][x1] = 0;
      int[] res = dfs(board, x2, y2, nextX, nextY);
      board[y1][x1] = 1;

      if(res[0] == LOSE){
        canWin = true;
        min = Math.min(min, res[1]);
      }
      else if(!canWin){
        max = Math.max(max, res[1]);
      }
    }

    if(canWin)
      return new int[]{WIN, min + 1};
    else
      return new int[]{LOSE, max + 1};
  }

  private boolean checkLose(int[][] board, int x, int y){
    if(checkImmovable(board, x, y))
      return true;

    return board[y][x] == 0;
  }

  private boolean checkImmovable(int[][] board, int x, int y){
    for(int i = 0 ; i < 4 ; i++){
      int nextX = x + dX[i];
      int nextY = y + dY[i];

      if(!checkBoundary(board, nextX, nextY))
        continue;

      if(board[nextY][nextX] == 0)
        continue;

      return false;
    }

    return true;
  }

  private boolean checkBoundary(int[][] board, int x, int y){
    return x >= 0 && x < board[0].length && y >= 0 && y < board.length;
  }
}
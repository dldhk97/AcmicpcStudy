import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/60063
class Solution {

  final int HORIZONTAL = 0;
  final int VERTICAL = 1;

  public int solution(int[][] board) {
    return bfs(board);
  }

  int[] dX = {0, 1, 0, -1};
  int[] dY = {-1, 0, 1, 0};

  private int bfs(int[][] board){
    boolean[][][] visited = new boolean[board.length][board[0].length][4];

    PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o1[3] - o2[3]));
    queue.add(new int[]{0, 0, HORIZONTAL, 0});

    int xBound = board[0].length;
    int yBound = board.length;

    while(!queue.isEmpty()){
      int[] polled = queue.poll();
      int x = polled[0]; int y = polled[1];
      int rotation = polled[2];
      int cost = polled[3];

      if(visited[y][x][rotation])
        continue;
      visited[y][x][rotation] = true;

      if(isArrived(x, y, rotation, board))
        return cost;

      for(int i = 0 ; i < dX.length ; i++){
        int nX = x + dX[i];
        int nY = y + dY[i];
        if(!isValidPos(nX, nY, xBound, yBound, rotation, board))
          continue;
        if(visited[nY][nX][rotation])
          continue;
        queue.add(new int[]{nX, nY, rotation, cost + 1});
      }

      if(rotation == HORIZONTAL){
        // leftwing clockwise
        if(isValidPos(x, y, xBound, yBound, VERTICAL, board) && _wallCheck(x + 1, y + 1, xBound, yBound, board))
          queue.add(new int[]{x, y, VERTICAL, cost + 1});

        // leftwing reverse clockwise
        if(isValidPos(x, y - 1, xBound, yBound, VERTICAL, board) && _wallCheck(x + 1, y - 1, xBound, yBound, board))
          queue.add(new int[]{x, y - 1, VERTICAL, cost + 1});

        // rightwing clockwise
        if(isValidPos(x + 1, y - 1, xBound, yBound, VERTICAL, board) && _wallCheck(x, y - 1, xBound, yBound, board))
          queue.add(new int[]{x + 1, y - 1, VERTICAL, cost + 1});

        // rightwing reverse clockwise
        if(isValidPos(x + 1, y, xBound, yBound, VERTICAL, board) && _wallCheck(x, y + 1, xBound, yBound, board))
          queue.add(new int[]{x + 1, y, VERTICAL, cost + 1});
      }
      else{
        // upperwing clockwise
        if(isValidPos(x - 1, y, xBound, yBound, HORIZONTAL, board) && _wallCheck(x - 1, y + 1, xBound, yBound, board))
          queue.add(new int[]{x - 1, y, HORIZONTAL, cost + 1});

        // upperwing reverse clockwise
        if(isValidPos(x, y, xBound, yBound, HORIZONTAL, board) && _wallCheck(x + 1, y + 1, xBound, yBound, board))
          queue.add(new int[]{x, y, HORIZONTAL, cost + 1});

        // lowerwing clockwise
        if(isValidPos(x, y + 1, xBound, yBound, HORIZONTAL, board) && _wallCheck(x + 1, y, xBound, yBound, board))
          queue.add(new int[]{x, y + 1, HORIZONTAL, cost + 1});

        // lowerwing reverse clockwise
        if(isValidPos(x - 1, y + 1, xBound, yBound, HORIZONTAL, board) && _wallCheck(x - 1, y, xBound, yBound, board))
          queue.add(new int[]{x - 1, y + 1, HORIZONTAL, cost + 1});
      }
    }

    return 0;
  }

  private boolean isValidPos(int x, int y, int xBound, int yBound, int rotation, int[][] board){
    if(_rangeCheck(x, y, xBound, yBound) && board[y][x] == 0){
      if(rotation == HORIZONTAL) {
        return _wallCheck(x + 1, y, xBound, yBound, board);
      }
      return _wallCheck(x, y + 1, xBound, yBound, board);
    }
    return false;
  }

  private boolean _rangeCheck(int x, int y, int xBound, int yBound){
    return 0 <= x && x < xBound && 0 <= y && y < yBound;
  }

  private boolean _wallCheck(int x, int y, int xBound, int yBound, int[][] board){
    return _rangeCheck(x, y, xBound, yBound) && board[y][x] == 0;
  }

  private boolean isArrived(int x, int y, int rotation, int[][] board){
    int endX = board[0].length - 1;
    int endY = board.length - 1;

    if(x == endX && y == endY)
      return true;

    if(rotation == HORIZONTAL)
      return x + 1 == endX && y == endY;
    else
      return x == endX && y + 1 == endY;
  }
}
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  private static int MAX = 999999999;
  public int solution(int[][] board) {
    int answer = bfs(board, 1);
    answer = Math.min(answer, bfs(board, 2));
    return answer;
  }

  private static int[] dX = {0, 1, 0, -1};
  private static int[] dY = {-1, 0, 1, 0};

  private int bfs(int[][] board, int startDirection){
    Queue<int[]> queue = new ArrayDeque<>();
    queue.add(new int[]{0, 0, startDirection, 0});

    int[][] dist = new int[board.length][board[0].length];
    for(int[] arr : dist)
      Arrays.fill(arr, MAX);
    dist[0][0] = 0;

    while(!queue.isEmpty()){
      int[] polled = queue.poll();
      int x = polled[0]; int y = polled[1]; int d = polled[2]; int cost = polled[3];

      for(int i = 0 ; i < 4 ; i++){
        int nX = x + dX[i];
        int nY = y + dY[i];

        if(!isInBoundary(nX, nY, board[0].length, board.length) || board[nY][nX] == 1)
          continue;

        int c = i == d ? 100 : 500 + 100;

        if(dist[nY][nX] < cost + c)
          continue;
        dist[nY][nX] = cost + c;

        queue.add(new int[]{nX, nY, i, cost + c});
      }
    }

    return dist[dist.length - 1][dist[0].length - 1];
  }

  private boolean isInBoundary(int x, int y, int W, int H){
    return 0 <= x && x < W && 0 <= y && y < H;
  }
}
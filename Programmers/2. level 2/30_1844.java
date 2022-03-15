import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://programmers.co.kr/learn/courses/30/lessons/1844
class Solution {
  static final int[] dX = {0, 1, 0, -1};
  static final int[] dY = {-1, 0, 1, 0};
  static final int DIST_MAX = 999999999;

  public int solution(int[][] maps) {
    Queue<int[]> stack = new LinkedList<>();
    stack.add(new int[]{0, 0});

    boolean[][] visited = new boolean[maps.length][maps[0].length];

    int[][] dist = new int[maps.length][maps[0].length];
    for(int[] d : dist)
      Arrays.fill(d, DIST_MAX);
    dist[0][0] = 1;

    while(!stack.isEmpty()){
      int[] polled = stack.poll();
      int x = polled[0]; int y = polled[1];

      if(visited[y][x])
        continue;
      visited[y][x] = true;

      for(int i = 0 ; i < 4 ; i++){
        int nX = x + dX[i]; int nY = y + dY[i];
        if(!isInRange(nX, nY, maps[0].length, maps.length))
          continue;
        if(visited[nY][nX] || maps[nY][nX] == 0)
          continue;
        dist[nY][nX] = Math.min(dist[nY][nX], dist[y][x] + 1);
        stack.add(new int[]{nX, nY});
      }
    }

    int endPoint = dist[maps.length - 1][maps[0].length - 1];
    if(endPoint == DIST_MAX)
      return -1;
    return endPoint;
  }

  private boolean isInRange(int x, int y, int width, int height){
    return (0 <= x && x < width) && (0 <= y && y < height);
  }
}
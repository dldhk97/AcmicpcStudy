import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/42898
class Solution {
  public int solution(int m, int n, int[][] puddles) {
    int[][] routes = new int[n][m];
    for(int[] r : routes)
      Arrays.fill(r, Integer.MAX_VALUE);

    for(int[] puddle : puddles){
      int x = puddle[0] - 1; int y = puddle[1] - 1;
      routes[y][x] = -1;
    }

    routes[0][0] = 1;

    for(int y = 0 ; y  < n ; y++){
      for(int x = 0 ; x < m ; x++){
        if(x == 0 && y == 0 || routes[y][x] == -1)
          continue;

        int left = 0;
        if(isInBoundary(m , n, x - 1, y)){
          if(routes[y][x - 1] != -1)
            left = routes[y][x - 1];
        }

        int up = 0;
        if(isInBoundary(m , n, x, y - 1)){
          if(routes[y - 1][x] != -1)
            up = routes[y - 1][x];
        }

        int sum = (left + up) % 1000000007;
        routes[y][x] = Math.min(routes[y][x], sum);
      }
    }

    return routes[n - 1][m - 1];
  }

  private boolean isInBoundary(int m, int n, int x, int y){
    return 0 <= x && x < m && 0 <= y && y < n;
  }
}
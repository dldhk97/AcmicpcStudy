import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/1829
class Solution {
  int[] directionX = {0, 0, -1, 1};
  int[] directionY = {-1, 1, 0, 0};

  boolean[][] visited;
  int height, width;
  int numberOfArea = 0;
  int maxSizeOfOneArea = 0;

  public int[] solution(int m, int n, int[][] picture) {
    height = m;
    width = n;
    visited = new boolean[height][width];

    for(int y = 0 ; y < height ; y++){
      for(int x = 0 ; x < width ; x++){
        if(picture[y][x] == 0 || visited[y][x])
          continue;
        dfs(x, y, picture);
      }
    }

    return new int[]{numberOfArea, maxSizeOfOneArea};
  }


  private void dfs(int startX, int startY, int[][] picture){
    Stack<int[]> stack = new Stack<>();

    stack.push(new int[]{startX, startY});

    int area = 0;
    int color = picture[startY][startX];
    while(!stack.isEmpty()){
      int[] popped = stack.pop();
      int x = popped[0]; int y = popped[1];

      if(visited[y][x])
        continue;
      visited[y][x] = true;
      area++;

      for(int i = 0 ; i < 4 ; i++){
        int nextX = x + directionX[i];
        int nextY = y + directionY[i];

        if(0 > nextX || nextX >= width)
          continue;

        if(0 > nextY || nextY >= height)
          continue;

        if(visited[nextY][nextX])
          continue;

        if(color != picture[nextY][nextX])
          continue;

        stack.push(new int[]{nextX, nextY});
      }
    }

    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, area);
    numberOfArea++;
  }
}
import java.util.ArrayList;
import java.util.List;

// https://programmers.co.kr/learn/courses/30/lessons/86052
class Solution {
  static int[] dX = {0, 1, 0, -1};
  static int[] dY = {1, 0, -1, 0};
  static boolean[][][] visited;

  public int[] solution(String[] grid) {
    char[][] charGrid = convertGridIntoCharArr(grid);
    visited = new boolean[charGrid.length][charGrid[0].length][4];

    List<Integer> list = new ArrayList<>();
    for(int y = 0 ; y < charGrid.length ; y++){
      for(int x = 0 ; x < charGrid[0].length ; x++){
        for(int i = 0 ; i < 4 ; i++){
          if(!visited[y][x][i])
            list.add(shootLight(charGrid, x, y, i));
        }
      }
    }

    return list.stream().sorted().mapToInt(Integer::valueOf).toArray();
  }

  private int shootLight(char[][] grid, int x, int y, int dir){
    int cycleLength = 0;
    while(!visited[y][x][dir]){
      visited[y][x][dir] = true;

      if(grid[y][x] == 'L')
        dir = dir == 0 ? 3 : dir - 1;
      else if(grid[y][x] == 'R')
        dir = dir == 3 ? 0 : dir + 1;

      x = (x + dX[dir] + grid[0].length) % grid[0].length;
      y = (y + dY[dir] + grid.length) % grid.length;

      cycleLength++;
    }
    return cycleLength;
  }

  private char[][] convertGridIntoCharArr(String[] grid){
    char[][] charGrid = new char[grid.length][grid[0].length()];

    for(int y = 0 ; y < grid.length ; y++)
      for(int x = 0 ; x < grid[0].length() ; x++)
        charGrid[y][x] = grid[y].charAt(x);

    return charGrid;
  }
}
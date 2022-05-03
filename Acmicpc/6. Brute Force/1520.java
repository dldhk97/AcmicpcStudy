import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1520
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    int[][] map = new int[M][N];
    int[][] ways = new int[map.length][map[0].length];
    for(int y = 0 ; y < M ; y++){
      st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < N ; x++){
        map[y][x] = Integer.parseInt(st.nextToken());
        ways[y][x] = -1;
      }
    }

    int result = dfs(map, 0, 0, ways);
    System.out.println(result);
  }

  private static int[] dX = {0, 1, 0, -1};
  private static int[] dY = {-1, 0, 1, 0};
  private static int dfs(int[][] map, int x, int y, int[][] ways){
    if(y == map.length - 1 && x == map[0].length - 1)
      return 1;
    if(ways[y][x] != -1)
      return ways[y][x];

    ways[y][x] = 0;

    for(int i = 0 ; i < dX.length ; i++){
      int nX = x + dX[i];
      int nY = y + dY[i];
      if(0 <= nX && nX < map[0].length && 0 <= nY && nY < map.length) {
        if(map[nY][nX] >= map[y][x])
          continue;
        ways[y][x] += dfs(map, nX, nY, ways);
      }
    }

    return ways[y][x];
  }


}
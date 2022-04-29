import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14719
public class Main {
  private static final int WALL = 1;
  private static final int WATER = 2;

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int H = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[][] map = new int[H][W];
    List<int[]> walls = new ArrayList<>();

    for(int x = 0 ; x < W ; x++) {
      int height = Integer.parseInt(st.nextToken());
      walls.add(new int[]{x, height});

      for(int y = 0 ; y < height; y++)
        map[H - 1 - y][x] = WALL;
    }

    walls.sort((o1, o2) -> o2[1] - o1[1]);

    int result = rain(walls, map);

    System.out.println(result);
  }

  private static int rain(List<int[]> walls, int[][] map){
    int filled = 0;

    for(int[] wall : walls){
      int x = wall[0]; int height = map.length - wall[1];
      filled += bfs(map, x - 1, height);
      filled += bfs(map, x + 1, height);
    }

    return filled;
  }

  static int[] dX = {0, 1, 0, -1};
  static int[] dY = {-1, 0, 1, 0};
  private static int bfs(int[][] map, int startX, int startY){
    if(!isInBoundary(startX, startY, map[0].length, map.length) || map[startY][startX] == WATER || map[startY][startX] == WALL)
      return 0;

    Queue<int[]> queue = new ArrayDeque<>();
    queue.add(new int[]{startX, startY});

    boolean[][] visited = new boolean[map.length][map[0].length];
    Queue<int[]> fillQueue = new ArrayDeque<>();

    while(!queue.isEmpty()){
      int[] polled = queue.poll();
      int x = polled[0]; int y = polled[1];

      if(visited[y][x])
        continue;
      visited[y][x] = true;
      fillQueue.add(new int[]{x, y});

      if(x - 1 < 0 || x + 1 >= map[0].length)
        return 0;

      for(int i = 0 ; i < 4 ; i++){
        int nX = x + dX[i];
        int nY = y + dY[i];

        if(!isInBoundary(nX, nY, map[0].length, map.length) || nY < startY)
          continue;
        if(map[nY][nX] == WALL || map[nY][nX] == WATER)
          continue;
        queue.add(new int[]{nX, nY});
      }
    }

    int fillCnt = 0;
    while(!fillQueue.isEmpty()){
      int[] polled = fillQueue.poll();
      map[polled[1]][polled[0]] = WATER;
      fillCnt++;
    }

    return fillCnt;
  }

  private static boolean isInBoundary(int x, int y, int xBound, int yBound){
    return 0 <= x && x < xBound && 0 <= y && y < yBound;
  }


}
import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/16234
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][N];
    for(int y = 0 ; y < N ; y++){
      st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < N ; x++){
        map[y][x] = Integer.parseInt(st.nextToken());
      }
    }

    int count = 0;
    while(isPopulationMoved(map, L, R)){
      count++;
    }

    System.out.println(count);
  }

  private static boolean isPopulationMoved(int[][] map, int L, int R){
    boolean[][] visited = new boolean[map.length][map[0].length];

    boolean isMoved = false;

    for(int y = 0 ; y < map.length ; y++){
      for (int x = 0; x < map[y].length; x++) {
        if(visited[y][x])
          continue;

        if(isUnionExists(map, L, R, x, y, visited))
          isMoved = true;
      }
    }

    return isMoved;
  }

  private static final int[] dX = {0, 1, 0, -1};
  private static final int[] dY = {-1, 0, 1, 0};

  private static boolean isUnionExists(int[][] map, int L, int R, int startX, int startY, boolean[][] visited){
    Queue<int[]> queue = new ArrayDeque<>();
    queue.add(new int[]{startX, startY});

    Queue<int[]> union = new ArrayDeque<>();
    int unionSum = 0;

    while(!queue.isEmpty()){
      int[] polled = queue.poll();
      int x = polled[0]; int y = polled[1];

      if(visited[y][x])
        continue;
      visited[y][x] = true;

      union.add(polled);
      unionSum += map[y][x];

      for(int i = 0 ; i < dX.length ; i++){
        int nX = x + dX[i];
        int nY = y + dY[i];

        if(!isInBoundary(nX, nY, map[0].length, map.length) || visited[nY][nX])
          continue;

        int diff = Math.abs(map[y][x] - map[nY][nX]);
        if(L <= diff && diff <= R){
          queue.add(new int[]{nX, nY});
        }
      }
    }

    if(union.size() <= 1)
      return false;

    mergeUnion(map, union, unionSum);

    return true;
  }

  private static void mergeUnion(int[][] map, Queue<int[]> union, int unionSum){
    int avg = unionSum / union.size();

    while(!union.isEmpty()){
      int[] polled = union.poll();
      int x = polled[0]; int y = polled[1];
      map[y][x] = avg;
    }
  }

  private static boolean isInBoundary(int x, int y, int xBound, int yBound){
    return 0 <= x && x < xBound && 0 <= y && y < yBound;
  }

}
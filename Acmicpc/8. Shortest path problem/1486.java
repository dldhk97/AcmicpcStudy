import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int T = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][M];
    for(int y = 0 ; y < N ; y++){
      String line = br.readLine();

      int x = 0;
      for(char c : line.toCharArray())
        map[y][x++] = convertCharIntoInt(c);
    }

    System.out.println(solve(N, M, T, D, map));
  }

  private static int solve(int N, int M, int T, int D, int[][] map){
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
    for(int y = 0 ; y < N ; y++){
      for(int x = 0 ; x < M ; x++){
        pq.add(new int[]{map[y][x], x, y});
      }
    }

    while(!pq.isEmpty()){
      int[] polled = pq.poll();
      int height = polled[0]; int x = polled[1]; int y = polled[2];

      int visitCost = calcVisitCost(T, D, map, 0, 0, 0, x, y);
      if(visitCost == CANT_VISIT)
        continue;

      if(calcVisitCost(T, D, map, x, y, visitCost, 0, 0) == CANT_VISIT)
        continue;
      return height;
    }

    return map[0][0];
  }

  static int[] dX = {0, 1, 0, -1};
  static int[] dY = {-1, 0, 1, 0};
  static int MAX_COST = 999999999;
  static int CANT_VISIT = -999999999;

  private static int calcVisitCost(int T, int D, int[][] map, int startX, int startY, int startCost, int targetX, int targetY){
    PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
    queue.add(new int[]{startCost, startX, startY});

    boolean[][] visited = new boolean[map.length][map[0].length];

    int[][] costs = new int[map.length][map[0].length];
    for(int[] c : costs)
      Arrays.fill(c, MAX_COST);
    costs[startY][startX] = startCost;

    while(!queue.isEmpty()){
      int[] polled = queue.poll();
      int x = polled[1]; int y = polled[2];

      if(visited[y][x])
        continue;
      visited[y][x] = true;
      if(x == targetX && y == targetY)
        return costs[y][x];

      for(int i = 0 ; i < 4 ; i++){
        int nextX = x + dX[i];
        int nextY = y + dY[i];

        if(!isInBoundary(nextX, nextY, map[0].length, map.length) || visited[nextY][nextX])
          continue;

        int heightDiff = Math.abs(map[y][x] - map[nextY][nextX]);
        if(heightDiff > T)
          continue;

        int cost = calcCost(map, x, y, nextX, nextY) + costs[y][x];
        if(cost > D)
          continue;

        costs[nextY][nextX] = Math.min(costs[nextY][nextX], cost);
        queue.add(new int[]{costs[nextY][nextX], nextX, nextY});
      }
    }
    return CANT_VISIT;
  }

  private static boolean isInBoundary(int x, int y, int width, int height){
    return (0 <= x && x < width && 0 <= y && y < height);
  }

  private static int calcCost(int[][] map, int x, int y, int nextX, int nextY){
    int height = map[y][x];
    int nextHeight = map[nextY][nextX];
    if(height >= nextHeight)
      return 1;
    return (int)Math.pow(nextHeight - height, 2);
  }

  private static int convertCharIntoInt(char c){
    if(Character.isUpperCase(c))
      return c - 'A';
    return c - 'A' - 6;
  }
}

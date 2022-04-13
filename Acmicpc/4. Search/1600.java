import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1600
public class Main {

  private static int[][] MAP;
  private static boolean[][][] visited;
  private static int W, H;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int K = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    W = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    MAP = new int[H][W];
    visited = new boolean[H][W][K + 1];

    for(int y = 0 ; y < H ; y++){
      st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < W ; x++){
        MAP[y][x] = Integer.parseInt(st.nextToken());
      }
    }

    int result = bfs(K);
    System.out.println(result);
  }

  private static final int[] directMoveX = {0, 1, 0, -1};
  private static final int[] directMoveY = {-1, 0, 1, 0};

  private static final int[] horseMoveX = {1, 2, 2, 1, -1, -2, -2, -1};
  private static final int[] horseMoveY = {-2, -1, 1, 2, 2, 1, -1, -2};

  private static int bfs(int k){
    Queue<int[]> queue = new ArrayDeque<>();
    queue.add(new int[]{0, 0, k, 0});

    while(!queue.isEmpty()){
      int[] polled = queue.poll();
      int x = polled[0];
      int y = polled[1];
      int leftHorseMove = polled[2];
      int cost = polled[3];

      if(x == W - 1 && y == H - 1)
        return cost;

      if(visited[y][x][leftHorseMove])
        continue;
      visited[y][x][leftHorseMove] = true;

      for(int i = 0 ; i < 4 ; i++){
        int nX = x + directMoveX[i];
        int nY = y + directMoveY[i];

        if(isInBoundary(nX, nY) && !visited[nY][nX][leftHorseMove] && MAP[nY][nX] == 0)
          queue.add(new int[]{nX, nY, leftHorseMove, cost + 1});
      }

      if(leftHorseMove <= 0)
        continue;

      for(int i = 0 ; i < 8 ; i++){
        int nX = x + horseMoveX[i];
        int nY = y + horseMoveY[i];

        if(isInBoundary(nX, nY) && !visited[nY][nX][leftHorseMove - 1] && MAP[nY][nX] == 0)
          queue.add(new int[]{nX, nY, leftHorseMove - 1, cost + 1});
      }
    }

    return -1;
  }

  private static boolean isInBoundary(int x, int y){
    return 0 <= x && x < MAP[0].length && 0 <= y && y < MAP.length;
  }
}

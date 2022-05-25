import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/12100
public class Main {

  static final int[] dX = {0, 1, 0, -1};
  static final int[] dY = {-1, 0, 1, 0};
  static final int MOVE = 5;
  static int MAX = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[][] map = new int[N][N];

    for(int y = 0 ; y < N ; y++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < N ; x++){
        map[y][x] = Integer.parseInt(st.nextToken());
        MAX = Math.max(MAX, map[y][x]);
      }
    }

    backtracking(map, new int[MOVE], 0);

    System.out.println(MAX);
  }

  private static void backtracking(int[][] map, int[] track, int depth){
    if(depth >= track.length)
      return;

    for(int i = 1 ; i <= dX.length;  i++){
      if(track[depth] != 0)
        continue;
      track[depth] = i;
      int[][] newMap = copyMap(map);
      simulate(newMap, i - 1);
      backtracking(newMap, track, depth + 1);
      track[depth] = 0;
    }
  }

  private static void simulate(int[][] map, int direction){
    int[] axisOption = getAxisOptionByDirection(direction, map[0].length, map.length);
    int xAxisStart = axisOption[0];
    int yAxisStart = axisOption[1];
    int xAxisDir = axisOption[2];
    int yAxisDir = axisOption[3];

    boolean[][] isConsumed = new boolean[map.length][map[0].length];

    for(int y = yAxisStart ; 0 <= y && y < map.length ; y += yAxisDir){
      for(int x = xAxisStart ; 0 <= x && x < map[y].length ; x += xAxisDir){
        if(map[y][x] == 0)
          continue;

        int[] firstMeetBlock = findFirstMeetBlock(x, y, direction, map);
        int nX = firstMeetBlock[0]; int nY = firstMeetBlock[1];

        if(x == nX && y == nY)
          continue;

        if(map[y][x] == map[nY][nX] && !isConsumed[nY][nX]){
          isConsumed[nY][nX] = true;
          map[nY][nX] = map[nY][nX] * 2;
          map[y][x] = 0;
          MAX = Math.max(MAX, map[nY][nX]);
        }
        else{
          if(map[nY][nX] != 0){
            nX -= dX[direction];
            nY -= dY[direction];
          }

          if(y != nY || x != nX){
            map[nY][nX] = map[y][x];
            map[y][x] = 0;
          }
        }
      }
    }
  }

  private static int[] findFirstMeetBlock(int x, int y, int direction, int[][] map){
    int nX = x + dX[direction];
    int nY = y + dY[direction];
    if(!isInBoundary(nX, nY, map[0].length, map.length))
      return new int[]{x, y};

    while(isInBoundary(nX, nY, map[0].length, map.length)){
      if(map[nY][nX] != 0)
        return new int[]{nX, nY};

      nX += dX[direction];
      nY += dY[direction];
    }

    return new int[]{nX - dX[direction], nY - dY[direction]};
  }

  private static int[] getAxisOptionByDirection(int direction, int xBoundary, int yBoundary){
    int xAxisStart = 0;
    int yAxisStart = 0;
    int xAxisDir = 1;
    int yAxisDir = 1;

    if(direction == 1) {
      xAxisStart = xBoundary - 1;
      xAxisDir = -1;
    }

    if(direction == 2) {
      yAxisStart = yBoundary - 1;
      yAxisDir = -1;
    }

    return new int[]{xAxisStart, yAxisStart, xAxisDir, yAxisDir};
  }

  private static boolean isInBoundary(int x, int y, int xBound, int yBound){
    return 0 <= x && x < xBound && 0 <= y && y < yBound;
  }

  private static int[][] copyMap(int[][] map){
    int[][] newMap = new int[map.length][map[0].length];

    for(int y = 0 ; y < map.length ; y++)
      newMap[y] = Arrays.copyOf(map[y], map[y].length);

    return newMap;
  }
}
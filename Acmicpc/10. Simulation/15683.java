import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15683
public class Main {

  private static final int INVISIBLE = 0;
  private static final int WALL = 6;
  private static final int VISIBLE = -1;
  private static final int[] dX = {0, 1, 0, -1};
  private static final int[] dY = {-1, 0, 1, 0};

  private static int MIN_INVISIBLE = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][M];

    List<int[]> cameras = new ArrayList<>();
    List<int[]> fourDirectionCameras = new ArrayList<>();

    for(int y = 0 ; y < N ; y++){
      st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < M ; x++){
        map[y][x] = Integer.parseInt(st.nextToken());

        if(map[y][x] == INVISIBLE || map[y][x] == WALL)
          continue;

        if(map[y][x] == 5){
          fourDirectionCameras.add(new int[]{x, y});
          continue;
        }

        cameras.add(new int[]{x, y});
      }
    }

    for(int[] cameraPos : fourDirectionCameras){
      fillByCamera(map, cameraPos[0], cameraPos[1], 0);
    }

    mutation(map, cameras, new boolean[cameras.size()][dX.length], 0);

    System.out.println(MIN_INVISIBLE);
  }

  private static void mutation(int[][] map, List<int[]> cameras, boolean[][] used, int cameraIdx){
    if(cameraIdx >= cameras.size()){
      MIN_INVISIBLE = Math.min(MIN_INVISIBLE, countInvisibleField(map));
      return;
    }

    for(int dir = 0 ; dir < dX.length ; dir++){
      if(used[cameraIdx][dir])
        continue;

      used[cameraIdx][dir] = true;

      int cameraX = cameras.get(cameraIdx)[0];
      int cameraY = cameras.get(cameraIdx)[1];

      int[][] copiedMap = copyMap(map);

      fillByCamera(copiedMap, cameraX, cameraY, dir);

      mutation(copiedMap, cameras, used, cameraIdx + 1);

      used[cameraIdx][dir] = false;
    }
  }

  private static void fillByCamera(int[][] map, int cameraX, int cameraY, int direction){
    if(map[cameraY][cameraX] == 1) {
      fillStraight(map, cameraX, cameraY, direction);
      return;
    }

    if(map[cameraY][cameraX] == 2){
      fillStraight(map, cameraX, cameraY, direction);
      int opposite = (direction + 2) % dX.length;
      fillStraight(map, cameraX, cameraY, opposite);
      return;
    }

    if(map[cameraY][cameraX] == 3){
      fillStraight(map, cameraX, cameraY, direction);
      int subDirection = (direction + 1) % dX.length;
      fillStraight(map, cameraX, cameraY, subDirection);
      return;
    }

    if(map[cameraY][cameraX] == 4){
      fillStraight(map, cameraX, cameraY, direction);

      int subDirection1 = direction - 1 < 0 ? dX.length - 1 : direction - 1;
      fillStraight(map, cameraX, cameraY, subDirection1);

      int subDirection2 = (direction + 1) % dX.length;
      fillStraight(map, cameraX, cameraY, subDirection2);
      return;
    }

    if(map[cameraY][cameraX] == 5){
      for(int dir = 0 ; dir < dX.length ; dir++){
        fillStraight(map, cameraX, cameraY, dir);
      }
    }
  }

  private static void fillStraight(int[][] map, int cameraX, int cameraY, int direction){
    int x = cameraX + dX[direction];
    int y = cameraY + dY[direction];

    while(isInBoundary(x, y, map[0].length, map.length)){
      if(map[y][x] == WALL)
        break;

      if(map[y][x] == INVISIBLE){
        map[y][x] = VISIBLE;
      }

      x += dX[direction];
      y += dY[direction];
    }
  }

  private static boolean isInBoundary(int x, int y, int xBound, int yBound){
    return 0 <= x && x < xBound && 0 <= y && y < yBound;
  }

  private static int countInvisibleField(int[][] map){
    int count = 0;

    for(int y = 0 ; y < map.length ; y++){
      for(int x = 0 ; x < map[0].length ; x++){
        if(map[y][x] == INVISIBLE)
          count++;
      }
    }

    return count;
  }

  private static int[][] copyMap(int[][] original){
    int[][] newMap = new int[original.length][original[0].length];

    for(int y = 0 ; y < original.length ; y++){
      newMap[y] = Arrays.copyOf(original[y], original[y].length);
    }

    return newMap;
  }
}
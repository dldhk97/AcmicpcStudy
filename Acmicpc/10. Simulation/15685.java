import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/15685
public class Main {

  private static int[][] MAP = new int[101][101];
  private static int color = 1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    for(int i = 0 ; i < N ; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());

      List<Integer> directions = createDirections(x, y, d, g);
      draw(x, y, directions);
      color++;
    }

    int count = 0;
    for(int y = 0 ; y < 100 ; y++){
      for(int x = 0 ; x < 100 ; x++){
        if(MAP[y][x] != 0 && MAP[y][x + 1] != 0 && MAP[y + 1][x] != 0 && MAP[y + 1][x + 1] != 0)
          count++;
      }
    }

    System.out.println(count);
  }

  private static int[] dX = {1, 0, -1, 0};
  private static int[] dY = {0, -1, 0, 1};

  private static List<Integer> createDirections(int x, int y, int d, int g){
    List<Integer> directions = new ArrayList<>();
    directions.add(d);

    for(int i = 1 ; i <= g ; i++){
      for(int j = directions.size() - 1 ; j >= 0 ; j--){
        int nextDirection = (directions.get(j) + 1 ) % 4;
        directions.add(nextDirection);
      }
    }

    return directions;
  }

  private static void draw(int startX, int startY, List<Integer> directions){
    int x = startX;
    int y = startY;

    MAP[y][x] = color;

    for(int direction : directions){
      int nextX = x + dX[direction];
      int nextY = y + dY[direction];

      MAP[nextY][nextX] = color;

      x = nextX;
      y = nextY;
    }
  }
}

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/3190
public class Main {
  static final int[] dX = {1, 0, -1, 0};
  static final int[] dY = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[][] map = new int[N][N];

    int K = Integer.parseInt(br.readLine());
    for(int i = 0 ; i < K ; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken()) - 1;
      int x = Integer.parseInt(st.nextToken()) - 1;

      map[y][x] = -1;
    }

    int dir = 0;
    int time = 0;
    map[0][0] = 1;
    Deque<int[]> snake = new ArrayDeque<>();
    snake.add(new int[]{0, 0});

    int L = Integer.parseInt(br.readLine());
    for(int i = 0 ; i <= L ; i++){
      int X = Integer.MAX_VALUE;
      char C = '-';

      if(i < L){
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        C = st.nextToken().charAt(0);
      }

      while(true){
        if(time >= X && i < L)
          break;

        time++;
        int x = snake.getFirst()[0]; int y = snake.getFirst()[1];
        int nX = x + dX[dir];
        int nY = y + dY[dir];

        if(!isInBoundary(nX, nY, map[0].length, map.length) || map[nY][nX] == 1) {
          System.out.println(time);
          return;
        }
        if(map[nY][nX] != -1){
          map[snake.getLast()[1]][snake.getLast()[0]] = 0;
          snake.removeLast();
        }
        map[nY][nX] = 1;
        snake.addFirst(new int[]{nX, nY});
      }

      if(C == '-')
        continue;
      if(C == 'L'){
        if(dir == 0)
          dir = 4;
        dir = (dir - 1) % 4;
      }
      else{
        dir = (dir + 1) % 4;
      }
    }

    System.out.println(time);
  }

  private static boolean isInBoundary(int x, int y, int xBound, int yBound){
    return 0 <= x && x < xBound && 0 <= y && y < yBound;
  }
}

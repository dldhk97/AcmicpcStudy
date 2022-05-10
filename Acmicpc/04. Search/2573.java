import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2573
public class Main {

  public static void main(String[] args) throws IOException {
    int[][] arr = read();

    int year = 0;
    while(true){
      int remain = calcRemainMountains(arr);
      meltIceMountain(arr);

      if(remain == 0){
        year = 0;
        break;
      }

      if(remain >= 2)
        break;

      year++;
    }

    System.out.println(year);
  }

  private static int[][] read() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] arr = new int[N][M];
    for(int y = 0 ; y < N ; y++){
      st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < M ; x++)
        arr[y][x] = Integer.parseInt(st.nextToken());
    }

    return arr;
  }

  private static int calcRemainMountains(int[][] arr){
    boolean[][] visited = new boolean[arr.length][arr[0].length];

    int iceMountainCnt = 0;

    for(int y = 0 ; y < arr.length ; y++){
      for(int x = 0 ; x < arr[0].length ; x++){
        if(visited[y][x] || arr[y][x] == 0)
          continue;

        visitDFS(arr, visited, new int[]{x, y});
        iceMountainCnt++;
      }
    }

    return iceMountainCnt;
  }

  private static int[] dX = {0, 1, 0, -1};
  private static int[] dY = {-1, 0, 1, 0};

  private static void visitDFS(int[][] arr, boolean[][] visited, int[] startPoint){
    Stack<int[]> stack = new Stack<>();
    stack.push(startPoint);

    while(!stack.isEmpty()){
      int[] popped = stack.pop();
      int x = popped[0]; int y = popped[1];

      if(visited[y][x])
        continue;
      visited[y][x] = true;

      for(int i = 0 ; i < 4 ; i++){
        int nextX = x + dX[i];
        int nextY = y + dY[i];

        if(visited[nextY][nextX] || !isInBoundary(nextX, nextY, arr[0].length, arr.length))
          continue;
        if(arr[nextY][nextX] == 0)
          continue;

        stack.push(new int[]{nextX, nextY});
      }
    }
  }

  private static boolean isInBoundary(int x, int y, int bX, int bY){
    return 0 <= x && x < bX && 0 <= y && y < bY;
  }

  private static void meltIceMountain(int[][] arr){
    Queue<int[]> queue = new ArrayDeque<>();

    for(int y = 0 ; y < arr.length ; y++){
      for(int x = 0 ; x < arr[0].length ; x++){
        if(arr[y][x] == 0)
          continue;

        int seaWaterCnt = 0;
        for(int i = 0 ; i < 4 ; i++){
          int nextX = x + dX[i];
          int nextY = y + dY[i];

          if(!isInBoundary(nextX, nextY, arr[0].length, arr.length))
            continue;
          if(arr[nextY][nextX] == 0)
            seaWaterCnt++;
        }
        if(seaWaterCnt > 0)
          queue.add(new int[]{x, y, seaWaterCnt});
      }
    }

    while(!queue.isEmpty()){
      int[] polled = queue.poll();
      int x = polled[0]; int y = polled[1];
      int seaWaterCnt = polled[2];

      arr[y][x] = arr[y][x] - seaWaterCnt;
      if(arr[y][x] < 0)
        arr[y][x] = 0;
    }
  }
}

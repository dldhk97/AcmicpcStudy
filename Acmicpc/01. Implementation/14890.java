import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14890
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][N];

    for(int y = 0 ; y < N ; y++){
      st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < N ; x++){
        map[y][x] = Integer.parseInt(st.nextToken());
      }
    }

    int ways = countRowWays(map, L);
    ways += countColumnWays(map, L);

    System.out.println(ways);
  }

  private static int countRowWays(int[][] map, int L){
    int ways = 0;

    for (int[] rows : map) {
      if (isWayConnected(rows, L))
        ways++;
    }

    return ways;
  }

  private static int countColumnWays(int[][] map, int L){
    int ways = 0;

    for(int x = 0 ; x < map[0].length ; x++){
      int[] column = new int[map.length];
      for(int y = 0 ; y < map.length ; y++){
        column[y] = map[y][x];
      }

      if(isWayConnected(column, L))
        ways++;
    }

    return ways;
  }

  private static boolean isWayConnected(int[] arr, int L){
    Queue<Integer> upper = new ArrayDeque<>();
    Queue<Integer> lower = new ArrayDeque<>();

    for(int i = 0 ; i < arr.length - 1 ; i++){
      if(arr[i] + 1 < arr[i + 1])
        return false;
      if(arr[i] + 1 == arr[i + 1])
        upper.add(i);

      if(arr[i] > arr[i + 1] + 1)
        return false;
      if(arr[i] == arr[i + 1] + 1)
        lower.add(i + 1);
    }

    boolean[] stairs = new boolean[arr.length];

    while(!upper.isEmpty()){
      int endPoint = upper.poll();
      int startPoint = endPoint - L + 1;

      if(!canPlaceStair(arr, stairs, startPoint, endPoint))
        return false;
    }

    while(!lower.isEmpty()){
      int startPoint = lower.poll();
      int endPoint = startPoint + L - 1;

      if(!canPlaceStair(arr, stairs, startPoint, endPoint))
        return false;
    }

    return true;
  }

  private static boolean canPlaceStair(int[] arr, boolean[] stairs, int startPoint, int endPoint){
    if(startPoint < 0 || endPoint >= arr.length)
      return false;

    for(int i = startPoint ; i <= endPoint ; i++){
      if(arr[i] != arr[endPoint])
        return false;
      if(stairs[i])
        return false;

      stairs[i] = true;
    }

    return true;
  }
}
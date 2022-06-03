import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/20055
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    int[] belt = new int[N * 2];

    for(int i = 0 ; i < N * 2 ; i++)
      belt[i] = Integer.parseInt(st.nextToken());

    int cycle = simulate(K, belt);

    System.out.println(cycle);

  }

  private static int simulate(int K, int[] belt){
    int[] robots = new int[belt.length / 2];

    int cycle = 0;
    int zeroDurabilityCnt = 0;

    while(zeroDurabilityCnt < K){
      shiftArray(belt);
      shiftArray(robots);

      zeroDurabilityCnt += moveRobots(belt, robots);

      if(belt[0] > 0) {
        robots[0] = 1;

        belt[0]--;
        if(belt[0] <= 0)
          zeroDurabilityCnt++;
      }

      cycle++;
    }

    return cycle;
  }

  private static int moveRobots(int[] belt, int[] robots){
    int zeroCnt = 0;

    robots[robots.length - 1] = 0;

    for(int i = robots.length - 1 ; i > 0 ; i--){
      if(robots[i - 1] == 0)
        continue;

      if(robots[i] == 0 && belt[i] > 0){
        robots[i] = 1;
        robots[i - 1] = 0;

        belt[i]--;

        if(belt[i] == 0)
          zeroCnt++;
      }
    }

    robots[robots.length - 1] = 0;

    return zeroCnt;
  }

  private static void shiftArray(int[] arr){
    int tail = arr[arr.length - 1];

    for(int i = arr.length - 1 ; i > 0 ; i--)
      arr[i] = arr[i - 1];

    arr[0] = tail;
  }

}
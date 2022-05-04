import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11066
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    while(T-- > 0){
      int K = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());

      // 누적합 배열 생성
      int[] arr = new int[K];
      int[] acc = new int[K + 1];
      int sum = 0;
      for(int i = 0 ; i < K ; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
        sum += arr[i];
        acc[i + 1] = sum;
      }

      // dp 배열 생성, costs[i][j]는 i부터 j까지 합쳤을때의 최소 비용이다.
      int[][] costs = new int[K + 1][K + 1];

      // 길이 설정(length=1이면, abcdef에서 ab, bc, cd, de, ef 순회, 2이면 abc, bcd, cde...)
      for(int length = 1 ; length <= K ; length++){
        // ab, bc, cd 순으로 순회
        for(int start = 1 ; start + length <= K ; start++){
          int end = start + length;

          // 분단지점 바꿔가면서 순회. length=3이면, a+bcd, ab+cd, abc+d로 순회 가능
          for(int mid = start ; mid < end ; mid++){
            // 비용 계산. 중간점을 기준으로 양 비용을 더하고, 거기에 현재 구간의 누적합을 더함
            int nextCost = costs[start][mid] + costs[mid + 1][end] + (acc[end] - acc[start - 1]);

            if(costs[start][end] == 0)
              costs[start][end] = nextCost;
            else
              costs[start][end] = Math.min(costs[start][end], nextCost);
          }
        }
      }

      System.out.println(costs[1][K]);
    }
  }

}
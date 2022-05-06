import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2559
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] acc = new int[N + 1];

    int sum = 0;
    st = new StringTokenizer(br.readLine());
    for(int i = 1 ; i < acc.length ; i++) {
      sum += Integer.parseInt(st.nextToken());
      acc[i] = sum;
    }

    int max = Integer.MIN_VALUE;
    for(int i = K ; i < acc.length ; i++){
      int rangeSum = acc[i] - acc[i - K];
      max = Math.max(max, rangeSum);
    }

    System.out.println(max);
  }
}
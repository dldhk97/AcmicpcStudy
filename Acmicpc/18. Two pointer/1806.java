import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1806
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    int[] arr = new int[N];
    int left = 0;
    long sum = 0;

    int minLength = Integer.MAX_VALUE;

    st = new StringTokenizer(br.readLine());
    for (int right = 0; right < N; right++) {
      int value = Integer.parseInt(st.nextToken());
      arr[right] = value;
      sum += value;

      while(sum >= S && left <= right){
        minLength = Math.min(minLength, right - left + 1);
        sum -= arr[left++];
      }
    }

    if (minLength == Integer.MAX_VALUE)
      System.out.println(0);
    else
      System.out.println(minLength);
  }
}
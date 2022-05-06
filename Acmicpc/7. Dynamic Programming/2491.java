import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2491
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int K = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] arr = new int[K];

    for(int i = 0 ; i < K ; i++)
      arr[i] = Integer.parseInt(st.nextToken());

    int[] increase = new int[K];
    int[] decrease = new int[K];

    int max = 0;
    for(int i = 1 ; i < K ; i++){
      if(arr[i] >= arr[i - 1]) {
        increase[i] = increase[i - 1] + 1;
        max = Math.max(max, increase[i]);
      }

      if(arr[i] <= arr[i - 1]) {
        decrease[i] = decrease[i - 1] + 1;
        max = Math.max(max, decrease[i]);
      }
    }

    System.out.println(max + 1);
  }
}
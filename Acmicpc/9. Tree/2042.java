import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2042
public class Main {

  private static long[] arr;
  private static long[] tree;

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    arr = new long[N];
    tree = new long[N * 4];

    for(int i = 0 ; i < N ; i++)
      arr[i] = Long.parseLong(br.readLine());

    // create segment tree
    initSegmentTree(0, N - 1, 1);

    StringBuilder sb = new StringBuilder();
    // change nodes & calc sum
    for(int i = 0 ; i < M + K ; i++){
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken()) - 1;

      if(a == 1){
        // change node
        long c = Long.parseLong(st.nextToken());
        long diff = -(arr[b] - c);
        update(0, N - 1, 1, b, diff);
        arr[b] = c;
      }
      else{
        // calc sum
        int c = Integer.parseInt(st.nextToken()) - 1;
        long sum = calcSum(0, N - 1, 1, b, c);
        sb.append(sum).append("\n");
      }
    }

    System.out.println(sb.toString().trim());
  }

  private static long initSegmentTree(int start, int end, int treeIdx){
    if(start == end)
      return tree[treeIdx] = arr[start];

    int mid = (start + end) / 2;
    return tree[treeIdx] = initSegmentTree(start, mid, treeIdx * 2) + initSegmentTree(mid + 1, end, treeIdx * 2 + 1);
  }

  private static long calcSum(int start, int end, int treeIdx, int rangeStart, int rangeEnd){
    if(end < rangeStart || rangeEnd < start)
      return 0;

    if(rangeStart <= start && end <= rangeEnd)
      return tree[treeIdx];

    int mid = (start + end) / 2;
    return calcSum(start, mid, treeIdx * 2, rangeStart, rangeEnd) + calcSum(mid + 1, end, treeIdx * 2 + 1, rangeStart, rangeEnd);
  }

  private static void update(int start, int end, int treeIdx, int targetIdx, long diff){
    if(targetIdx < start || end < targetIdx)
      return;

    tree[treeIdx] += diff;

    if(start == end)
      return;

    int mid = (start + end) / 2;
    update(start, mid, treeIdx * 2, targetIdx, diff);
    update(mid + 1, end, treeIdx * 2 + 1, targetIdx, diff);
  }
}

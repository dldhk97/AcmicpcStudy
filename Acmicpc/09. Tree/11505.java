import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11505
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
    Arrays.fill(tree, -1);

    for(int i = 0 ; i < N ; i++)
      arr[i] = Long.parseLong(br.readLine());

    // create segment tree
    initSegmentTree(0, N - 1, 1);

    StringBuilder sb = new StringBuilder();

    for(int i = 0 ; i < M + K ; i++){
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken()) - 1;

      if(a == 1){
        // change node
        long c = Long.parseLong(st.nextToken());
        update(0, N - 1, 1, b, c);
        arr[b] = c;
      }
      else{
        // calc
        int c = Integer.parseInt(st.nextToken()) - 1;
        long multiply = calcMultiply(0, N - 1, 1, b, c);
        sb.append(multiply).append("\n");
      }
    }

    System.out.println(sb.toString().trim());
  }

  private static long initSegmentTree(int start, int end, int treeIdx){
    if(start == end)
      return tree[treeIdx] = arr[start];

    int mid = (start + end) / 2;
    return tree[treeIdx] = initSegmentTree(start, mid, treeIdx * 2) * initSegmentTree(mid + 1, end, treeIdx * 2 + 1) % 1000000007;
  }

  private static long calcMultiply(int start, int end, int treeIdx, int rangeStart, int rangeEnd){
    if(rangeEnd < start || end < rangeStart)
      return 1;

    if(rangeStart <= start && end <= rangeEnd)
      return tree[treeIdx];

    int mid = (start + end) / 2;
    return calcMultiply(start, mid, treeIdx * 2, rangeStart, rangeEnd) * calcMultiply(mid + 1, end, treeIdx * 2 + 1, rangeStart, rangeEnd) % 1000000007;
  }

  private static long update(int start, int end, int treeIdx, int targetIdx, long value){
    if(targetIdx < start || end < targetIdx) {
      if(targetIdx < 0 || arr.length - 1 < targetIdx || tree[treeIdx] == -1)
        return 1;
      return tree[treeIdx];
    }

    if(start == end)
      return tree[treeIdx] = value;

    int mid = (start + end) / 2;
    return tree[treeIdx] = update(start, mid, treeIdx * 2, targetIdx, value) * update(mid + 1, end, treeIdx * 2 + 1, targetIdx, value) % 1000000007;
  }
}

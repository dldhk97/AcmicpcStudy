import java.io.*;
import java.util.*;

public class Main {

  private static int[] arr;
  private static int[] tree;

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    tree = new int[arr.length * 4];

    // Tree initailziation
    initSegmentTree(0, arr.length - 1, 1);

    // sum values in range(0 ~ 2)
    int s = calcSum(0, arr.length - 1, 1, 0, 2);
    System.out.println(s);

    // update Node(index 0)
    update(0, arr.length - 1, 1, 0, +4);

    s = calcSum(0, arr.length - 1, 1, 0, 2);
    System.out.println(s);
  }

  private static int initSegmentTree(int start, int end, int treeIdx){
    if(start == end)
      return tree[treeIdx] = arr[start];

    int mid = (start + end) / 2;
    return tree[treeIdx] = initSegmentTree(start, mid, treeIdx * 2) + initSegmentTree(mid + 1, end, treeIdx * 2 + 1);
  }

  private static int calcSum(int start, int end, int treeIdx, int rangeStart, int rangeEnd){
    if(rangeEnd < start || end < rangeStart)
      return 0;

    if(rangeStart <= start && end <= rangeEnd)
      return tree[treeIdx];

    int mid = (start + end) / 2;
    return calcSum(start, mid, treeIdx * 2, rangeStart, rangeEnd) + calcSum(mid + 1, end, treeIdx * 2 + 1, rangeStart, rangeEnd);
  }

  private static void update(int start, int end, int treeIdx, int targetIdx, int diff){
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

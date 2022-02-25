import java.io.*;
import java.util.*;

public class Solution {

  // Complete the triplets function below.
  static long triplets(int[] a, int[] b, int[] c) {
    Arrays.sort(a);
    Arrays.sort(b);
    Arrays.sort(c);

    int[] newA = distinctArr(a);
    int[] newB = distinctArr(b);
    int[] newC = distinctArr(c);

    long result = 0;

    for(int i : newB){
      long aCount = countSmaller(i, newA);
      long cCount = countSmaller(i, newC);

      result += aCount * cCount;
    }

    return result;
  }

  private static int[] distinctArr(int[] arr){
    List<Integer> tempList = new ArrayList<>();

    int prevValue = -1;
    for(int i : arr){
      if(prevValue == i)
        continue;
      else
        prevValue = i;

      tempList.add(i);
    }

    return tempList.stream().mapToInt(Integer::intValue).toArray();
  }

  private static int countSmaller(int target, int[] arr){
    int left = 0;
    int right = arr.length - 1;

    while(left <= right){
      int mid = (left + right) / 2;

      if(arr[mid] <= target)
        left = mid + 1;
      else
        right = mid - 1;
    }

    return left;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] lenaLenbLenc = scanner.nextLine().split(" ");

    int lena = Integer.parseInt(lenaLenbLenc[0]);

    int lenb = Integer.parseInt(lenaLenbLenc[1]);

    int lenc = Integer.parseInt(lenaLenbLenc[2]);

    int[] arra = new int[lena];

    String[] arraItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < lena; i++) {
      int arraItem = Integer.parseInt(arraItems[i]);
      arra[i] = arraItem;
    }

    int[] arrb = new int[lenb];

    String[] arrbItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < lenb; i++) {
      int arrbItem = Integer.parseInt(arrbItems[i]);
      arrb[i] = arrbItem;
    }

    int[] arrc = new int[lenc];

    String[] arrcItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < lenc; i++) {
      int arrcItem = Integer.parseInt(arrcItems[i]);
      arrc[i] = arrcItem;
    }

    long ans = triplets(arra, arrb, arrc);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}

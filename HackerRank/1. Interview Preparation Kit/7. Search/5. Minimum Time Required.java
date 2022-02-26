import java.io.*;
import java.util.*;

public class Solution {

  // Complete the minTime function below.
  static long minTime(long[] machines, long goal) {
    Map<Long, Integer> machineMap = new HashMap<>();

    for(long machine : machines)
      machineMap.put(machine, machineMap.getOrDefault(machine, 0) + 1);

    long left = 1;
    long right = 1000000000L * 100000;

    while(left <= right){
      long mid = (left + right) / 2;
      long produce = calcProduce(machineMap, mid);

      if(produce >= goal){
        right = mid - 1;
      }
      else{
        left = mid + 1;
      }
    }

    return right + 1;
  }

  private static long calcProduce(Map<Long, Integer> machineMap, long time){
    long produce = 0;

    for(long key : machineMap.keySet())
      produce += (time / key) * machineMap.get(key);

    return produce;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] nGoal = scanner.nextLine().split(" ");

    int n = Integer.parseInt(nGoal[0]);

    long goal = Long.parseLong(nGoal[1]);

    long[] machines = new long[n];

    String[] machinesItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      long machinesItem = Long.parseLong(machinesItems[i]);
      machines[i] = machinesItem;
    }

    long ans = minTime(machines, goal);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}

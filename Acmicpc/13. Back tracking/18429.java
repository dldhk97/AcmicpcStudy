import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/18429
public class Main {

  private static int K;
  private static List<Integer> kits = new ArrayList<>();
  private static int result = 0;

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    for(int i = 0 ; i < N ; i++)
      kits.add(Integer.parseInt(st.nextToken()));

    used = new boolean[N];
    backtracking(500, N);

    System.out.println(result);
  }

  private static boolean[] used;
  private static void backtracking(int weight, int leftDays){
    if(weight < 500)
      return;
    if(leftDays <= 0){
      result++;
      return;
    }

    for(int i = 0 ; i < kits.size() ; i++){
      if(used[i])
        continue;

      used[i] = true;
      backtracking(weight - K + kits.get(i), leftDays - 1);
      used[i] = false;
    }
  }
}

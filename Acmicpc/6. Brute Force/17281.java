import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17281
public class Main {

  static int[][] result;
  static int MAX_SCORE = 0;
  static int N;

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    result = new int[N][9];

    for(int i = 0 ; i < N ; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j = 0 ; st.hasMoreTokens() ; j++){
        result[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    mutation(new boolean[8], new ArrayList<>());

    System.out.println(MAX_SCORE);
  }

  private static void mutation(boolean[] used, List<Integer> list){
    boolean isAllUsed = true;
    for(int i = 0 ; i < used.length ; i++){
      if(used[i])
        continue;

      used[i] = true;
      list.add(i + 1);
      mutation(used, list);
      list.remove(list.size() - 1);
      used[i] = false;

      isAllUsed = false;
    }

    if(isAllUsed) {
      list.add(3, 0);
      simulation(list);
      list.remove(3);
    }
  }

  private static void simulation(List<Integer> list){
    int score = 0;
    int index = 0;

    for(int yeening = 0 ; yeening < N ; yeening++){
      int out = 0;
      boolean[] bases = new boolean[3];

      while(true){
        int cur = result[yeening][list.get(index)];
        index = (index + 1) % 9;

        if(cur == 0) {
          if(++out >= 3)
            break;
          continue;
        }

        if(cur == 4){
          for(int i = 0 ; i < 3 ; i++){
            if(bases[i]){
              bases[i] = false;
              ++score;
            }
          }
          ++score;
          continue;
        }

        for(int i = 0 ; i < cur ; i++){
          for(int j = 2 ; j > 0 ; j--){
            if(j == 2 && bases[j]){
              bases[j] = false;
              ++score;
            }
            if(bases[j - 1]){
              bases[j] = true;
              bases[j - 1] = false;
            }
          }
        }
        bases[cur - 1] = true;
      }
    }

    MAX_SCORE = Math.max(MAX_SCORE, score);
  }


}

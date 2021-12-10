import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

  static int N;
  static boolean[] used;
  static int MIN = Integer.MAX_VALUE;

  static void mutation(int[] set, int depth){
    if(depth == N / 2){
      int[] set2 = new int[N / 2];
      int unSetIndex = 0;

      for(int i = 0 ; i < N ; i++)
        if(!used[i])
          set2[unSetIndex++] = i + 1;

      int team1 = calcSum(set);
      int team2 = calcSum(set2);

      int diff = Math.abs(team1 - team2);
      MIN = Math.min(diff, MIN);
      return;
    }

    int searchStart = depth == 0 ? 0 : set[depth - 1];

    for(int i = searchStart ; i < N ; i++){
      if(!used[i]){
        set[depth] = i + 1;
        used[i] = true;
        mutation(set, depth + 1);
        used[i] = false;
      }
    }

  }

  static int calcSum(int[] set){
    int sum = 0;
    for(int i = 0 ; i < N / 2 ; i++){
      for(int j = i + 1 ; j < N / 2 ; j++){
        int x = set[i] - 1;
        int y = set[j] - 1;
        sum += MAP[y][x];
      }
    }
    return sum;
  }

  static int[][] MAP = new int[N][N];;

  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    MAP = new int[N][N];

    for(int y = 0 ; y < N ; y++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < N ; x++){
        int value = Integer.parseInt(st.nextToken());
        if(x > y)
          MAP[x][y] += value;
        else
          MAP[y][x] += value;
      }
    }

    used = new boolean[N];
    mutation(new int[N / 2], 0);

    System.out.println(MIN);
  }

}
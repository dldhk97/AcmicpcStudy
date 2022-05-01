import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/42895
class Solution {
  int MAX = 32001;
  Map<Integer, Set<Integer>> map = new HashMap<>();
  int[] dp = new int[MAX];
  int N, number;

  public int solution(int N, int number) {
    if(N == number)
      return 1;

    Arrays.fill(dp, MAX);

    for(int i = 1 ; i <= 8 ; i++)
      map.put(i, new HashSet<>());

    this.N = N;
    this.number = number;

    // 특이한 수 생성
    int cost = 1;
    for(int i = N ; i < MAX && cost <= 8 ; ++cost){
      map.get(cost).add(i);
      dp[i] = cost;
      String special = String.valueOf(i) + N;
      i = Integer.parseInt(special);
    }

    solve();

    if(dp[number] > 8)
      return -1;
    return dp[number];
  }

  private void solve(){
    for(int cost1 = 1 ; cost1 <= 8 ; cost1++){
      for(int cost2 = 1 ; cost2 <= 8 ; cost2++){

        for(int value1 : map.get(cost1)){
          for(int value2 : map.get(cost2)){

            int[] values = {value1 + value2, value1 - value2, value1 * value2, value1 / value2};
            for(int value : values){
              if(value <= 0 || MAX <= value)
                continue;
              int costSum = cost1 + cost2;
              if(dp[value] < costSum || costSum > 8)
                continue;

              dp[value] = costSum;
              map.get(costSum).add(value);
            }
          }
        }
      }
    }
  }

}
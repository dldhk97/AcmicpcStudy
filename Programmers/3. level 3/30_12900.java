// https://programmers.co.kr/learn/courses/30/lessons/12900
class Solution {
  public int solution(int n) {
    long[] DP = new long[n];
    DP[0] = 1;
    DP[1] = 2;

    for(int i = 2 ; i < n ; i++){
      DP[i] = (DP[i - 1] + DP[i - 2]) % 1000000007L;
    }
    return (int)DP[n - 1];
  }

}
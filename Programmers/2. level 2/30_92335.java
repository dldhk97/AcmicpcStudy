import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/92335
class Solution {

  public int solution(int n, int k) {
    String converted = convert(n, k);

    int primeCnt = 0;

    StringTokenizer st = new StringTokenizer(converted, "0");
    while(st.hasMoreTokens()){
      long token = Long.parseLong(st.nextToken());
      if(isPrime(token))
        primeCnt++;
    }
    return primeCnt;
  }

  private String convert(int n, int k){
    StringBuilder sb = new StringBuilder();

    for(int i = n ; i > 0 ; i = i / k){
      int left = i % k;
      sb.insert(0, left);
    }

    return sb.toString();
  }

  private boolean isPrime(long number){
    if(number == 1)
      return false;

    for(long i = 2 ; i <= Math.sqrt(number) ; i++){
      if(number % i == 0)
        return false;
    }
    return true;
  }
}
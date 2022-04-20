import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/64062
class Solution {
  public int solution(int[] stones, int k) {
    int left = 1;
    int right = 200000000;

    int result = 0;

    while(left <= right){
      int mid = (left + right) / 2;

      if(canCross(stones, mid, k)){
        result = Math.max(result, mid);

        left = mid + 1;
        continue;
      }
      right = mid - 1;
    }

    return result;
  }

  private boolean canCross(int[] stones, int friends, int k){
    int skipped = 0;
    for(int stone : stones){
      if(stone < friends)
        skipped++;
      else
        skipped = 0;

      if(skipped == k)
        return false;
    }

    return true;
  }

}

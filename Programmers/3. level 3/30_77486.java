import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://programmers.co.kr/learn/courses/30/lessons/77486
class Solution {
  Map<String, Integer> nameMap = new HashMap<>();
  int[] parents;
  int[] result;

  public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
    for(int i = 0 ; i < enroll.length ; i ++){
      nameMap.put(enroll[i], i);
    }

    parents = new int[referral.length];
    Arrays.fill(parents, -1);
    for(int i = 0 ; i < referral.length ; i ++){
      String parentName = referral[i];
      if(!parentName.equals("-"))
        parents[i] = nameMap.get(parentName);
    }

    result = new int[enroll.length];
    for(int i = 0 ; i < seller.length ; i++){
      bottomUp(nameMap.get(seller[i]), amount[i] * 100);
    }

    return result;
  }

  private void bottomUp(int sellerIndex, int money){
    if(sellerIndex == -1)
      return;

    int left = money / 10;
    result[sellerIndex] += money - left;
    bottomUp(parents[sellerIndex], left);
  }
}
import java.util.HashMap;
import java.util.Map;

// https://programmers.co.kr/learn/courses/30/lessons/67258
class Solution {
  public int[] solution(String[] gems) {
    if(gems.length == 1)
      return new int[]{1, 1};

    Map<String, Integer> gemMap = createGemMap(gems);

    int[] contains = new int[gemMap.size()];

    int fillCount = 0;
    int answerLeft = 0;
    int answerRight = gems.length - 1;

    int left = 0;
    boolean isRightUpdated = true;

    for(int right = 0 ; right < gems.length ; right++){
      if(isRightUpdated){
        int rightGemIdx = gemMap.get(gems[right]);
        if(contains[rightGemIdx]++ == 0)
          fillCount++;
      }

      if(fillCount == gemMap.size()){
        int[] a = updateAnswer(fillCount, gemMap, new int[]{answerLeft, answerRight}, new int[]{left, right});
        answerLeft = a[0]; answerRight = a[1];

        int leftGemIdx = gemMap.get(gems[left]);
        contains[leftGemIdx]--;
        if(contains[leftGemIdx] == 0)
          fillCount--;
        left++;
        right--;
        isRightUpdated = false;
      }
      else{
        isRightUpdated = true;
      }
    }

    return new int[]{answerLeft + 1, answerRight + 1};
  }

  private int[] updateAnswer(int fillCount, Map<String, Integer> gemMap, int[] answer, int[] newAnswer){
    if(fillCount == gemMap.size()){
      int answerDiff = answer[1] - answer[0];
      int diff = newAnswer[1] - newAnswer[0];
      if(answerDiff > diff){
        return newAnswer;
      }
      else if(answerDiff == diff){
        if(answer[0] > newAnswer[0]){
          return newAnswer;
        }
      }
    }
    return answer;
  }

  private Map<String, Integer> createGemMap(String[] gems){
    Map<String, Integer> gemMap = new HashMap<>();

    int index = 0;

    for(String gem : gems){
      if(gemMap.containsKey(gem))
        continue;

      gemMap.put(gem, index++);
    }

    return gemMap;
  }
}
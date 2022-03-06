import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/1835
class Solution {
  private static final char[] NAMES = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
  private static final int NUMBER_OF_GUYS = 8;
  private static int RESULT;
  private static String[] CONDITIONS;

  public int solution(int n, String[] data) {
    CONDITIONS = Arrays.copyOf(data, data.length);
    RESULT = 0;
    mutation(new char[NUMBER_OF_GUYS], 0);
    return RESULT;
  }

  boolean[] used = new boolean[NUMBER_OF_GUYS];
  private void mutation(char[] guys, int index){
    if(index > 1){
      for(String condition : CONDITIONS){
        if(!checkCondition(guys, index, condition))
          return;
      }
    }

    if(index >= NUMBER_OF_GUYS){
      RESULT++;
      return;
    }

    for(int i = 0 ; i < 8 ; i++){
      if(used[i])
        continue;

      used[i] = true;
      guys[index] = NAMES[i];
      mutation(guys, index + 1);
      used[i] = false;
    }
  }

  private boolean checkCondition(char[] guys, int index, String condition){
    int leftIdx = findGuy(guys, index, condition.charAt(0));
    int rightIdx = findGuy(guys, index, condition.charAt(2));
    char operation = condition.charAt(3);
    int value = condition.charAt(4) - '0';

    if(leftIdx == -1 || rightIdx == -1)
      return true;

    int distance = Math.abs(leftIdx - rightIdx) - 1;

    if(operation == '=')
      return distance == value;

    if(operation == '>')
      return distance > value;

    return distance < value;
  }

  private int findGuy(char[] guys, int index, char target){
    for(int i = 0 ; i < index ; i++)
      if(guys[i] == target)
        return i;

    return -1;
  }
}
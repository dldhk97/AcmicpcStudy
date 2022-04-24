import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/92342
class Solution {
  int[] APEACH_SHOTS;
  int MAX_SCORE_DIFF = 0;
  List<int[]> LION_SHOTS_RESULT = new ArrayList<>();

  public int[] solution(int n, int[] info) {
    APEACH_SHOTS = info;

    int aPeachSum = 0;
    for(int i = 0 ; i < info.length ; i++)
      if(info[i] > 0)
        aPeachSum += 10 - i;

    backtracking(new int[info.length], 0, aPeachSum, 0, n);

    if(MAX_SCORE_DIFF == 0)
      return new int[]{-1};
    LION_SHOTS_RESULT.sort((o1, o2) -> {
      for(int i = o1.length - 1; i >= 0 ; i--){
        if(o1[i] == o2[i])
          continue;
        return -(o1[i] - o2[i]);
      }
      return 0;
    });
    return LION_SHOTS_RESULT.get(0);
  }

  private void backtracking(int[] lionShots, int index, int aPeachScore, int lionScore, int lionLeft){
    if(lionLeft <= 0 || index >= lionShots.length){
      int diff = lionScore - aPeachScore;
      if(0 < diff){
        if(MAX_SCORE_DIFF > diff)
          return;
        if(MAX_SCORE_DIFF < diff)
          LION_SHOTS_RESULT.clear();

        // 남은 화살 정리
        int[] copied = Arrays.copyOf(lionShots, lionShots.length);
        if(lionLeft > 0){
          for(int i = copied.length - 1 ; i >= 0 ; i--){
            if(copied[i] == 0){
              copied[i] = lionLeft;
              break;
            }
          }
        }

        LION_SHOTS_RESULT.add(copied);
        MAX_SCORE_DIFF = diff;
      }
      return;
    }

    int score = 10 - index;
    if(APEACH_SHOTS[index] > 0){
      int newLionShot = APEACH_SHOTS[index] + 1;
      if(lionLeft >= newLionShot) {
        lionShots[index] = newLionShot;
        backtracking(lionShots, index + 1, aPeachScore - score, lionScore + score, lionLeft - newLionShot);
        lionShots[index] = 0;
      }
      backtracking(lionShots, index + 1, aPeachScore, lionScore, lionLeft);
      return;
    }

    lionShots[index] = 1;
    backtracking(lionShots, index + 1, aPeachScore, lionScore + score, lionLeft - 1);
    lionShots[index] = 0;
    backtracking(lionShots, index + 1, aPeachScore, lionScore, lionLeft);
  }
}
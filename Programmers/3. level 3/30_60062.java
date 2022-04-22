import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/60062
class Solution {
  int MIN = Integer.MAX_VALUE;
  int[] WEAK;
  int N;

  public int solution(int n, int[] weak, int[] dist) {
    N = n;
    WEAK = Arrays.copyOf(weak, weak.length * 2);

    for(int i = weak.length ; i < WEAK.length ; i++)
      WEAK[i] = WEAK[i - weak.length] + n;

    mutation(dist, new boolean[dist.length], 0, new LinkedList<>());

    return MIN == Integer.MAX_VALUE ? -1 : MIN;
  }

  private void mutation(int[] dist, boolean[] used, int usedCnt, List<Integer> friends){
    if(usedCnt >= dist.length){
      cover(friends);
      return;
    }

    for(int i = 0 ; i < dist.length ; i++){
      if(used[i])
        continue;

      used[i] = true;
      friends.add(dist[i]);

      mutation(dist, used, usedCnt + 1, friends);

      friends.remove(friends.size() - 1);
      used[i] = false;
    }
  }

  // 주어진 순열로 다 덮을 수 있는지 탐색
  private void cover(List<Integer> friends){
    // 시작점은 모든점에 대해서 바꿔가면서 시도
    for(int i = 0 ; i <= WEAK.length / 2 ; i++){
      int cursor = WEAK[i];
      int endPoint = WEAK[i + WEAK.length / 2 - 1];

      int usedFriend = 0;
      for(int dist : friends){
        cursor += dist;
        usedFriend++;

        if(cursor >= endPoint){
          MIN = Math.min(MIN, usedFriend);
          break;
        }

        // 마지막으로 방문한 지점(cursor) 이후의 지점 탐색
        for(int j = i ; ; j++){
          if(WEAK[j] > cursor){
            cursor = WEAK[j];
            break;
          }
        }
      }
    }
  }
}
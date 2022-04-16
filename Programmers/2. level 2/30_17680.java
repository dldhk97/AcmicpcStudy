import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/17680
class Solution {
  public int solution(int cacheSize, String[] cities) {
    Deque<String> deque = new LinkedList<>();

    int time = 0;

    for(String c : cities){
      String city = c.toLowerCase();

      if(cacheSize == 0 || !deque.contains(city)){
        time += 5;

        if(deque.size() >= cacheSize && !deque.isEmpty()){
          deque.removeLast();
        }
        deque.addFirst(city);
        continue;
      }

      time += 1;
      deque.remove(city);
      deque.addFirst(city);
    }

    return time;
  }
}
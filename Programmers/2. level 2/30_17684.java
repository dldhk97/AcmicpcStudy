import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/17684
class Solution {

  private static Map<String, Integer> map = new HashMap<>();

  public int[] solution(String msg) {
    createMap();

    List<Integer> result = new ArrayList<>();

    for(int i = 0 ; i < msg.length() ; i++){
      StringBuilder sb = new StringBuilder();

      String longest = "";
      int j = 0;
      for(; i + j < msg.length() ; j++){
        sb.append(msg.charAt(i + j));
        if(!map.containsKey(sb.toString())){
          map.put(sb.toString(), map.size() + 1);
          break;
        }
        longest = sb.toString();
      }

      result.add(map.get(longest));
      i += j - 1;
    }

    return result.stream().mapToInt(Integer::valueOf).toArray();
  }

  private void createMap(){
    int index = 1;
    for(int i = 0 ; i < 26 ; i++){
      char c = (char)('A' + i);
      map.put(String.valueOf(c), index++);
    }
  }
}
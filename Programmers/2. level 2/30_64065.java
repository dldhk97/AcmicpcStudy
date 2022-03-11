import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/64065
class Solution {
  public int[] solution(String s) {
    List<List<Integer>> splitList = convertIntoList(s);
    splitList.sort(Comparator.comparingInt(List::size));

    int size = splitList.get(splitList.size() - 1).size();
    int[] result = new int[size];

    Map<Integer, Integer> map = new HashMap<>();
    for(int i : splitList.get(splitList.size() - 1))
      map.put(i, map.getOrDefault(i, 0) + 1);

    for(int i = 0 ; i < splitList.size() ; i++){
      for(int key : splitList.get(i)){
        int cnt = map.getOrDefault(key, 0);
        if(cnt > 0){
          result[i] = key;
          map.put(key, cnt - 1);
        }
      }
    }

    return result;
  }

  private List<List<Integer>> convertIntoList(String s){
    List<List<Integer>> result = new ArrayList<>();
    Stack<Character> stack = new Stack<>();

    for(char c : s.toCharArray()){
      if(c == '}'){
        List<Integer> temp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){
          char popped = stack.pop();
          if(popped == '{'){
            if(sb.length() > 0)
              temp.add(0, Integer.parseInt(sb.toString()));
            break;
          }
          if(popped == ','){
            if(sb.length() > 0)
              temp.add(0, Integer.parseInt(sb.toString()));
            sb.delete(0, sb.length());
            continue;
          }
          sb.insert(0, popped - '0');
        }
        if(temp.size() > 0)
          result.add(temp);
        continue;
      }

      stack.push(c);
    }
    return result;
  }
}
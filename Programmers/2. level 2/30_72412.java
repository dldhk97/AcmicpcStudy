import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://programmers.co.kr/learn/courses/30/lessons/72412
class Solution {

  private final Map<String, List<Integer>> scoreMap = new HashMap<>();

  public int[] solution(String[] info, String[] query) {
    for(String i : info){
      String[] splitted = i.split(" ");
      mutation(splitted, 0, "");
    }

    for (List<Integer> list : scoreMap.values())
      list.sort(Integer::compareTo);

    int[] answer = new int[query.length];
    for(int i = 0 ; i < query.length ; i++){
      answer[i] = processQuery(query[i]);
    }

    return answer;
  }

  private void mutation(String[] tokens, int index, String key){
    if(index > 3){
      scoreMap.put(key, scoreMap.getOrDefault(key, new ArrayList<>()));
      scoreMap.get(key).add(Integer.parseInt(tokens[4]));
      return;
    }

    mutation(tokens, index + 1, key + "-");
    mutation(tokens, index + 1, key + tokens[index]);
  }

  private int processQuery(String query){
    String q = query.replaceAll(" and ","");
    String[] splitted = q.split(" ");

    String key = splitted[0];
    int targetScore = Integer.parseInt(splitted[1]);
    return countApplicant(key, targetScore);
  }

  private int countApplicant(String key, int targetScore){
    List<Integer> scores = scoreMap.getOrDefault(key, new ArrayList<>());

    int left = 0;
    int right = scores.size();

    while(left < right){
      int mid = (left + right) / 2;
      if(scores.get(mid) < targetScore){
        left = mid + 1;
      }
      else{
        right = mid;
      }
    }
    return scores.size() - right;
  }
}
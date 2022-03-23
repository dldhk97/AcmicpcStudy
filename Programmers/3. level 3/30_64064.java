import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/64064
class Solution {
  List<Set<String>> result;

  public int solution(String[] user_id, String[] banned_id) {
    result = new ArrayList<>();

    Map<String, List<String>> map = new HashMap<>();
    Map<String, Integer> bannedMap = new HashMap<>();

    for(String banned : banned_id){
      map.put(banned, new ArrayList<>());
      bannedMap.put(banned, bannedMap.getOrDefault(banned, 0) + 1);

      for(String user : user_id){
        if(banned.length() != user.length())
          continue;

        if(isBanRuleMatches(user, banned))
          map.get(banned).add(user);
      }
    }

    combination(map, bannedMap, new HashSet<>());

    return result.size();
  }

  private void combination(Map<String, List<String>> map, Map<String, Integer> bannedMap, Set<String> set){
    if(isFinished(bannedMap)) {
      boolean isContained = false;
      for(Set<String> s : result){
        if(s.containsAll(set) || set.containsAll(s)) {
          isContained = true;
          break;
        }
      }
      if(!isContained){
        result.add(new HashSet<>(set));
      }
      return;
    }

    for(String banned_id : bannedMap.keySet()){
      if(bannedMap.get(banned_id) <= 0)
        continue;

      for(String user_id : map.get(banned_id)){
        if(set.contains(user_id))
          continue;
        bannedMap.put(banned_id, bannedMap.get(banned_id) - 1);

        set.add(user_id);
        combination(map, bannedMap, set);
        set.remove(user_id);

        bannedMap.put(banned_id, bannedMap.get(banned_id) + 1);
      }
    }
  }

  private boolean isFinished(Map<String, Integer> bannedMap){
    for(int i : bannedMap.values())
      if(i != 0)
        return false;
    return true;
  }

  private boolean isBanRuleMatches(String user_id, String banned_id){
    for(int i = 0 ; i < banned_id.length() ; i++){
      if(banned_id.charAt(i) == '*')
        continue;
      if(banned_id.charAt(i) != user_id.charAt(i))
        return false;
    }
    return true;
  }
}
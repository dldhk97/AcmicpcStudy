import java.util.HashMap;
import java.util.Map;

// https://programmers.co.kr/learn/courses/30/lessons/17677
class Solution {
  public int solution(String str1, String str2) {
    Map<String, Integer> m1 = preprocess(str1);
    Map<String, Integer> m2 = preprocess(str2);

    if(m1.size() == 0 && m2.size() == 0)
      return 65536;

    int intersection = getIntersection(m1, m2);
    int union = getUnion(m1, m2);

    double result = ((double)intersection / union) * 65536;

    return (int)result;
  }

  private Map<String, Integer> preprocess(String str){
    String lowerStr = str.toLowerCase();

    Map<String, Integer> hashMap = new HashMap<>();
    for(int i = 0 ; i < lowerStr.length() - 1 ; i++){
      char c1 = lowerStr.charAt(i);
      char c2 = lowerStr.charAt(i + 1);

      if(('a' <= c1 && c1 <= 'z') && ('a' <= c2 && c2 <= 'z')){
        String s = String.valueOf(c1) + c2;
        hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
      }
    }
    return hashMap;
  }

  private int getUnion(Map<String, Integer> m1, Map<String, Integer> m2){
    Map<String, Integer> hashMap = new HashMap();

    for(String key : m1.keySet()){
      hashMap.put(key, m1.get(key));
    }

    for(String key : m2.keySet()){
      hashMap.put(key, Math.max(hashMap.getOrDefault(key, 0), m2.get(key)));
    }

    int count = 0;
    for(int value : hashMap.values())
      count += value;

    return count;
  }

  private int getIntersection(Map<String, Integer> m1, Map<String, Integer> m2){
    int count = 0;

    for(String key1 : m1.keySet()){
      for(String key2: m2.keySet()){
        if(key1.equals(key2)){
          count += Math.min(m1.get(key1), m2.get(key2));
          break;
        }
      }
    }
    return count;
  }
}
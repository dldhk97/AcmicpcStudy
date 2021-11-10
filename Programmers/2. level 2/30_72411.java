import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://programmers.co.kr/learn/courses/30/lessons/72411
class Solution {
  int[] BITS = new int[26];
  Map<String, Integer> BIT_SETS = new HashMap<>();

  public String[] solution(String[] orders, int[] course) {
    getBitsets(orders);

    List<String> mostUsedOrders = getMoseUsedOrders(course);
    mostUsedOrders.sort(String::compareTo);

    return mostUsedOrders.toArray(new String[0]);
  }

  private int alphabetToBit(char c){
    int diff = c - 'A';
    if(BITS[diff] == 0)
      return BITS[diff] = (int) Math.pow(2, diff);
    return BITS[diff];
  }

  private int alphabetStreamToBit(String s){
    int bitset = 0;
    for(char c : s.toCharArray()){
      bitset += alphabetToBit(c);
    }
    return bitset;
  }

  Map<String, Boolean> visited;
  private void getBitsets(String[] orders){
    for(String order : orders){
      int bits = alphabetStreamToBit(order);
      String bitset = Integer.toBinaryString(bits);
      int cntOfOne = countOne(bitset);

      visited = new HashMap<>();
      generateMinorBitsets(bitset, cntOfOne);
    }
  }

  private int countOne(String bitset){
    int cnt = 0;
    for(char c : bitset.toCharArray()){
      if(c == '1')
        cnt++;
    }
    return cnt;
  }

  private void generateMinorBitsets(String bitset, int cntOfOne){
    if(cntOfOne < 2)
      return;

    String alphabets = bitsetToAlphabets(bitset);

    if(visited.containsKey(alphabets))
      return;
    visited.put(alphabets, true);

    BIT_SETS.put(alphabets, BIT_SETS.getOrDefault(alphabets, 0) + 1);

    int produced = 0;
    for(int i = 0 ; i < bitset.length() ; i++){
      if(produced >= cntOfOne)
        break;
      if(bitset.charAt(i) == '0')
        continue;

      generateMinorBitsets(new StringBuilder(bitset).deleteCharAt(i).insert(i, "0").toString(), cntOfOne - 1);
      produced++;
    }
  }

  private String bitsetToAlphabets(String bitset){
    StringBuilder sb = new StringBuilder();

    byte[] bytes = bitset.getBytes(StandardCharsets.UTF_8);
    for(int i = bytes.length - 1 ; i >= 0 ; i--){
      if(bytes[i] == '0')
        continue;
      int c = bytes.length - i - 1 + 'A';
      sb.append((char)c);
    }
    return sb.toString();
  }

  private List<String> getMoseUsedOrders(int[] courses){
    Map<Integer, List<String>> mostUsedMenus = new HashMap<>();
    Map<Integer, Integer> mostUsedCounts = new HashMap<>();

    for(String order : BIT_SETS.keySet()){
      int usedCount = BIT_SETS.get(order);
      if(usedCount < 2)
        continue;

      int course = order.length();
      if(!isCourseContains(courses, course))
        continue;

      updateOrders(mostUsedMenus, mostUsedCounts, order);
    }
    return concatList(mostUsedMenus);
  }

  private boolean isCourseContains(int[] courses, int course){
    for(int c : courses){
      if(c == course){
        return true;
      }
    }
    return false;
  }

  private void updateOrders(Map<Integer, List<String>> mostUsedMenus, Map<Integer, Integer> mostUsedCounts, String order){
    int usedCount = BIT_SETS.get(order);
    int course = order.length();

    int mostUsedCnt = mostUsedCounts.getOrDefault(course, 0);

    if(mostUsedCnt <= 0 || mostUsedCnt < usedCount){
      mostUsedCounts.put(course, usedCount);

      List<String> newList = new LinkedList<>();
      newList.add(order);
      mostUsedMenus.put(course, newList);
      return;
    }

    if(mostUsedCnt > usedCount)
      return;

    mostUsedMenus.get(course).add(order);
  }

  private List<String> concatList(Map<Integer, List<String>> mostUsedOrders){
    List<String> result = new LinkedList<>();
    for(List<String> orders : mostUsedOrders.values()){
      result.addAll(orders);
    }
    return result;
  }

}
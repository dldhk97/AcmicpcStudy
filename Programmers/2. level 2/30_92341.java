import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/92341
class Solution {
  int defaultTime, defaultFee, unitTime, unitFee;

  public int[] solution(int[] fees, String[] records) {
    defaultTime = fees[0];
    defaultFee = fees[1];
    unitTime = fees[2];
    unitFee = fees[3];

    Map<String, Integer> feeMap = new TreeMap<>(String::compareTo);
    Map<String, Integer> carInTimeMap = new TreeMap<>();
    Map<String, Integer> carTimeMap = new TreeMap<>();

    for(String record : records){
      StringTokenizer st = new StringTokenizer(record);
      int time = stringTimeToInt((st.nextToken()));
      String carNumber = st.nextToken();
      String actionType = st.nextToken();

      if(actionType.equals("IN")){
        carInTimeMap.put(carNumber, time);
        continue;
      }

      int timeDiff = time - carInTimeMap.get(carNumber);
      carTimeMap.put(carNumber, carTimeMap.getOrDefault(carNumber, 0) + timeDiff);
      carInTimeMap.remove(carNumber);
    }

    for(String carNumber : carInTimeMap.keySet()){
      int timeDiff = stringTimeToInt("23:59") - carInTimeMap.get(carNumber);
      carTimeMap.put(carNumber, carTimeMap.getOrDefault(carNumber, 0) + timeDiff);
    }

    for(String carNumber : carTimeMap.keySet()){
      int timeDiff = carTimeMap.get(carNumber);
      calc(feeMap, timeDiff, carNumber);
    }

    return feeMap.values().stream().mapToInt(Integer::valueOf).toArray();
  }

  private void calc(Map<String, Integer> feeMap, int timeDiff, String carNumber){
    int fee = defaultFee;

    if(timeDiff > defaultTime){
      int leftTime = timeDiff - defaultTime;
      fee += (int)Math.ceil((double) leftTime / unitTime) * unitFee;
    }

    feeMap.put(carNumber, feeMap.getOrDefault(carNumber, 0) + fee);
  }

  private int stringTimeToInt(String time){
    StringTokenizer st = new StringTokenizer(time, ":");
    int hour = Integer.parseInt(st.nextToken());
    int min = Integer.parseInt(st.nextToken());

    return hour * 60 + min;
  }
}
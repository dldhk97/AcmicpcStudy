import java.util.StringTokenizer;

// https://programmers.co.kr/learn/courses/30/lessons/72414
class Solution {
  private static int MAX;

  public String solution(String play_time, String adv_time, String[] logs) {
    MAX = timeStringToNum(play_time);
    int[] playCounts = new int[MAX];

    for(String log : logs){
      StringTokenizer st = new StringTokenizer(log, "-");
      int startTime = timeStringToNum(st.nextToken());
      int endTime = timeStringToNum(st.nextToken());

      for(int i = startTime ; i < endTime ; i++)
        playCounts[i]++;
    }

    int intAdvTime = timeStringToNum(adv_time);

    long result = solve(playCounts, intAdvTime);

    return numTimeToString(result);
  }

  private int timeStringToNum(String time){
    StringTokenizer st = new StringTokenizer(time, ":");
    int hour = Integer.parseInt(st.nextToken()) * 60 * 60;
    int min = Integer.parseInt(st.nextToken()) * 60;
    int second = Integer.parseInt(st.nextToken());
    return hour + min + second;
  }

  private String numTimeToString(long time){
    long hour = time / 60 / 60;
    long min = (time - (hour * 60 * 60)) / 60 ;
    long second = time - (hour * 60 * 60) - (min * 60);
    return formatStringTime(hour, min, second);
  }

  private String formatStringTime(long hour, long min, long second){
    String stringHour = formatDigit(hour);
    String stringMin = formatDigit(min);
    String stringSecond = formatDigit(second);
    return stringHour + ":" + stringMin + ":" + stringSecond;
  }

  private String formatDigit(long x){
    return x < 10 ? "0" + x : String.valueOf(x);
  }

  private long solve(int[] playCounts, int advTime){
    long maxCounts = 0;
    int maxCountIdx = 0;

    long sum = 0;
    for(int i = 0 ; i < advTime ; i++)
      sum += playCounts[i];

    for(int i = 0 ; i <= MAX - advTime ; i++){
      if(sum > maxCounts){
        maxCounts = sum;
        maxCountIdx = i;
      }

      if(i < MAX - advTime){
        sum -= playCounts[i];
        sum += playCounts[i + advTime];
      }
    }

    return maxCountIdx;
  }
}
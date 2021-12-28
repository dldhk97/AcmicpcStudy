import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/17676
class Solution {
  public int solution(String[] lines) {
    Queue<Integer> queue = new LinkedList<>();
    List<int[]> logs = new LinkedList<>();

    for(String line : lines){
      StringTokenizer st = new StringTokenizer(line);
      st.nextToken();

      int endTime = StringTimeToIntTime(st.nextToken());
      String stringDuration = st.nextToken();
      int duration = StringSecToIntSec(stringDuration.substring(0, stringDuration.length() - 1));
      int startTime = endTime - duration + 1;

      queue.add(startTime);
      queue.add(endTime);

      logs.add(new int[] {startTime, endTime});
    }

    int max = 0;
    while(!queue.isEmpty()){
      int start = queue.poll();
      int end = start + 999;

      int cnt = 0;
      for(int[] log : logs){
        int logStart = log[0];
        int logEnd = log[1];

        if(end < logStart || logEnd < start)
          continue;

        cnt++;
      }

      max = Math.max(max, cnt);
    }
    return max;
  }

  private int StringTimeToIntTime(String time){
    StringTokenizer st = new StringTokenizer(time, ":");
    int hour = Integer.parseInt(st.nextToken()) * 60 * 60 * 1000;
    int min =  Integer.parseInt(st.nextToken()) * 60 * 1000;
    int sec = StringSecToIntSec(st.nextToken());
    return hour + min + sec;
  }

  private int StringSecToIntSec(String stringSec){
    return (int)(Double.parseDouble(stringSec) * 1000);
  }
}
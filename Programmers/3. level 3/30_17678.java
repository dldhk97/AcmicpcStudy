import java.text.DecimalFormat;
import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/17678
class Solution {
  public String solution(int n, int t, int m, String[] timetable) {
    PriorityQueue<Long> crewArriveTimes = new PriorityQueue<>();

    for(String time : timetable)
      crewArriveTimes.add(convertIntoMin(time));

    List<Long> boardingTimes = createBoardingTimes(n, t);

    long lastBoardingTime = boardingTimes.get(boardingTimes.size() - 1);
    List<Long> lastBoarderArriveTimes = new ArrayList<>();

    int[] boardCount = new int[n];
    for(int i = 0 ; i < n ; i++){
      if(crewArriveTimes.isEmpty())
        return convertIntoStr(lastBoardingTime);

      while(!crewArriveTimes.isEmpty()){
        long crewArriveTime = crewArriveTimes.peek();

        if(boardCount[i] >= m)
          break;
        if(crewArriveTime > boardingTimes.get(i))
          break;

        boardCount[i]++;
        Long polled = crewArriveTimes.poll();

        if(lastBoardingTime == boardingTimes.get(i))
          lastBoarderArriveTimes.add(polled);
      }
    }

    if(boardCount[n - 1] < m)
      return convertIntoStr(lastBoardingTime);

    lastBoarderArriveTimes.sort(Long::compareTo);

    int cnt = 0;
    for(int i = 0 ; i < lastBoarderArriveTimes.size() ; i++){
      if(cnt >= m)
        break;
      cnt++;
    }

    long answer = lastBoarderArriveTimes.get(cnt - 1) - 1;

    return convertIntoStr(answer);
  }

  private long convertIntoMin(String time){
    StringTokenizer st = new StringTokenizer(time, ":");
    long hourStr = Long.parseLong(st.nextToken());
    long minStr = Long.parseLong(st.nextToken());
    return hourStr * 60 + minStr;
  }

  private String convertIntoStr(long time){
    long hour = time / 60;
    long min = time - hour * 60;
    DecimalFormat df = new DecimalFormat("00");
    return df.format(hour) + ":" + df.format(min);
  }

  private List<Long> createBoardingTimes(int n, int t){
    List<Long> boardingTimes = new ArrayList<>();
    long firstBoard = convertIntoMin("09:00");
    boardingTimes.add(firstBoard);

    for(int i = 1 ; i < n ; i++){
      long boardTime = firstBoard + (long)t * i;
      boardingTimes.add(boardTime);
    }
    return boardingTimes;
  }

}
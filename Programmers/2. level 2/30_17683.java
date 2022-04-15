import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/17683
class Solution {

  private static final String[] CODES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

  public String solution(String m, String[] musicinfos) {
    PriorityQueue<Music> pq = new PriorityQueue<>();

    int[] mRhythm = stringRhythmIntoInt(m);

    int musicIndex = 1;
    for(String musicInfo : musicinfos){
      Music music = parseMusicInfo(musicInfo, musicIndex++);

      for(int i = 0 ; i <= music.playTime - mRhythm.length ; i++){
        boolean isSucceed = true;
        for(int j = 0 ; j < mRhythm.length ; j++){
          int temp = (i + j) % music.rhythm.length;
          if(music.rhythm[temp] != mRhythm[j]){
            isSucceed = false;
            break;
          }
        }

        if(isSucceed){
          if(!pq.contains(music))
            pq.add(music);
        }
      }

    }

    if(pq.isEmpty())
      return "(None)";

    return pq.poll().title;
  }

  private Music parseMusicInfo(String musicInfo, int musicIndex){
    StringTokenizer st = new StringTokenizer(musicInfo, ",");

    int startTime = stringTimeToInt(st.nextToken());
    int endTime = stringTimeToInt(st.nextToken());
    String title = st.nextToken();
    int[] rhythm = stringRhythmIntoInt(st.nextToken());

    return new Music(musicIndex, startTime, endTime, title, rhythm);
  }

  private int stringTimeToInt(String time){
    StringTokenizer st = new StringTokenizer(time, ":");
    int hour = Integer.parseInt(st.nextToken()) * 60;
    int min = Integer.parseInt(st.nextToken());
    return hour + min;
  }

  private String intTimeToString(int time){
    int hour = time / 60;
    int min = time - hour * 60;
    return String.format("%02d", hour) + ":" + String.format("%02d", min);
  }

  private int[] stringRhythmIntoInt(String rhythm){
    List<Integer> list = new ArrayList<>();
    for(int i = 0 ; i < rhythm.length() ; i++){
      char c = rhythm.charAt(i);
      if(c == '#'){
        list.set(list.size() - 1, list.get(list.size() - 1) + 1);
        continue;
      }

      for(int j = 0 ; j < CODES.length ; j++){
        if(CODES[j].equals(String.valueOf(c))){
          list.add(j);
          break;
        }
      }
    }

    int[] result = list.stream().mapToInt(Integer::valueOf).toArray();
    return result;
  }
}

class Music implements Comparable<Music>{
  int index;
  int startTime;
  int endTime;
  int playTime;

  String title;
  int[] rhythm;

  public Music(int index, int startTime, int endTime, String title, int[] rhythm) {
    this.index = index;
    this.startTime = startTime;
    this.endTime = endTime;
    this.playTime = endTime - startTime;
    this.title = title;
    this.rhythm = rhythm;
  }

  @Override
  public int compareTo(Music o) {
    if(this.playTime == o.playTime){
      return this.index - o.index;
    }
    return o.playTime - this.playTime;
  }
}
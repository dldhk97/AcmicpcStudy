import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/17683
// 정확성 60점인데, 반례를 못 찾겠음.
class Solution {
  private static int MAX;

  private static final String[] codes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

  private static int[] musicStream;
  private static int[] musicIndexStream;
  private static Map<Integer, Music> musicMap;

  public String solution(String m, String[] musicInfos) {
    MAX = 1440;
    musicStream = new int[MAX];
    musicIndexStream = new int[MAX];
    musicMap = new HashMap<>();

    Arrays.fill(musicStream, -1);

    parseMusicInfos(musicInfos);

    List<Integer> mList = stringStreamIntoIntStream(m);
    return search(mList);
  }

  private void parseMusicInfos(String[] musicInfos){
    int musicIndex = 1;

    for(String musicInfo : musicInfos){
      StringTokenizer st = new StringTokenizer(musicInfo, ",");

      int startTime = stringTimeToInt(st.nextToken());
      int endTime = stringTimeToInt(st.nextToken());
      String name = st.nextToken();
      String stream = st.nextToken();

      musicMap.put(musicIndex, new Music(musicIndex, startTime, endTime, name));

      List<Integer> streamList = stringStreamIntoIntStream(stream);

      int streamIdx = 0;
      for(int i = startTime ; i < endTime ; i++){
        musicStream[i] = streamList.get(streamIdx);
        musicIndexStream[i] = musicIndex;

        streamIdx = (streamIdx + 1) % streamList.size();
      }

      musicIndex++;
    }
  }

  private String search(List<Integer> mList){
    PriorityQueue<Music> pq = new PriorityQueue<>();

    for(int i = 0 ; i <= MAX - mList.size() ; i++){
      int sameCnt = 0;

      for(int j = 0 ; j < mList.size() ; j++){
        if(musicStream[i + j] != mList.get(j))
          break;

        sameCnt++;

        if(sameCnt == mList.size()){
          Music m = musicMap.get(musicIndexStream[i]);
          Music m2 = musicMap.get(musicIndexStream[i + j]);

          // 이 경우 m이 두 곡 사이에 걸쳐있는 경우다.
          // 두 곡에 걸쳐서 m이 매칭되는건 실패한 것으로 간주한다.
          if(m != m2)
            break;

          if(!pq.contains(m))
            pq.add(m);
          break;
        }
      }
    }

    if(pq.isEmpty())
      return "(None)";
    return pq.poll().name;
  }

  private List<Integer> stringStreamIntoIntStream(String stream){
    List<Integer> list = new ArrayList<>();

    for(int i = 0 ; i < stream.length() ; i++){
      char c = stream.charAt(i);
      if(c == '#')
        list.set(list.size() - 1, list.get(list.size() - 1) + 1);
      else
        list.add(stringCodeIntoInt(String.valueOf(c)));
    }

    return list;
  }

  private int stringTimeToInt(String time){
    StringTokenizer st = new StringTokenizer(time, ":");
    int hour = Integer.parseInt(st.nextToken()) * 60;
    int min = Integer.parseInt(st.nextToken());
    return hour + min;
  }

  private int stringCodeIntoInt(String code){
    for(int i = 0 ; i < codes.length ; i++){
      if(codes[i].equals(code))
        return i;
    }
    return -1;
  }

}

class Music implements Comparable<Music>{
  int index;
  int startTime;
  int endTime;
  int playLength;
  String name;

  public Music(int index, int startTime, int endTime, String name) {
    this.index = index;
    this.startTime = startTime;
    this.endTime = endTime;
    this.playLength = endTime - startTime;
    this.name = name;
  }

  @Override
  public int compareTo(Music o) {
    if(this.playLength == o.playLength)
      return this.index - o.index;

    return o.playLength - this.playLength;
  }
}
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// https://programmers.co.kr/learn/courses/30/lessons/42888
class Solution {
  public String[] solution(String[] record) {
    Queue<String[]> logs = new LinkedList<>();
    Map<String, String> uidNicknameMap = new HashMap<>();

    for(String r : record){
      StringTokenizer st = new StringTokenizer(r);
      String command = st.nextToken();
      String uid = st.nextToken();

      if(command.equals("Leave")){
        logs.add(new String[] {uid, command});
        continue;
      }

      String nickname = st.nextToken();
      uidNicknameMap.put(uid, nickname);

      if(command.equals("Enter")){
        logs.add(new String[] {uid, command});
      }
    }

    String[] result = new String[logs.size()];
    for(int i = 0 ; !logs.isEmpty() ; i++){
      String[] log = logs.poll();
      String uid = log[0];
      String command = log[1];

      String msg = command.equals("Enter") ? "님이 들어왔습니다." : "님이 나갔습니다.";
      String line = uidNicknameMap.get(uid) + msg;
      result[i] = line;
    }

    return result;
  }
}
import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int width = Integer.parseInt(st.nextToken());
    int height = Integer.parseInt(st.nextToken());

    List<Integer> xList = new ArrayList<>();
    List<Integer> yList = new ArrayList<>();

    int N = Integer.parseInt(br.readLine());
    for(int i = 0 ; i < N ; i++){
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int pos = Integer.parseInt(st.nextToken());

      if(type == 0)
        yList.add(pos);
      else
        xList.add(pos);
    }
    xList.add(width);
    yList.add(height);

    xList.sort(Integer::compareTo);
    yList.sort(Integer::compareTo);

    int max = 0;
    int[] prevPos = {0, 0};
    for(int y : yList){
      for(int x : xList){
        int area = (x - prevPos[0]) * (y - prevPos[1]);
        max = Math.max(max, area);
        prevPos[0] = x;
      }
      prevPos[0] = 0;
      prevPos[1] = y;
    }

    System.out.println(max);
  }
}
import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2304
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    List<int[]> pillars = new ArrayList<>();
    for(int i = 0 ; i < N ; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int height = Integer.parseInt(st.nextToken());
      pillars.add(new int[]{x, height});
    }

    pillars.sort((o1, o2) -> o1[0] - o2[0]);

    int area = 0;
    for(int i = 0 ; i < pillars.size() - 1; i++){
      int[] current = pillars.get(i);

      boolean higherExists = false;
      int[] maximum = new int[2];
      int maximumIdx = -1;

      for(int j = i + 1 ; j < pillars.size() ; j++){
        int[] next = pillars.get(j);

        // 높이가 같거나 높으면 면적 누적
        if(current[1] <= next[1]){
          area += current[1];       // 현재 기둥 면적 누적
          area += current[1] * (next[0] - current[0] - 1);    // 다음 기둥까지의 면적(다음 기둥 자체 면적은 제외) 누적
          higherExists = true;
          i = j - 1;              // 커서를 다음 기둥으로 변경 (-1 넣은 이유는 for문 돌때 ++되므로)
          break;
        }

        // 다음 기둥들 중 최대 높이값 탐색
        if(maximum[1] < next[1]){
          maximum[0] = next[0];
          maximum[1] = next[1];
          maximumIdx = j;
        }
      }

      // 현재 기둥보다 높은 기둥이 없으면
      if(!higherExists){
        area += current[1];   // 현재 기둥 면적 누적
        area += maximum[1] * (maximum[0] - current[0] - 1);   // 다음으로 가장 높은 기둥까지의 면적 누적
        i = maximumIdx - 1;   // 커서를 다음 기둥으로 변경
      }
    }

    // 마지막 기둥 면적 누적
    area += pillars.get(pillars.size() - 1)[1];

    System.out.println(area);
  }

}
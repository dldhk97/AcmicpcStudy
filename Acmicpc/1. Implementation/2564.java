import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2564
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int width = Integer.parseInt(st.nextToken());
    int height = Integer.parseInt(st.nextToken());
    int shopCnt = Integer.parseInt(br.readLine());

    List<Integer> shopAbsolutePosList = new ArrayList<>();
    int manAbsolutePos = -1;
    for(int i = 0 ; i < shopCnt + 1 ; i++){
      st = new StringTokenizer(br.readLine());
      int dir = Integer.parseInt(st.nextToken());
      int pos = Integer.parseInt(st.nextToken());

      int absolutePos = getAbsolutePos(dir, pos, width, height);

      if(i == shopCnt)
        manAbsolutePos = absolutePos;
      else
        shopAbsolutePosList.add(absolutePos);
    }

    int sum = 0;
    for(int shopAbsolutePos : shopAbsolutePosList)
      sum += getRelativePos(shopAbsolutePos, manAbsolutePos, (width + height) * 2);

    System.out.println(sum);

  }

  private static int getAbsolutePos(int dir, int pos, int width, int height){
    if(dir == 1)
      return pos;
    if(dir == 2)
      return width + height + (width - pos);
    if(dir == 3)
      return width + height + width + (height - pos);
    return width + pos;
  }

  private static int getRelativePos(int shopAbsolutePos, int manAbsolutePos, int posEnd){
    int lower = Math.min(shopAbsolutePos, manAbsolutePos);
    int higher = Math.max(shopAbsolutePos, manAbsolutePos);

    int posDiff1 = lower + posEnd - higher;
    int posDiff2 = higher - lower;

    return Math.min(posDiff1, posDiff2);
  }
}
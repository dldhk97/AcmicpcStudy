import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17136
public class Main {

  private final static int N = 10;
  private static int leftFields = 0;
  private static int MIN_USE = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    int[][] map = readMap();

    if(leftFields <= 3){
      System.out.println(leftFields);
      return;
    }

    backtracking(map, new int[]{5, 5, 5, 5, 5}, leftFields, 0);

    if (MIN_USE == Integer.MAX_VALUE)
      System.out.println(-1);
    else
      System.out.println(MIN_USE);
  }

  private static int[][] readMap() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[][] map = new int[N][N];

    for(int y = 0 ; y < N ; y++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < N ; x++){
        map[y][x] = Integer.parseInt(st.nextToken());
        if(map[y][x] == 1)
          leftFields++;
      }
    }

    return map;
  }

  private static void backtracking(int[][] map, int[] leftPapers, int leftFields, int usedPaperCnt){
    if(MIN_USE <= usedPaperCnt)
      return;

    if(leftFields == 0){
      MIN_USE = Math.min(MIN_USE, usedPaperCnt);
      return;
    }

    if(isLeftPapersEmpty(leftPapers))
      return;

    Optional<int[]> nextField = findNextField(map);
    if(nextField.isEmpty())
      return;

    int x = nextField.get()[0];
    int y = nextField.get()[1];

    for(int paperSize = 5 ; paperSize > 0 ; paperSize--){
      if(leftPapers[paperSize - 1] <= 0)
        continue;
      if(!canCoverFields(map, x, y, paperSize))
        continue;

      leftPapers[paperSize - 1]--;
      coverFields(map, x, y, paperSize, 0);

      int newLeftFields = leftFields - (paperSize * paperSize);

      backtracking(map, leftPapers, newLeftFields, usedPaperCnt + 1);

      leftPapers[paperSize - 1]++;
      coverFields(map, x, y, paperSize, 1);
    }

  }

  private static Optional<int[]> findNextField(int[][] map){
    for(int y = 0 ; y < map.length ; y++){
      for(int x = 0 ; x < map[0].length ; x++){
        if(map[y][x] == 1)
          return Optional.of(new int[]{x, y});
      }
    }

    return Optional.empty();
  }

  private static boolean isLeftPapersEmpty(int[] leftPapers){
    for(int leftPaperCnt : leftPapers){
      if(leftPaperCnt > 0)
        return false;
    }
    return true;
  }

  private static boolean canCoverFields(int[][] map, int startX, int startY, int paperSize){
    if(startX + paperSize > N || startY + paperSize > N)
      return false;

    for(int y = startY ; y < startY + paperSize && y < N  ; y++){
      for(int x = startX ; x < startX + paperSize && x < N ; x++){
        if(map[y][x] == 0)
          return false;
      }
    }
    return true;
  }

  private static void coverFields(int[][] map, int startX, int startY, int paperSize, int value){
    for(int y = startY ; y < startY + paperSize && y < N  ; y++){
      for(int x = startX ; x < startX + paperSize && x < N ; x++){
        map[y][x] = value;
      }
    }
  }
}
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

    backtracking(map, new int[]{5, 5, 5, 5, 5}, leftFields, 0, 5);

    if (MIN_USE == Integer.MAX_VALUE)
      System.out.println(-1);
    else
      System.out.println(MIN_USE);

    System.out.println();
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

  private static void backtracking(int[][] map, int[] leftPapers, int leftFields, int usedPapers, int paperSize){
    if(usedPapers >= MIN_USE)
      return;

    if(isLeftPapersEmpty(leftPapers) || leftFields <= 0){
      if(leftFields <= 0)
        MIN_USE = Math.min(MIN_USE, usedPapers);
      return;
    }

    for(int size = paperSize ; size > 0 ; size--){
      if(leftPapers[size - 1] <= 0)
        continue;

      Queue<int[]> coverableFields = getCoverableFields(map, size);
      if(coverableFields.size() <= 0)
        continue;

      while(!coverableFields.isEmpty()){
        int[] polled = coverableFields.poll();
        int x = polled[0]; int y = polled[1];

        leftPapers[size - 1]--;
        coverFields(map, x, y, size, 0);

        int newLeftFields = leftFields - (size * size);

        backtracking(map, leftPapers, newLeftFields, usedPapers + 1, size);

        coverFields(map, x, y, size, 1);
        leftPapers[size - 1]++;
      }
    }
  }

  private static Queue<int[]> getCoverableFields(int[][] map, int paperSize){
    Queue<int[]> fieldStartPoints = new ArrayDeque<>();

    for(int startY = 0 ; startY < N ; startY++){
      for(int startX = 0 ; startX < N ; startX++){
        if(map[startY][startX] == 0)
          continue;

        if(startY + paperSize > N || startX + paperSize > N)
          continue;

        boolean canCover = true;
        for(int y = startY ; y < startY + paperSize ; y++){
          for(int x = startX ; x < startX + paperSize ; x++){
            if(map[y][x] == 0) {
              y = y + paperSize;
              canCover = false;
              break;
            }
          }
        }
        if(canCover)
          fieldStartPoints.add(new int[]{startX, startY});
      }
    }

    return fieldStartPoints;
  }

  private static void coverFields(int[][] map, int startX, int startY, int paperSize, int value){
    for(int y = startY ; y < startY + paperSize && y < N  ; y++){
      for(int x = startX ; x < startX + paperSize && x < N ; x++){
        map[y][x] = value;
      }
    }
  }

  private static boolean isLeftPapersEmpty(int[] leftPapers){
    for(int leftPaperCnt : leftPapers){
      if(leftPaperCnt > 0)
        return false;
    }
    return true;
  }
}
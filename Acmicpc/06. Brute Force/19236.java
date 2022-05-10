import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/19236
public class Main {

  private static final int[] dX = {0, -1, -1, -1, 0, 1, 1, 1};
  private static final int[] dY = {-1, -1, 0, 1, 1, 1, 0, -1};
  private static int EAT_MAX = 1;

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[][] fishMap = new int[4][4];
    List<Fish> fishList = new ArrayList<>();

    for(int y = 0 ; y < 4 ; y++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < 4 ; x++){
        int index = Integer.parseInt(st.nextToken()) - 1;
        int direction = Integer.parseInt(st.nextToken()) - 1;

        Fish fish = new Fish(index, x, y, direction);
        fishList.add(fish);
        fishMap[y][x] = index;
      }
    }

    fishList.sort(Fish::compareTo);

    dfs(new int[]{0, 0}, fishList, fishMap, 0);

    System.out.println(EAT_MAX);
  }

  private static void dfs(int[] sharkPos, List<Fish> fishList, int[][] fishMap, int eatSum){
    int sharkX = sharkPos[0]; int sharkY = sharkPos[1];
    Fish sharkPosFish = fishList.get(fishMap[sharkY][sharkX]);
    int sharkDir = sharkPosFish.direction;

    int curEatSum = eatSum;

    if(sharkPosFish.isAlive){
      sharkPosFish.isAlive = false;
      curEatSum += sharkPosFish.index + 1;
    }
    EAT_MAX = Math.max(EAT_MAX, curEatSum);

    // fish moves
    for(int i = 0 ; i < fishList.size() ; i++){
      Fish fish = fishList.get(i);
      if(!fish.isAlive)
        continue;

      for(int d = 0 ; d < 8 ; d++){
        int direction = (fish.direction + d) % 8;
        int nX = fish.x + dX[direction];
        int nY = fish.y + dY[direction];
        if(!isInBoundary(nX, nY, fishMap[0].length, fishMap.length) || (sharkX == nX && sharkY == nY))
          continue;

        swapFish(fish, fishList.get(fishMap[nY][nX]), fishMap);
        fish.direction = direction;
        break;
      }
    }

    // shark moves
    boolean sharkCantMove = true;
    for(int i = 1 ; i <= 3 ; i++){
      int nX = sharkX + dX[sharkDir] * i;
      int nY = sharkY + dY[sharkDir] * i;

      if(!isInBoundary(nX, nY, fishMap[0].length, fishMap.length) || !fishList.get(fishMap[nY][nX]).isAlive)
        continue;

      List<Fish> newFishList = copyFishList(fishList);
      int[][] newFishMap = copyFishMap(fishMap);

      dfs(new int[]{nX, nY}, newFishList, newFishMap, curEatSum);

      sharkCantMove = false;
    }

    // go home
    if(sharkCantMove){
      EAT_MAX = Math.max(EAT_MAX, curEatSum);
    }
  }

  private static boolean isInBoundary(int x, int y, int xBound, int yBound){
    return 0 <= x && x < xBound && 0 <= y && y < yBound;
  }

  private static List<Fish> copyFishList(List<Fish> fishList){
    List<Fish> copied = new ArrayList<>();
    for(Fish f : fishList)
      copied.add(new Fish(f));
    return copied;
  }

  private static int[][] copyFishMap(int[][] fishMap){
    int[][] newFishMap = new int[4][4];
    for(int t = 0 ; t < 4 ; t++)
      newFishMap[t] = Arrays.copyOf(fishMap[t], 4);
    return newFishMap;
  }

  private static void swapFish(Fish fish1, Fish fish2, int[][] fishMap){
    int tempX = fish1.x;
    int tempY = fish1.y;
    fish1.x = fish2.x;
    fish1.y = fish2.y;
    fish2.x = tempX;
    fish2.y = tempY;

    fishMap[fish1.y][fish1.x] = fish1.index;
    fishMap[fish2.y][fish2.x] = fish2.index;
  }

}

class Fish implements Comparable<Fish>{
  int index;
  int x, y;
  int direction;
  boolean isAlive = true;

  public Fish(int index, int x, int y, int direction) {
    this.index = index;
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public Fish(Fish f) {
    this.index = f.index;
    this.x = f.x;
    this.y = f.y;
    this.direction = f.direction;
    this.isAlive = f.isAlive;
  }

  @Override
  public int compareTo(Fish o) {
    return this.index - o.index;
  }
}
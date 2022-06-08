import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17140
public class Main {

  private static final int INIT_ARR_SIZE = 3;
  private static final int MAX_ARR_SIZE = 100;

  private static int ROW_SIZE = INIT_ARR_SIZE;
  private static int COLUMN_SIZE = INIT_ARR_SIZE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int r = Integer.parseInt(st.nextToken()) - 1;
    int c = Integer.parseInt(st.nextToken()) - 1;
    int k = Integer.parseInt(st.nextToken());

    int[][] map = readMap(br);

    int time = 0;
    while(map[r][c] != k){

      if(time > 100){
        time = -1;
        break;
      }

      if(ROW_SIZE >= COLUMN_SIZE)
        rowSort(map);
      else
        columnSort(map);

      time++;
    }

    System.out.println(time);
  }

  private static int[][] readMap(BufferedReader br) throws IOException{
    int[][] map = new int[MAX_ARR_SIZE][MAX_ARR_SIZE];

    for(int y = 0 ; y < INIT_ARR_SIZE ; y++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      for(int x = 0 ; x < INIT_ARR_SIZE ; x++)
        map[y][x] = Integer.parseInt(st.nextToken());
    }

    return map;
  }

  private static void rowSort(int[][] map){
    int maxColumnSize = 0;

    for(int y = 0 ; y < ROW_SIZE ; y++){
      int[] sorted = arrSort(map[y]);
      maxColumnSize = Math.max(maxColumnSize, sorted.length);
      map[y] = Arrays.copyOf(sorted, MAX_ARR_SIZE);
    }

    COLUMN_SIZE = maxColumnSize;
  }

  private static void columnSort(int[][] map){
    int maxRowSize = 0;

    for(int x = 0 ; x < COLUMN_SIZE ; x++){
      int[] tempArr = new int[MAX_ARR_SIZE];

      for(int y = 0 ; y < map.length ; y++){
        tempArr[y] = map[y][x];
      }

      tempArr = arrSort(tempArr);
      maxRowSize = Math.max(maxRowSize, tempArr.length);

      for(int y = 0 ; y < map[x].length ; y++){
        if(y < tempArr.length)
          map[y][x] = tempArr[y];
        else
          map[y][x] = 0;
      }
    }

    ROW_SIZE = maxRowSize;
  }

  private static int[] arrSort(int[] arr){
    Map<Integer, Integer> map = new HashMap<>();

    for(int i : arr) {
      if(i == 0)
        continue;
      map.put(i, map.getOrDefault(i, 0) + 1);
    }

    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
      if(o1[1] != o2[1])
        return o1[1] - o2[1];
      return o1[0] - o2[0];
    });

    for(int i : map.keySet()){
      pq.add(new int[]{i, map.get(i)});
    }

    int[] newArr = new int[pq.size() * 2];
    int arrIndex = 0;

    while(!pq.isEmpty() && arrIndex <= newArr.length - 2){
      int[] polled = pq.poll();

      newArr[arrIndex] = polled[0];
      newArr[arrIndex + 1] = polled[1];

      arrIndex += 2;
    }

    return newArr;
  }
}
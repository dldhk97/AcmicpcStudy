import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/68645
class Solution {
  public int[] solution(int n) {
    int[][] arr = new int[n][n];

    fill(arr, 1, 0, 0, n);

    List<Integer> result = new ArrayList<>();

    for(int y = 0 ; y < arr.length ; y++){
      for(int x = 0 ; x < arr[0].length ; x++){
        if(arr[y][x] != 0)
          result.add(arr[y][x]);
      }
    }

    return result.stream().mapToInt(Integer::valueOf).toArray();
  }

  private void fill(int[][] arr, int startI, int startX, int startY, int startN){
    if(startN <= 0)
      return;

    int x = startX;
    int y = startY;
    int i = startI;
    int n = startN;

    while(n-- > 0){
      arr[y++][x] = i++;
    }

    x++;
    y--;
    n = startN - 1;
    while(n-- > 0){
      arr[y][x++] = i++;
    }

    x -= 2;
    y--;
    n = startN - 2;
    while(n-- > 0){
      arr[y--][x--] = i++;
    }

    fill(arr, i, startX + 1, startY + 2, startN - 3);
  }
}
import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/1832
class Solution {

  public int solution(int m, int n, int[][] cityMap) {
    int DIV = 20170805;
    long[][][] ways = initWays(cityMap);

    for(int y = 1 ; y < cityMap.length ; y++){
      for(int x = 1 ; x < cityMap[0].length ; x++){
        if(cityMap[y - 1][x] == 0){
          ways[y][x][1] = ((ways[y][x][1] + ways[y - 1][x][0]) % DIV + ways[y - 1][x][1]) % DIV;
        }
        if(cityMap[y - 1][x] == 2){
          ways[y][x][1] = (ways[y][x][1] + ways[y - 1][x][1]) % DIV;
        }
        if(cityMap[y][x - 1] == 0){
          ways[y][x][0] = ((ways[y][x][0] + ways[y][x - 1][0]) % DIV + ways[y][x - 1][1]) % DIV;
        }
        if(cityMap[y][x - 1] == 2){
          ways[y][x][0] = (ways[y][x][0] + ways[y][x - 1][0]) % DIV;
        }
      }
    }

    return (int)(ways[cityMap.length - 1][cityMap[0].length - 1][0] + ways[cityMap.length - 1][cityMap[0].length - 1][1]) % DIV;
  }

  private long[][][] initWays(int[][] cityMap){
    long[][][] ways = new long[cityMap.length][cityMap[0].length][2];

    for(int x = 0 ; x < cityMap[0].length ; x++){
      if(cityMap[0][x] == 1)
        break;
      ways[0][x][0] = 1;
    }

    for(int y = 0 ; y < cityMap.length ; y++){
      if(cityMap[y][0] == 1)
        break;
      ways[y][0][1] = 1;
    }

    return ways;
  }

}
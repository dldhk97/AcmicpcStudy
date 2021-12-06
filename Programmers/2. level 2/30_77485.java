// https://programmers.co.kr/learn/courses/30/lessons/77485
class Solution {
  public int[] solution(int rows, int columns, int[][] queries) {
    // make arr
    int index = 1;
    int[][] map = new int[columns][rows];
    for(int y = 0 ; y < rows ; y++){
      for(int x = 0 ; x < columns ; x++)
        map[x][y] = index++;
    }

    int[] answer = new int[queries.length];

    int ansIndex = 0;
    for(int[] query : queries){
      int min = Integer.MAX_VALUE;
      int x1 = query[1] - 1; int y1 = query[0] - 1;
      int x2 = query[3] - 1; int y2 = query[2] - 1;

      int x1y1Value = map[x1][y1];
      for(int y = y1 ; y < y2  ; y++){
        map[x1][y] = map[x1][y + 1];
        min = Math.min(min ,map[x1][y]);
      }

      for(int x = x1 ; x < x2 ; x++){
        map[x][y2] = map[x + 1][y2];
        min = Math.min(min ,map[x][y2]);
      }

      for(int y = y2 ; y > y1 ; y--){
        map[x2][y] = map[x2][y - 1];
        min = Math.min(min ,map[x2][y]);
      }

      for(int x = x2 ; x > x1 ; x--){
        map[x][y1] = map[x - 1][y1];
        min = Math.min(min ,map[x][y1]);
      }
      map[x1 + 1][y1] = x1y1Value;
      min = Math.min(min ,map[x1 + 1][y1]);

      answer[ansIndex++] = min;
    }

    return answer;
  }
}
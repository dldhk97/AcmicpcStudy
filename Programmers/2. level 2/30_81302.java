import java.util.Stack;

// https://programmers.co.kr/learn/courses/30/lessons/81302
class Solution {
  public int[] solution(String[][] places) {
    int[] result = new int[places.length];

    for(int i = 0 ; i < places.length ; i++){
      result[i] = check(places[i]);
    }

    return result;
  }

  private int check(String[] place){
    char[][] placeArr = convertPlaceAsArray(place);

    for(int i = 0 ; i < 5 ; i++){
      for(int j = 0 ; j < 5 ; j++){
        if(placeArr[i][j] != 'P')
          continue;

        if(!dfs(placeArr, new int[]{j, i})){
          return 0;
        }
      }
    }

    return 1;
  }

  private static final int[] directionX = {0, 0, -1, 1};
  private static final int[] directionY = {-1, 1, 0, 0};

  private boolean dfs(char[][] placeArr, int[] startPoint){
    Stack<int[]> stack = new Stack<>();
    stack.push(startPoint);

    boolean[][] isVisited = new boolean[5][5];
    while(!stack.isEmpty()){
      int[] popped = stack.pop();
      int x = popped[0]; int y = popped[1];

      if(isVisited[y][x])
        continue;

      isVisited[y][x] = true;
      if(placeArr[y][x] == 'P' && (x != startPoint[0] || y != startPoint[1])){
        if(getDistance(startPoint, popped) <= 2 && !checkAround(startPoint, popped, placeArr))
          return false;
      }

      for(int i = 0 ; i < 4 ; i++){
        int nextX = x + directionX[i];
        int nextY = y + directionY[i];
        if(nextX < 0 || nextX > 4 || nextY < 0 || nextY > 4)
          continue;
        if(placeArr[nextY][nextX] == 'X')
          continue;

        stack.push(new int[]{nextX, nextY});
      }
    }

    return true;
  }

  private int getDistance(int[] pointA, int[] pointB){
    return Math.abs(pointA[0] - pointB[0]) + Math.abs(pointA[1] - pointB[1]);
  }

  private boolean checkAround(int[] pointA, int[] pointB, char[][] placeArr){
    int yDiff = clipDiff(pointA[1] - pointB[1]);
    int xDiff = clipDiff(pointA[0] - pointB[0]);

    if(xDiff == 0)
      return placeArr[pointA[1] + yDiff][pointA[0]] == 'X';
    if(yDiff == 0)
      return placeArr[pointA[1]][pointA[0] + xDiff] == 'X';
    return placeArr[pointA[1] + yDiff][pointA[0]] == 'X' && placeArr[pointA[1]][pointA[0] + xDiff] == 'X';
  }

  private int clipDiff(int diff){
    if(diff > 0)
      return -1;
    else if(diff < 0)
      return 1;
    return 0;
  }

  private char[][] convertPlaceAsArray(String[] place){
    char[][] placeArr = new char[5][5];

    for(int i = 0 ; i < 5 ; i++)
      for(int j = 0 ; j < 5 ; j++)
        placeArr[i][j] = place[i].charAt(j);

    return placeArr;
  }

}
import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/60059
class Solution {

  public boolean solution(int[][] key, int[][] lock) {
    int[][] map = new int[60][60];
    for (int[] line : map)
      Arrays.fill(line, -1);

    int fillCount = 0;
    for(int y = 0 ; y < lock.length ; y++) {
      for (int x = 0; x < lock[0].length; x++) {
        map[20 + y][20 + x] = lock[y][x];
        if(lock[y][x] == 0)
          fillCount++;
      }
    }

    int[][] rotatedKey = key;
    for(int i = 0 ; i < 4 ; i++){
      for(int y = 0 ; y < rotatedKey.length + lock.length - 1 ; y++){
        for(int x = 0 ; x < rotatedKey[0].length + lock[0].length - 1 ; x++){
          int nX = 20 - rotatedKey[0].length + 1 + x;
          int nY = 20 - rotatedKey.length + 1 + y;
          if(validate(nX, nY, rotatedKey, map, fillCount))
            return true;
        }
      }
      rotatedKey = rotateKey(rotatedKey);
    }


    return false;
  }

  private boolean validate(int startX, int startY, int[][] key, int[][] map, int fillCount){
    int count = 0;
    for(int y = 0 ; y < key.length ; y++){
      for(int x = 0 ; x < key[0].length ; x++){
        int nX = startX + x;
        int nY = startY + y;
        if(map[nY][nX] == 0 && key[y][x] == 1){
          count++;
        }
        else if(map[nY][nX] == 1 && key[y][x] == 1) {
          return false;
        }
      }
    }
    return fillCount == count;
  }

  private int[][] rotateKey(int[][] key){
    int orgHeight = key.length;
    int orgWidth = key[0].length;
    int[][] rotatedKey = new int[orgWidth][orgHeight];

    for(int y = 0 ; y < orgWidth ; y++){
      for(int x = 0 ; x < orgHeight ; x++){
        rotatedKey[y][x] = key[orgHeight - 1 - x][y];
      }
    }

    return rotatedKey;
  }
}
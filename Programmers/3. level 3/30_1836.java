import java.util.*;
import java.util.stream.Collectors;

// https://programmers.co.kr/learn/courses/30/lessons/1836
class Solution {
  public String solution(int m, int n, String[] board) {
    char[][] charBoard = convertIntoArray(board);

    Map<Character, int[][]> tiles = findTiles(charBoard);

    List<Character> list = tiles.keySet().stream().sorted().collect(Collectors.toList());

    int index = 0;
    StringBuilder sb = new StringBuilder();

    while(!list.isEmpty()){
      char tile = list.get(index);
      int x1 = tiles.get(tile)[0][0]; int y1 = tiles.get(tile)[0][1];
      int x2 = tiles.get(tile)[1][0]; int y2 = tiles.get(tile)[1][1];

      if(canRemove(charBoard, tile, x1, y1, x2, y2)){
        list.remove(index);
        index = 0;
        sb.append(tile);
        charBoard[y1][x1] = '.';
        charBoard[y2][x2] = '.';
      }
      else{
        index++;
        if(index >= list.size())
          return "IMPOSSIBLE";
      }
    }

    return sb.toString();
  }

  private boolean canRemove(char[][] charBoard, char tile, int x1, int y1, int x2, int y2){
    if(x2 < x1){
      int temp = x2;
      x2 = x1;
      x1 = temp;

      temp = y2;
      y2 = y1;
      y1 = temp;
    }
    if(y1 < y2){
      if(checkX(charBoard, tile, x1, x2, y1) && checkY(charBoard, tile, y1, y2, x2)){
        return true;
      }
      if(checkX(charBoard, tile, x1, x2, y2) && checkY(charBoard, tile, y1, y2, x1)){
        return true;
      }
    }
    else{
      if(checkX(charBoard, tile, x1, x2, y1) && checkY(charBoard, tile, y2, y1, x2)){
        return true;
      }
      if(checkX(charBoard, tile, x1, x2, y2) && checkY(charBoard, tile, y2, y1, x1)){
        return true;
      }
    }
    return false;
  }

  private boolean checkX(char[][] charBoard, char tile, int x1, int x2, int y){
    for(int i = x1 ; i <= x2 ; i++){
      if(charBoard[y][i] != '.' && charBoard[y][i] != tile)
        return false;
    }
    return true;
  }

  private boolean checkY(char[][] charBoard, char tile, int y1, int y2, int x){
    for(int i = y1 ; i <= y2 ; i++){
      if(charBoard[i][x] != '.' && charBoard[i][x] != tile)
        return false;
    }
    return true;
  }

  private char[][] convertIntoArray(String[] board){
    char[][] result = new char[board.length][board[0].length()];
    for(int y = 0 ; y < board.length ; y++){
      for(int x = 0; x < board[0].length() ; x++){
        result[y][x] = board[y].charAt(x);
      }
    }
    return result;
  }

  private Map<Character, int[][]> findTiles(char[][] board){
    Map<Character, int[][]> tiles = new HashMap<>();

    for(int y = 0 ; y < board.length ; y++){
      for(int x = 0; x < board[0].length ; x++){
        char tile = board[y][x];
        if(tile == '.' || tile == '*')
          continue;
        if(!tiles.containsKey(tile)){
          tiles.put(tile, new int[2][2]);
          tiles.get(tile)[0][0] = x;
          tiles.get(tile)[0][1] = y;
        }
        else{
          tiles.get(tile)[1][0] = x;
          tiles.get(tile)[1][1] = y;
        }
      }
    }

    return tiles;
  }
}
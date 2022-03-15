// https://programmers.co.kr/learn/courses/30/lessons/49191
class Solution {
  public int solution(int n, int[][] results) {
    boolean[][] visited = new boolean[n][n];

    for(int y = 0 ; y < results.length ; y++){
      int winner = results[y][0] - 1;
      int loser = results[y][1] - 1;

      visited[winner][loser] = true;
    }

    for(int mid = 0 ; mid < n ; mid++){
      for(int from = 0 ; from < n ; from++){
        for(int to = 0 ; to < n ; to++){
          if(visited[from][mid] && visited[mid][to])
            visited[from][to] = true;
        }
      }
    }

    int answer = 0;
    for(int y = 0 ; y < n ; y++){

      int connected = 0;
      for(int x = 0 ; x < n ; x++){
        if(visited[y][x] || visited[x][y])
          connected++;
      }

      if(connected == n - 1)
        answer++;
    }

    return answer;
  }
}
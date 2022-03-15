import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/43162
class Solution {
  static int[] visited;
  static int networkIndex;

  public int solution(int n, int[][] computers) {
    visited = new int[n];
    Arrays.fill(visited, -1);
    networkIndex = 0;

    for(int y = 0 ; y < n ; y++){
      if(visited[y] != -1)
        continue;
      dfs(y, computers);
    }

    return networkIndex;
  }

  private void dfs(int i, int[][] computers){
    Stack<Integer> stack = new Stack<>();
    stack.push(i);

    while(!stack.isEmpty()){
      int popped = stack.pop();

      if(visited[popped] > 0)
        continue;

      visited[popped] = networkIndex;

      for(int y = 0 ; y < computers.length ; y++){
        if(visited[y] != -1)
          continue;
        if(computers[popped][y] == 1)
          stack.push(y);
      }
    }

    networkIndex++;
  }
}
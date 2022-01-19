import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://programmers.co.kr/learn/courses/30/lessons/49189
class Solution {
  public int solution(int n, int[][] edge) {
    List<List<Integer>> graph = new ArrayList<>();
    for(int i = 0 ; i < n ; i++)
      graph.add(new ArrayList<>());

    for(int[] e : edge){
      int from = e[0] - 1;
      int to = e[1] - 1;

      graph.get(from).add(to);
      graph.get(to).add(from);
    }

    Queue<Integer> queue = new LinkedList<>();

    int[] distances = new int[n];
    Arrays.fill(distances, Integer.MAX_VALUE);

    queue.add(0);
    distances[0] = 0;

    while(!queue.isEmpty()){
      int polled = queue.poll();

      for(int i : graph.get(polled)){
        if(distances[i] == Integer.MAX_VALUE){
          distances[i] = distances[polled] + 1;
          queue.add(i);
        }
      }
    }

    int cnt = 0;
    int max = Arrays.stream(distances).max().getAsInt();
    for(int i : distances){
      if(i == max)
        cnt++;
    }

    return cnt;
  }
}
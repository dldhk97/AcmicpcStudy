import java.util.Arrays;
import java.util.PriorityQueue;

// https://programmers.co.kr/learn/courses/30/lessons/12978
class Solution {
  int[][] EDGES;
  static int MAX = 1000000;

  public int solution(int N, int[][] road, int K) {
    EDGES = createEdges(N, road);

    int[] dist = new int[N];
    Arrays.fill(dist, MAX);

    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
    pq.add(new int[]{0, 0});
    dist[0] = 0;

    while(!pq.isEmpty()){
      int[] polled = pq.poll();
      int cost = polled[0]; int node = polled[1];

      for(int next = 0 ; next < N ; next++){
        if(next == node)
          continue;

        int nextCost = cost + EDGES[node][next];
        if(nextCost < dist[next]){
          dist[next] = nextCost;
          pq.add(new int[]{nextCost, next});
        }
      }
    }

    int result = 0;
    for(int i = 0 ; i < N ; i++){
      if(dist[i] <= K)
        result++;
    }

    return result;
  }

  private int[][] createEdges(int N, int[][] road){
    int[][] edges = new int[N][N];
    for (int[] d : edges)
      Arrays.fill(d, MAX);

    for(int i = 0 ; i < N ; i++)
      edges[i][i] = 0;

    for(int[] r : road){
      int from = r[0] - 1;
      int to = r[1] - 1;
      int dist = r[2];

      edges[from][to] = Math.min(edges[from][to], dist);
      edges[to][from] = Math.min(edges[to][from], dist);
    }

    return edges;
  }
}
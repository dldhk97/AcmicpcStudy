import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/72413
class Solution {
  private static final int INF = 100000000;

  public int solution(int n, int s, int a, int b, int[][] fares) {
    int[][] edges = new int[n][];
    for(int i = 0 ; i < n ; i++){
      edges[i] = new int[n];
      Arrays.fill(edges[i], INF);
    }

    for(int[] fare : fares){
      int org = fare[0] - 1;
      int dest = fare[1] - 1;
      int cost = fare[2];
      edges[org][dest] = cost;
      edges[dest][org] = cost;
    }

    s--; a--; b--;

    int[][] costs = floydWarshall(edges);

    int min = INF;
    for(int i = 0 ; i < costs.length ; i++){
      int iToA = costs[i][a];
      int iToB = costs[i][b];
      int sToI = costs[s][i];
      int sum = iToA + iToB + sToI;

      min = Math.min(min, sum);
    }

    return min;
  }

  private int[][] floydWarshall(int[][] edges){
    int[][] costs = new int[edges.length][];

    for(int i = 0 ; i < edges.length ; i++){
      costs[i] = new int[edges.length];
      for(int j = 0 ; j < edges.length ; j++){
        if(i == j) continue;
        costs[i][j] = edges[i][j];
      }
    }

    for(int mid = 0 ; mid < edges.length ; mid++){
      for(int org = 0 ; org < edges.length ; org++){
        for(int dest = 0 ; dest < edges.length ; dest++){
          int sum = Math.min(costs[org][mid] + costs[mid][dest], INF);
          costs[org][dest] = Math.min(costs[org][dest], sum);
        }
      }
    }

    return costs;
  }
}
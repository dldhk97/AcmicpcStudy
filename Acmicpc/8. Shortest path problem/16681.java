import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/16681
public class Main {

  static long INF = 99999999999999L;
  static long MINUS_INF = -99999999999999L;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] heights = new int[N];
    for(int i = 0 ; i < N ; i++)
      heights[i] = Integer.parseInt(st.nextToken());

    List<List<int[]>> edges = new ArrayList<>();
    for(int i = 0 ; i < N ; i++)
      edges.add(new ArrayList<>());

    for(int i = 0 ; i < M ; i++){
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1;
      int b = Integer.parseInt(st.nextToken()) - 1;
      int n = Integer.parseInt(st.nextToken());

      if(heights[a] < heights[b])
        edges.get(a).add(new int[]{b, n});
      else if(heights[b] < heights[a])
        edges.get(b).add(new int[]{a, n});
    }

    System.out.println(solve(D, E, heights, edges));
  }

  private static String solve(int D, int E, int[] heights, List<List<int[]>> edges){
    long[] startCost = dijkstra(0, heights, edges);
    long[] returnCost = dijkstra(edges.size() - 1, heights, edges);

    long result = MINUS_INF;
    for(int i = 1 ; i < heights.length - 1 ; i++){
      long cost = startCost[i] + returnCost[i];
      result = Math.max(result, (long) E * heights[i] - cost * D);
    }

    if(result != MINUS_INF)
      return String.valueOf(result);
    return "Impossible";
  }

  private static long[] dijkstra(int startIndex, int[] heights, List<List<int[]>> edges){
    long[] costs = new long[heights.length];
    Arrays.fill(costs, INF);
    costs[startIndex] = 0;

    PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> (int) (o1[1] - o2[1]));
    pq.add(new long[]{startIndex, 0});

    while(!pq.isEmpty()){
      long[] polled = pq.poll();
      int index = (int)polled[0]; long cost = polled[1];

      if(cost > costs[index])
        continue;

      for(int[] edge : edges.get(index)){
        int nextIndex = edge[0]; int dist = edge[1];

        if(heights[index] >= heights[nextIndex])
          continue;

        if(costs[nextIndex] > costs[index] + dist) {
          costs[nextIndex] = costs[index] + dist;
          pq.add(new long[]{nextIndex, costs[index] + dist});
        }
      }
    }
    return costs;
  }

}

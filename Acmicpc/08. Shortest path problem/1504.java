import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1504
public class Main {
  private static int[][] costs;
  private static final int MAX = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());

    costs = new int[N][N];
    for(int[] arr : costs)
      Arrays.fill(arr, MAX);

    Map<Integer, Node> nodes = new HashMap<>();
    for (int i = 0 ; i < N ; i++) {
      nodes.put(i, new Node(i));
    }

    for(int i = 0 ; i < E ; i++){
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1;
      int b = Integer.parseInt(st.nextToken()) - 1;
      int cost = Integer.parseInt(st.nextToken());

      nodes.get(a).addEdge(b, cost);
      nodes.get(b).addEdge(a, cost);
    }

    st = new StringTokenizer(br.readLine());
    int v1 = Integer.parseInt(st.nextToken()) - 1;
    int v2 = Integer.parseInt(st.nextToken()) - 1;

    // s -> v1 -> v2 -> e
    int cost1 = calcCost(nodes, 0, v1, v2, N - 1);
    // s -> v2 -> v1 -> e
    int cost2 = calcCost(nodes, 0, v2, v1, N - 1);

    int minCost;
    if(cost1 != -1 && cost2 != -1)
      minCost = Math.min(cost1, cost2);
    else if(cost1 != -1)
      minCost = cost1;
    else
      minCost = cost2;

    System.out.println(minCost);
  }

  private static int calcCost(Map<Integer, Node> nodes, int start, int v1, int v2, int end){
    int v1Cost = dijkstra(nodes, start, v1);
    int v2Cost = dijkstra(nodes, v1, v2);
    int v3Cost = dijkstra(nodes, v2, end);

    if(v1Cost == -1 || v2Cost == -1 || v3Cost == -1)
      return -1;
    return v1Cost + v2Cost + v3Cost;
  }

  private static int dijkstra(Map<Integer, Node> nodes, int start, int end){
    if(costs[start][end] != MAX)
      return costs[start][end];

    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
    pq.add(new int[]{start, 0});
    boolean[] visited = new boolean[nodes.size()];

    while(!pq.isEmpty()){
      int[] polled = pq.poll();
      int idx = polled[0]; int cost = polled[1];

      if(visited[idx])
        continue;
      visited[idx] = true;

      if(idx == end){
        costs[end][start] = Math.min(costs[end][start], cost);
        return costs[start][end] = Math.min(costs[start][end], cost);
      }

      for(int adj : nodes.get(idx).edges.keySet()){
        if(visited[adj])
          continue;
        pq.add(new int[]{adj, cost + nodes.get(idx).edges.get(adj)});
      }
    }
    costs[end][start] = -1;
    return costs[start][end] = -1;
  }

  private static class Node{
    int index;
    Map<Integer, Integer> edges = new HashMap<>();

    public Node(int index) {
      this.index = index;
    }

    public void addEdge(int to, int cost){
      int newCost = Math.min(edges.getOrDefault(to, 1001), cost);
      edges.put(to, newCost);
    }
  }
}

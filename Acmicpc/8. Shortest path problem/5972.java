import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/5972
public class Main {

  private static final long MAX = 9999999999L;

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    List<Warehouse> warehouses = new ArrayList<>();
    for(int i = 0 ; i < N ; i++)
      warehouses.add(new Warehouse(i));

    for(int i = 0 ; i < M ; i++){
      st = new StringTokenizer(br.readLine());
      Warehouse from = warehouses.get(Integer.parseInt(st.nextToken()) - 1);
      Warehouse to = warehouses.get(Integer.parseInt(st.nextToken()) - 1);
      int cost = Integer.parseInt(st.nextToken());

      from.addAdjacent(to, cost);
      to.addAdjacent(from, cost);
    }

    long result = dijkstra(warehouses, 0, N - 1);

    System.out.println(result);
  }

  private static long dijkstra(List<Warehouse> warehouses, int start, int end){
    PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> (int)(o1[1] - o2[1]));

    pq.add(new long[]{start, 0});

    long[] costs = new long[warehouses.size()];
    Arrays.fill(costs, MAX);

    while(!pq.isEmpty()){
      long[] polled = pq.poll();
      int index = (int)polled[0]; long cost = polled[1];

      if(costs[index] < cost)
        continue;
      costs[index] = cost;

      if(index == end)
        return cost;

      Warehouse cur = warehouses.get(index);

      for(Warehouse adj : cur.adjacent.keySet()){
        long nextCost = cost + cur.adjacent.get(adj);
        if(costs[adj.index] <= nextCost)
          continue;
        pq.add(new long[]{adj.index, nextCost});
      }
    }
    return -1;
  }
}

class Warehouse{
  int index;
  HashMap<Warehouse, Long> adjacent = new HashMap<>();

  public Warehouse(int index) {
    this.index = index;
  }

  public void addAdjacent(Warehouse to, long cost){
    if(adjacent.containsKey(to))
      adjacent.put(to, Math.min(adjacent.get(to), cost));
    else
      adjacent.put(to, cost);
  }
}

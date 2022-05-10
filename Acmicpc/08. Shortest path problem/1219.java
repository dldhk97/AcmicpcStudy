import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1219
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Map<Integer, City> cities =  new HashMap<>();
    for(int i = 0 ; i < N ; i++)
      cities.put(i, new City(i));

    for(int i = 0 ; i < M ; i++){
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      cities.get(start).addEdge(end, cost);
    }

    st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < N ; i++)
      cities.get(i).earns = Integer.parseInt(st.nextToken());

    // 처음부터 도착점에 갈 수 없는지 검사
    if (!canVisit(S, E, cities))
      System.out.println("gg");
    else
      System.out.println(SPFA(S, E, cities));
  }

  private static String SPFA(int start, int end, Map<Integer, City> cities){
    Queue<City> queue = new ArrayDeque<>();
    boolean[] inQ = new boolean[cities.size()];   // 이미 queue에 들어있는지 구분
    int[] visited = new int[cities.size()];
    long[] earned = new long[cities.size()];
    Arrays.fill(earned, Long.MIN_VALUE);
    earned[start] = cities.get(start).earns;

    queue.add(cities.get(start));

    while(!queue.isEmpty()){
      City polled = queue.poll();
      inQ[polled.index] = false;
      visited[polled.index]++;

      for(int adj : polled.edges.keySet()){
        int edgeCost = polled.edges.get(adj);

        // 기록된 다음 노드까지 가는데 비용 < 현재 노드까지 오는데 비용 - 간선 비용 + 다음 노드의 수익
        if(earned[adj] < earned[polled.index] - edgeCost + cities.get(adj).earns){
          earned[adj] = earned[polled.index] - edgeCost + cities.get(adj).earns;    // 갱신

          // 큐이 없을때만 추가
          if(!inQ[adj]){
            inQ[adj] = true;
            visited[adj]++;
            // 노드 수만큼 같은 노드를 방문을 했다 = 사이클 발생
            if(visited[adj] >= cities.size()){
              // 사이클에서 도착점에 갈 수 있다면? 돈을 무한으로 벌고 도착 가능
              if(canVisit(adj, end, cities))
                return "Gee";
            }
            else{
              // 사이클이 아닌 경우에만 큐에 추가
              queue.add(cities.get(adj));
            }
          }
        }
      }
    }
    return String.valueOf(earned[end]);
  }

  // bfs로 start에서 end로 갈 수 있는지 검사
  private static boolean canVisit(int start, int end, Map<Integer, City> cities){
    Queue<City> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[cities.size()];

    queue.add(cities.get(start));

    while(!queue.isEmpty()){
      City polled = queue.poll();

      if(visited[polled.index])
        continue;
      visited[polled.index] = true;
      if(polled.index == end)
        return true;

      for(int adj : polled.edges.keySet()){
        if(!visited[adj])
          queue.add(cities.get(adj));
      }
    }

    return false;
  }
}

class City{
  int index;
  Map<Integer, Integer> edges = new HashMap<>();
  int earns;

  public City(int index) {
    this.index = index;
  }

  public void addEdge(int end, int cost){
    if(!edges.containsKey(end)){
      edges.put(end, cost);
      return;
    }

    edges.put(end, Math.min(edges.get(end), cost));
  }
}

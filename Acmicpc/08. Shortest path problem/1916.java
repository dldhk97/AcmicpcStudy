import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1916
public class Main {

    static final long MAX = 99999999999L;
    static int N, M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        List<City> cities = new ArrayList<>();
        for(int i = 0 ; i < N ; i++)
            cities.add(new City(i));

        StringTokenizer st;
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            long cost = Integer.parseInt(st.nextToken());

            cities.get(from).adjacent.add(new long[]{to, cost});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        long result = dijkstra(start, end, cities);
        System.out.println(result);
    }

    private static long dijkstra(int start, int end, List<City> cities){
        long[] costs = new long[N];
        Arrays.fill(costs, MAX);

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> (int) (o1[1] - o2[1]));
        pq.add(new long[]{start, 0});

        while(!pq.isEmpty()){
            long[] polled = pq.poll();
            int index = (int)polled[0];
            long cost = polled[1];

            if(cost >= costs[index])
                continue;
            costs[index] = cost;

            if(index == end)
                return cost;

            for(long[] edge : cities.get(index).adjacent){
                int to = (int)edge[0];
                long edgeCost = edge[1];

                if(cost + edgeCost < costs[to])
                    pq.add(new long[]{to, cost + edgeCost});
            }
        }

        return -1;
    }

}

class City{
    int index;
    List<long[]> adjacent = new ArrayList<>();

    public City(int index) {
        this.index = index;
    }
}

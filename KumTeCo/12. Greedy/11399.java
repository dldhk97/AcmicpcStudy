import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(int i = 0 ; i < N ; i++){
            long cost = Integer.parseInt(st.nextToken());
            pq.add(cost);
        }

        long prevCost = 0;
        long totalCost = 0;
        while(!pq.isEmpty()){
            long polled = pq.poll();
            totalCost += prevCost + polled;
            prevCost = prevCost + polled;
        }

        System.out.println(totalCost);

    }
}
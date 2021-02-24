import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            int o1Start = o1[0]; int o1End = o1[1];
            int o2Start = o2[0]; int o2End = o2[1];

            if(o1End == o2End){
                return o1Start - o2Start;
            }

            return o1End - o2End;
        });

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.add(new int[] {start, end});
        }

        int cnt = 0;
        int prevEnd = -1;
        while(!pq.isEmpty()){
            int[] polled = pq.poll();
            int polledStart = polled[0]; int polledEnd = polled[1];

            if(prevEnd <= polledStart){
                cnt++;
                prevEnd = polledEnd;
            }
        }

        System.out.println(cnt);
    }
}
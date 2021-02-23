import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    final static int MAX = 100000;
    static int N, K;

    static boolean isAvailable(int pos){
        return (0 <= pos && pos < MAX + 1);
    }

    static void solve(){
        int[] costs = new int[MAX + 1];

        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[N] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {N, 0});

        while(!queue.isEmpty()){
            int[] polled = queue.poll();
            int pos = polled[0]; int cost = polled[1];

            if(pos == K){
                System.out.println(cost);
                return;
            }

            int newPos = pos * 2;
            if(isAvailable(newPos)){
                if(cost + 1 < costs[newPos]){
                    costs[newPos] = cost + 1;
                    queue.add(new int[] {newPos, cost + 1});
                }
            }

            newPos = pos + 1;
            if(isAvailable(newPos)){
                if(cost + 1 < costs[newPos]){
                    costs[newPos] = cost + 1;
                    queue.add(new int[] {newPos, cost + 1});
                }
            }

            newPos = pos - 1;
            if(isAvailable(newPos)){
                if(cost + 1 < costs[newPos]){
                    costs[newPos] = cost + 1;
                    queue.add(new int[] {newPos, cost + 1});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        solve();
    }
}
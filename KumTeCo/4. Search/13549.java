import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static final int RANGE = 100001;
    static final int INF = 1000000000;

    static boolean isAvailable(int x){
        return (0 <= x && x < RANGE);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] times = new int[RANGE];
        Arrays.fill(times, INF);

        times[N] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {N, 0});

        while(!queue.isEmpty()){
            int[] polled = queue.poll();
            int pos = polled[0]; int time = polled[1];

            if(time > times[pos]) continue;
            if(pos == K)
                break;

            int teleport = 2 * pos;
            if(isAvailable(teleport)){
                int newTime = time;
                if(newTime < times[teleport]){
                    times[teleport] = newTime;
                    queue.add(new int[] {teleport, newTime});
                }
            }

            int backward = pos - 1;
            if(isAvailable(backward)){
                int newTime = time + 1;
                if(newTime < times[backward]){
                    times[backward] = newTime;
                    queue.add(new int[] {backward, newTime});
                }
            }

            int forward = pos + 1;
            if(isAvailable(forward)){
                int newTime = time + 1;
                if(newTime < times[forward]){
                    times[forward] = newTime;
                    queue.add(new int[] {forward, newTime});
                }
            }
        }

        System.out.println(times[K]);
    }
}
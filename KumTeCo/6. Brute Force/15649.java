import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int N, M;
    static boolean[] used;
    static int[] nums;

    public static void solve(int index){
        if(index >= M){
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < M ; i++){
                sb.append(nums[i]);
                sb.append(' ');
            }
            System.out.println(sb.toString());
            return;
        }

        for(int i = 1 ; i <= N ; i++){
            if(used[i])
                continue;

            used[i] = true;
            nums[index] = i;
            solve(index + 1);
            used[i] = false;
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        used = new boolean[N + 1];
        nums = new int[M];

        solve(0);
    }
}
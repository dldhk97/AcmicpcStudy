import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

    static int N, M;
    static int[] printNums;
    static int[] nums;
    static boolean[] used;

    static StringBuilder sb = new StringBuilder();

    public static void solve(int index){
        if(index >= M){
            for(int i = 0 ; i < M ; i++){
                sb.append(printNums[i]);
                sb.append(' ');
            }
            sb.append('\n');
            return;
        }

        for(int i = 0 ; i < N ; i++){
            if(used[i])
                continue;

            used[i] = true;
            printNums[index] = nums[i];
            solve(index + 1);
            used[i] = false;
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        printNums = new int[M];
        nums = new int[N];
        used = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int x = Integer.parseInt(st.nextToken());
            nums[i] = x;
        }

        Arrays.sort(nums);

        solve(0);

        System.out.println(sb.toString());
    }
}
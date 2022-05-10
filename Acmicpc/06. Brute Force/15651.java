import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int N, M;
    static int[] nums;

    static StringBuilder sb = new StringBuilder();

    public static void solve(int index){
        if(index >= M){
            for(int i = 0 ; i < M ; i++){
                sb.append(nums[i]);
                sb.append(' ');
            }
            sb.append('\n');
            return;
        }

        for(int i = 1 ; i <= N ; i++){
            nums[index] = i;
            solve(index + 1);
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[M];

        solve(0);

        System.out.println(sb.toString());
    }
}
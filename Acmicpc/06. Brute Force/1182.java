import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int N, S;
    static int[] arr;
    static int cnt = 0;

    public static void solve(int index, int sum){
        if(index >= N)
            return;

        sum += arr[index];
        if(sum == S)
            cnt++;

        solve(index + 1, sum);
        solve(index + 1, sum - arr[index]);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new int[N];

        for(int i = 0 ; i < N ; i++){
            int x = Integer.parseInt(st.nextToken());
            arr[i] = x;
        }

        solve(0, 0);

        System.out.println(cnt);
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main{

    static int N;
    static int S = 0;

    static int[] chu;
    static boolean[] visited;

    static void solve(int index, int sum){
        if(index > N){
            if(sum >= 0)
                visited[sum] = true;
            return;
        }

        solve(index + 1, sum - chu[index]);
        solve(index + 1, sum);
        solve(index + 1, sum + chu[index]);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        chu = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i <= N ; i++){
            int weight = Integer.parseInt(st.nextToken());
            chu[i] = weight;
            S += weight;
        }

        visited = new boolean[S + 1];

        solve(1, 0);

        int cnt = 0;
        for(int i = 1 ; i <= S ; i++){
            if(!visited[i])
                cnt++;
        }
        System.out.println(cnt);
    }

}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int[] arr;

    public static int findRoot(int x){
        if(arr[x] == x){
            return x;
        }
        else{
            return arr[x] = findRoot(arr[x]);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        for(int i = 0 ; i < n + 1 ; i++){
            arr[i] = i;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int aRoot = findRoot(a);
            int bRoot = findRoot(b);

            if(op == 0){
                if(aRoot == bRoot)
                    continue;

                arr[bRoot] = aRoot;
            }
            else{
                if(aRoot == bRoot)
                    sb.append("YES\n");
                else
                    sb.append("NO\n");
            }
        }

        System.out.println(sb.toString());

    }
}
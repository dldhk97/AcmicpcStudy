import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] values = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++){
            int value = Integer.parseInt(st.nextToken());
            values[i] = value;
        }

        Arrays.sort(values);

        int x = Integer.parseInt(br.readLine());

        int cnt = 0;

        for(int i = 0 ; i < n - 1 ; i++){
            for(int j = i + 1 ; j < n ; j++){
                int sum = values[i] + values[j];
                if(sum == x)
                    cnt++;

                if(sum > x)
                    break;
            }
        }

        System.out.println(cnt);
    }
}
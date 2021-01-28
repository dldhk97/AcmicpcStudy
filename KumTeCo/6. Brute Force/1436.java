import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int N;
    static int[] dp = new int[1000001];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int num = 0;
        int cnt = 0;
        for(int i = 0 ; cnt < N ; i++){
            if(String.valueOf(i).contains("666")){
                cnt++;
                num = i;
            }

        }

        System.out.println(num);
    }
}
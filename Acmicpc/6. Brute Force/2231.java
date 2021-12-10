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
        for(int i = N ; i >= 0 ; i--){
            char[] chars = String.valueOf(i).toCharArray();

            int sum = i;
            for(char c : chars){
                sum += (c - '0');
            }

            if(sum == N){
                num = i;
            }
        }

        System.out.println(num);
    }
}
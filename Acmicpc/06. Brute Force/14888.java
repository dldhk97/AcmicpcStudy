import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int N;
    static int[] VALUES;
    static int[] OPERATORS;

    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;

    static int calc(int operatorIdx, int num1, int num2){
        switch(operatorIdx){
            case 0:
                return num1 + num2;
            case 1:
                return num1 - num2;
            case 2:
                return num1 * num2;
            default:
                if(num1 < 0){
                    int value = (-1 * num1) / num2;
                    return -1 * value;
                }
                else{
                    return num1 / num2;
                }
        }
    }

    static void solve(int index, int sum){
        if(index >= N){
            MAX = Math.max(sum, MAX);
            MIN = Math.min(sum, MIN);
            return;
        }

        int a = VALUES[index];

        for(int operatorIdx = 0 ; operatorIdx < 4 ; operatorIdx++){
            if(OPERATORS[operatorIdx] > 0){
                OPERATORS[operatorIdx]--;

                int tempSum = calc(operatorIdx, sum, VALUES[index]);
                solve(index + 1, tempSum);

                OPERATORS[operatorIdx]++;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        VALUES = new int[N];
        OPERATORS = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int value = Integer.parseInt(st.nextToken());

            VALUES[i] = value;
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 4 ; i++){
            int op = Integer.parseInt(st.nextToken());

            OPERATORS[i] = op;
        }

        solve(1, VALUES[0]);

        System.out.println(MAX);
        System.out.println(MIN);
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    private static final int MAX_SIZE = 10001;

    // 최대공약수
    public static int getGCD(int bigger, int smaller){

        // 소수를 구한다.
        int isNotPrime[] = new int[smaller + 1];
        isNotPrime[0] = 1; isNotPrime[1] = 1;

        for(int i = 2 ; i < smaller + 1; i++){
            if(isNotPrime[i] == 0){
                for(int j = i + i ; j < smaller + 1 ; j += i){
                    isNotPrime[j] = 1;
                }
            }
        }

        // 소수의 배수를 두 수의 나머지 연산이 0일때 까지 시도 후 맥스 갱신.
        int max = 1;
        for(int i = 0 ; i < smaller + 1; i++){
            if(isNotPrime[i] == 0){
                for(int j = i ; j < smaller + 1 ; j += i){
                    if(bigger % j == 0 && smaller % j == 0){
                        if(max < j){
                            max = j;
                        }
                    }
                }
            }
        }

        return max;
    }

    // 최소공배수
    public static int getLCM(int bigger, int smaller){
        for(int i = bigger; i <= MAX_SIZE * MAX_SIZE; i += bigger){
            for(int j = smaller; j <= i ; j += smaller){
                if(i == j){
                    return j;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int bigger = a > b ? a : b;
        int smaller = a > b ? b : a;

        System.out.println(getGCD(bigger, smaller));
        System.out.println(getLCM(bigger, smaller));


    }
}
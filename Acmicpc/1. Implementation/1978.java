import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_SIZE = 1001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫줄은 쓰지않는다.
        br.readLine();

        // 0 이면 소수, 1이면 소수아님
        int isPrime[] = new int[MAX_SIZE];
        isPrime[0] = 1; isPrime[1] = 1;

        for(int i = 2; i < MAX_SIZE ; i++){
            if(isPrime[i] == 0){
                for(int j = i * 2 ; j < MAX_SIZE ; j += i){
                    isPrime[j] = 1;
                }
            }
        }

        int cnt = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int number = Integer.parseInt(st.nextToken());

            if(isPrime[number] == 0){
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}

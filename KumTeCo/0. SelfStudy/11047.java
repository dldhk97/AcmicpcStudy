import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> coins = new ArrayList<>();
        while(N-- > 0){
            int coin = Integer.parseInt(br.readLine());
            coins.add(coin);
        }

        int coinCnt = 0;
        int remain = K;

        for(int i = coins.size() - 1; i >= 0 ; i--){
            int currentCoin = coins.get(i);

            if(coins.get(i) <= remain){
                coinCnt += remain / currentCoin;
                remain = remain % currentCoin;
            }
            if(remain <= 0){
                break;
            }
        }

        System.out.println(coinCnt);

    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<Integer> lengths = new ArrayList<>();

        for(int i = 0 ; i < K ; i++){
            int length = Integer.parseInt(br.readLine());
            lengths.add(length);
        }

        lengths.sort(Integer::compareTo);

        long left = 1;
        long right = lengths.get(lengths.size() - 1);

        long maxLength = 0;
        while(left <= right){
            long mid = (left + right) / 2;

            long sum = 0;
            for(int i = lengths.size() - 1 ; i >= 0 ; i--){
                long splitedCnt = lengths.get(i) / mid;
                if(splitedCnt <= 0){
                    break;
                }
                sum += splitedCnt;
            }

            if(sum >= N){
                if(mid > maxLength){
                    maxLength = mid;
                }
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        System.out.println(maxLength);
    }
}
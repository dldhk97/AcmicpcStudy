import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long maxHeight = 0;

        long start = 0;
        long end = arr[N - 1];

        while(start <= end){
            long mid = (start + end) / 2;
            long sum = 0;
            for(int i = arr.length - 1 ; i >= 0 ; i--){
                if(arr[i] <= mid){
                    break;
                }
                sum += arr[i] - mid;
            }

            if(sum >= M){
                if(mid > maxHeight){
                    maxHeight = mid;
                }
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }

        System.out.println(maxHeight);

    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long minLength = Long.MAX_VALUE;
        int left = 0;
        int right = 1;
        long sum = arr[0] + arr[1];

        while(left <= right){

            if(sum >= S){
                long length = right - left + 1;

                if(length < minLength){
                    minLength = length;

                    if(minLength == 1){
                        break;
                    }
                }

                if(left < N - 1){
                    sum -= arr[left];
                    left++;
                }
                else{
                    break;
                }

            }
            else{
                if(right < N - 1){
                    right++;
                    sum += arr[right];
                }
                else{
                    break;
                }

            }
        }

        if(minLength == Long.MAX_VALUE){
            minLength = 0;
        }

        System.out.println(minLength);
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Long> values = new ArrayList<>();

        while(st.hasMoreTokens()){
            long value = Long.parseLong(st.nextToken());
            values.add(value);
        }

        int left = 0;
        int right = values.size() - 1;

        int low = left;
        int high = right;
        long sum;
        long minValue = Long.MAX_VALUE;

        while(left < right){
            sum = values.get(left) + values.get(right);

            if(Math.abs(sum) < minValue){
                minValue = Math.abs(sum);
                low = left;
                high = right;
            }

            if(sum > 0){
                right--;
            }
            else{
                left++;
            }
        }

        System.out.println(values.get(low) + " " + values.get(high));

    }
}
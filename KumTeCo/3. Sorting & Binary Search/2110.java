import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        List<Long> positions = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            positions.add(Long.parseLong(br.readLine()));
        }

        positions.sort(Long::compareTo);

        List<Long> diffs = new ArrayList<>();
        long totalDiffs = 0;
        for(int i = 1 ; i < positions.size() ; i++){
            long diff = positions.get(i) - positions.get(i - 1);
            diffs.add(diff);
            totalDiffs += diff;
        }

        long left = 0;
        long right = totalDiffs / (C - 1);
        long mid = right;

        long result = 0;

        while(left <= right){
            long sum = 0;
            int cnt = 1;
            for(int i = 1 ; i < positions.size() ; i++){
                long diff = positions.get(i) - positions.get(i - 1);
                sum += diff;
                if(mid <= sum){
                    sum = 0;
                    cnt++;

                    if(cnt >= C){
                        break;
                    }
                }
            }

            if(cnt >= C){
                if(mid > result){
                    result = mid;
                }
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
            mid = (left + right) / 2;
        }

        System.out.println(result);

    }
}
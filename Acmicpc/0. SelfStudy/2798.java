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

        List<Integer> cards = new ArrayList<>();

        while(st.hasMoreTokens()){
            int card = Integer.parseInt(st.nextToken());
            cards.add(card);
        }

        cards.sort(Integer::compareTo);

        int maxSum = Integer.MIN_VALUE;

        int leftIdx = cards.size() - 3;
        int midIdx = cards.size() - 2;
        int rightIdx = cards.size() - 1;

        while(rightIdx >= 2){
            int sum = cards.get(leftIdx) + cards.get(midIdx) + cards.get(rightIdx);

            if(sum <= M){
                if(sum > maxSum){
                    maxSum = sum;
                }
            }

            if(leftIdx > 0){
                leftIdx--;
            }
            else{
                if(midIdx > 1){
                    midIdx--;
                    leftIdx = midIdx - 1;
                }
                else{
                    rightIdx--;
                    midIdx = rightIdx - 1;
                    leftIdx = midIdx - 1;
                }
            }
        }

        System.out.println(maxSum);

    }
}
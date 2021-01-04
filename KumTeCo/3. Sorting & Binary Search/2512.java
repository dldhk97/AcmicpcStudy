import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long M = Long.parseLong(br.readLine());

        List<Integer> budgets = new ArrayList<>();

        long budgetSum = 0;
        int maxBudget = 0;
        while(N-- > 0){
            int budget = Integer.parseInt(st.nextToken());
            budgets.add(budget);

            if(budget > maxBudget){
                maxBudget = budget;
            }

            budgetSum += budget;
        }

        if(budgetSum <= M){
            System.out.println(maxBudget);
            return;
        }

        budgets.sort(Integer::compareTo);

        int left = 1;
        int right = budgets.get(budgets.size() - 1);
        int mid;

        maxBudget = 0;

        while(left <= right){
            mid = (left + right) / 2;

            long sum = 0;
            for(int i = budgets.size() - 1 ; i >= 0 ; i--){
                if(sum > M){
                    break;
                }
                int budget = budgets.get(i);
                sum += Math.min(budget, mid);
            }

            if(sum > M){
                right = mid - 1;
            }
            else{
                if(mid > maxBudget){
                    maxBudget = mid;
                }
                left = mid + 1;
            }
        }

        System.out.println(maxBudget);

    }
}
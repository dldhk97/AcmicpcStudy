import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int costSum = 0;

        for(int i = 1 ; i < N + 1 ; i++){
            deque.addLast(i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++){
            int idx = Integer.parseInt(st.nextToken());

            int targetIdx = 0;
            for(int j : deque){
                if(j == idx)
                    break;
                targetIdx++;
            }

            int cost = 0;
            while(true){
                if(deque.isEmpty()) break;

                if(idx == deque.peekFirst()){
                    deque.removeFirst();
                    break;
                }

                if(targetIdx < deque.size() - targetIdx)
                    deque.addLast(deque.pollFirst());
                else
                    deque.addFirst(deque.pollLast());

                cost++;
            }
            costSum += cost;
        }

        System.out.println(costSum);
    }
}
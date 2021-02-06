import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new ArrayDeque<>();

        for(int i = 1 ; i <= N ; i++){
            queue.add(i);
        }

        int result = -1;
        while(!queue.isEmpty()){
            int polled = queue.poll();

            if(queue.isEmpty()){
                result = polled;
                break;
            }

            int polled2 = queue.poll();
            queue.add(polled2);
        }

        System.out.println(result);
    }
}
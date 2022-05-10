import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int bufferSize = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new ArrayDeque<>();

        while(true){
            int input = Integer.parseInt(br.readLine());

            if(input > 0){
                if(queue.size() < bufferSize) {
                    queue.add(input);
                }
            }
            else if(input == 0){
                queue.poll();
            }
            else{
                break;
            }
        }

        if(queue.isEmpty()){
            System.out.println("empty");
        }
        else{
            StringBuilder sb = new StringBuilder();
            while(!queue.isEmpty()){
                sb.append(queue.poll());
                sb.append(" ");
            }
            System.out.println(sb.toString().trim());
        }

    }
}
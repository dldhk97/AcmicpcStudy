import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();

        for(int i = 1 ; i <= N ; i++){
            list.add(i);
        }

        System.out.print("<");
        int deleteIndex = K - 1;
        while(!list.isEmpty()){
            int elem = list.get(deleteIndex);
            list.remove(deleteIndex);
            System.out.print(elem);
            if(list.size() > 0) {
                deleteIndex = (deleteIndex - 1 + K) % list.size();
                System.out.print(", ");
            }
        }
        System.out.print(">");

    }
}
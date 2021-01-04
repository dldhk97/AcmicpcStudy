import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<Integer, Boolean> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        while(N-- > 0){
            int i = Integer.parseInt(st.nextToken());
            map.put(i, false);
        }


        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] result = new int[M];
        for(int i = 0 ; i < M ; i++){
            int target = Integer.parseInt(st.nextToken());
            if(map.containsKey(target)){
                result[i] = 1;
            }
            else{
                result[i] = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i : result){
            sb.append(i);
            sb.append('\n');
        }
        System.out.println(sb.toString());

    }
}
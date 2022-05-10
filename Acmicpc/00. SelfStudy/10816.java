import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Integer, Integer> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; st.hasMoreTokens() ; i++){
            int key  = Integer.parseInt(st.nextToken());
            if(map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            }
            else{
                map.put(key, 1);
            }
        }

        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; st.hasMoreTokens() ; i++){
            int key = Integer.parseInt(st.nextToken());
            int value = 0;
            if(map.containsKey(key))
                value = map.get(key);
            sb.append(value);
            sb.append(' ');
        }

        System.out.println(sb.toString());
    }
}
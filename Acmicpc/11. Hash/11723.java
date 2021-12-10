import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());

        Map<Integer, Integer> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String operator = st.nextToken();

            int value = 0;
            if(st.hasMoreTokens())
                value = Integer.parseInt(st.nextToken());

            switch(operator){
                case "add":
                    map.put(value, 0);
                    break;
                case "remove":
                    map.remove(value);
                    break;
                case "check":
                    int checkResult = map.containsKey(value) ? 1 : 0;
                    sb.append(checkResult).append("\n");
                    break;
                case "toggle":
                    if(map.containsKey(value))
                        map.remove(value);
                    else
                        map.put(value, 0);
                    break;
                case "all":
                    map.clear();
                    for(int j = 1 ; j <= 20 ; j++){
                        map.put(j, 0);
                    }
                    break;
                case "empty":
                    map.clear();
                    break;
            }
        }

        System.out.println(sb.toString());
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static boolean isInteger(String value){
        try{
            Integer.parseInt(value);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>();
        String[] arr = new String[N + 1];

        for(int i = 1 ; i <= N ; i++){
            String s = br.readLine();
            map.put(s, i);
            arr[i] = s;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= M ; i++){
            String str = br.readLine();
            if(isInteger(str)){
                int index = Integer.parseInt(str);
                sb.append(arr[index]).append("\n");
            }
            else{
                sb.append(map.get(str)).append("\n");
            }
        }

        System.out.print(sb.toString());
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, String> neverHeard = new HashMap<>();

        for(int i = 0 ; i < N ; i++){
            String name = br.readLine();
            neverHeard.put(name, name);
        }

        List<String> resultList = new ArrayList<>();

        for(int i = 0 ; i < M ; i++){
            String name = br.readLine();
            if(neverHeard.containsKey(name)){
                resultList.add(name);
            }
        }

        resultList.sort(String::compareTo);

        StringBuilder sb = new StringBuilder();
        for(String s : resultList){
            sb.append(s);
            sb.append('\n');
        }
        System.out.println(resultList.size());
        System.out.println(sb.toString());

    }
}
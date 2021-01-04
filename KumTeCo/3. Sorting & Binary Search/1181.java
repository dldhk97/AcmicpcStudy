import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<String> words = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            String s = br.readLine();
            if(!words.contains(s)){
                words.add(s);
            }
        }

        words.sort((o1, o2) -> {
            if(o1.length() != o2.length()) return o1.length() - o2.length();
            return o1.compareTo(o2);
        });

        StringBuilder sb = new StringBuilder();
        for(String s : words){
            sb.append(s);
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
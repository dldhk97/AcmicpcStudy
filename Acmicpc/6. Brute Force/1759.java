import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main{

    static int C, L;
    static char[] chars;
    static Map<Character, Boolean> used;
    static int vowelCnt = 0;
    static int consonantCnt = 0;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int index, String s){
        if(index >= L){
            if(vowelCnt >= 1 && consonantCnt >= 2){
                sb.append(s);
                sb.append('\n');
            }

            return;
        }


        for(char c : chars){
            if(used.containsKey(c))
                continue;

            if(s.length() > 0){
                if(s.charAt(s.length() - 1) > c)
                    continue;
            }

            boolean isVowel = isVowel(c);

            if(isVowel){
                vowelCnt++;
            }
            else{
                consonantCnt++;
            }

            used.put(c, true);

            dfs(index + 1, s + c);

            used.remove(c);

            if(isVowel){
                vowelCnt--;
            }
            else{
                consonantCnt--;
            }

        }

    }

    static boolean isVowel(char c){
        switch(c){
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        chars = new char[C];
        used = new HashMap<>();

        for(int i = 0 ; i < C ; i++){
            char c = st.nextToken().charAt(0);
            chars[i] = c;
        }

        Arrays.sort(chars);

        dfs(0, "");

        System.out.println(sb.toString());

    }

}
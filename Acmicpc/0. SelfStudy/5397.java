import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            String line = br.readLine();

            List<Character> result = new LinkedList<>();

            int cursorIdx = 0;
            for(char c : line.toCharArray()){
                switch(c){
                    case '<':
                        cursorIdx = cursorIdx > 0 ? cursorIdx - 1 : 0;
                        break;
                    case '>':
                        cursorIdx = cursorIdx >= result.size() ? result.size() : cursorIdx + 1;
                        break;
                    case '-':
                        if(cursorIdx > 0 && !result.isEmpty()){
                            result.remove(--cursorIdx);
                        }
                        break;
                    default:
                        result.add(cursorIdx++, c);
                        break;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(char c : result){
                sb.append(c);
            }
            System.out.println(sb.toString());
        }

    }
}
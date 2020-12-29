import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();

        int bombLength = bomb.length();

        Stack<Character> charStack = new Stack<>();
        for(int i = str.length() - 1; i >= 0 ; i--){
            charStack.push(str.charAt(i));

            boolean isBomb = false;
            for(int j = 0 ; j < bombLength && charStack.size() >= bombLength ; j++){
                char x = charStack.get(charStack.size() - 1 - j);
                char y = bomb.charAt(j);
                if(x != y){
                    isBomb = false;
                    break;
                }
                else{
                    isBomb = true;
                }
            }

            if(isBomb){
                for(int j = 0 ; j < bombLength ; j++){
                    charStack.pop();
                }
            }
        }

        if(charStack.isEmpty()){
            System.out.println("FRULA");
        }
        else{
            StringBuilder sb = new StringBuilder();
            while(!charStack.isEmpty()){
                sb.append(charStack.pop());
            }
            System.out.println(sb.toString());
        }

    }
}
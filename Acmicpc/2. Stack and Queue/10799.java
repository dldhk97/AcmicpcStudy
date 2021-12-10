import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>();

        String line = br.readLine();
        char prev = '(';
        int result = 0;
        for(char c : line.toCharArray()){
            if(c == '('){
                stack.push(c);
            }
            else{
                stack.pop();
                if(prev == '('){
                    result += stack.size();
                }
                else{
                    result += 1;
                }

            }
            prev = c;
        }

        System.out.println(result);
    }
}
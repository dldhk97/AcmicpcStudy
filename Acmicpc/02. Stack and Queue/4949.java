import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String line = br.readLine();

            if(line.equals(".")){
                break;
            }

            String result = "yes";

            Stack<Character> stack = new Stack<>();
            for(char c : line.toCharArray()){
                if(c == '(' || c == '['){
                    stack.push(c);
                }
                else if(c ==')' || c == ']'){
                    char opener = c == ')' ? '(' : '[';

                    if(stack.empty()){
                        result = "no";
                        break;
                    }
                    char top = stack.pop();
                    if(top != opener){
                        result = "no";
                        break;
                    }

                }
            }
            if(!stack.empty()){
                result = "no";
            }
            System.out.println(result);
        }
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int lineCnt = Integer.parseInt(br.readLine());

        while(lineCnt-- > 0){

            String result = "YES";
            Stack<Character> stack = new Stack<>();

            String line = br.readLine();
            for(char c : line.toCharArray()){
                if(c == '('){
                    stack.push(c);
                }
                else{
                    if(stack.empty()){
                        result = "NO";
                        break;
                    }
                    stack.pop();
                }
            }

            if(!stack.empty()){
                result = "NO";
            }

            System.out.println(result);
        }
    }
}
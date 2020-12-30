import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{

    public static boolean isOpener(char c){
        if(c == '(' || c == '['){
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<String> stack = new Stack<>();
        char chars[] = br.readLine().toCharArray();

        for(char c : chars){
            if(isOpener(c)){
                stack.push(String.valueOf(c));
            }
            else{
                if(stack.isEmpty()){
                    System.out.println("0");
                    return;
                }
                String top = stack.pop();
                String opener = c == ')' ? "(" : "[";
                if(!top.equals(opener)){
                    System.out.println("0");
                    return;
                }
            }
        }

        if(!stack.isEmpty()){
            System.out.println("0");
            return;
        }

        stack.clear();

        for(char c : chars){
            if(isOpener(c)){
                stack.push(String.valueOf(c));
            }
            else{
                String opener = c == ')' ? "(" : "[";
                int value = c == ')' ? 2 : 3;

                int sum = 0;
                while(!stack.empty()){
                    String top = stack.pop();
                    if(top.equals(opener)){
                        if(sum != 0){
                            value = value * sum;
                        }
                        stack.push(String.valueOf(value));
                        break;
                    }

                    if(isNumeric(top)){
                        sum += Integer.parseInt(top);
                        continue;
                    }

                    // 에러발생
                    System.out.println("0");
                    return;

                }
            }
        }

        int values = 0;
        while(!stack.isEmpty()){
            String top = stack.pop();
            values += Integer.parseInt(top);
        }
        System.out.println(values);
    }
}
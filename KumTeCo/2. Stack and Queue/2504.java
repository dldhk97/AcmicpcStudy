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

    public static boolean isCloser(char c){
        if(c == ')' || c == ']'){
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
                char opener = c == ')' ? '(' : '[';
                int value = c == ')' ? 2 : 3;

                if(stack.empty()){
                    System.out.println("0");
                    return;
                }

                int totalValue = 0;
                while(!stack.empty()){
                    String top = stack.pop();
                    if(top.equals(String.valueOf(opener))){
                        if(totalValue != 0){
                            value = value * totalValue;
                        }
                        stack.push(String.valueOf(value));
                        break;
                    }

                    if(isNumeric(top)){
                        totalValue += Integer.parseInt(top);
                        continue;
                    }

                    // 에러발생
                    System.out.println("0");
                    return;

                }
            }
        }

        String result = "0";

        int values = 0;
        while(!stack.isEmpty()){
            String top = stack.pop();
            values += Integer.parseInt(top);
            result = String.valueOf(values);
        }
        System.out.println(result);
    }
}
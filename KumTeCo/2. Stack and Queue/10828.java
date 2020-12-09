import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class IntegerStack{
    int topIndex = -1;
    private List<Integer> list = new ArrayList<>();

    public void push(int integer){
        list.add(integer);
        topIndex++;
    }

    public int pop(){
        if(list.isEmpty()){
            return -1;
        }
        int top = list.get(topIndex);
        list.remove(topIndex--);
        return top;
    }

    public int size(){
        return list.size();
    }

    public int empty(){
        return list.isEmpty() ? 1 : 0;
    }

    public int top(){
        if(list.isEmpty())
            return -1;
        return list.get(topIndex);
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int commandSize = Integer.parseInt(br.readLine());

        IntegerStack stack = new IntegerStack();

        while(commandSize-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command){
                case "push":
                    int integer = Integer.parseInt(st.nextToken());
                    stack.push(integer);
                    break;

                case "pop":
                    System.out.println(stack.pop());
                    break;

                case "size":
                    System.out.println(stack.size());
                    break;

                case "empty":
                    System.out.println(stack.empty());
                    break;

                case "top":
                    System.out.println(stack.top());
                    break;
            }
        }
    }
}
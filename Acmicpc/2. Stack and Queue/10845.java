import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class IntegerQueue{
    private List<Integer> list = new ArrayList<>();

    public void push(int integer){
        list.add(integer);
    }

    public int pop(){
        if(empty()){
            return -1;
        }

        int top = list.get(0);
        list.remove(0);
        return top;
    }

    public int size(){
        return list.size();
    }

    public boolean empty(){
        return list.isEmpty();
    }

    public int front(){
        if(empty()){
            return -1;
        }
        return list.get(0);
    }

    public int back(){
        if(empty()){
            return -1;
        }
        return list.get(list.size() - 1);
    }
}

public class Main{



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int commandSize = Integer.parseInt(br.readLine());

        IntegerQueue queue = new IntegerQueue();


        while(commandSize-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch(command){
                case "push":
                    int arg = Integer.parseInt(st.nextToken());
                    queue.push(arg);
                    break;
                case "pop":
                    System.out.println(queue.pop());
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                case "empty":
                    String msg = queue.empty() ? "1" : "0";
                    System.out.println(msg);
                    break;
                case "front":
                    System.out.println(queue.front());
                    break;
                case "back":
                    System.out.println(queue.back());
                    break;
            }
        }

    }
}
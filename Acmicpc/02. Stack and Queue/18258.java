import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Queue{
    List<Integer> arr = new LinkedList<>();

    public void push(int x){
        arr.add(x);
    }

    public int pop(){
        if(arr.isEmpty())
            return -1;
        int value = arr.get(0);
        arr.remove(0);
        return value;
    }

    public int size(){
        return arr.size();
    }

    public boolean empty(){
        return arr.isEmpty();
    }

    public int front(){
        if(arr.isEmpty())
            return -1;
        return arr.get(0);
    }

    public int back(){
        if(arr.isEmpty())
            return -1;
        return arr.get(arr.size() - 1);
    }
}

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue queue = new Queue();

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            int value = -1;

            switch (command){
                case "push":
                    value = Integer.parseInt(st.nextToken());
                    queue.push(value);
                    continue;
                case "pop":
                    value = queue.pop();
                    break;
                case "size":
                    value = queue.size();
                    break;
                case "empty":
                    value = queue.empty() ? 1 : 0;
                    break;
                case "front":
                    value = queue.front();
                    break;
                case "back":
                    value = queue.back();
                    break;
            }
            sb.append(value);
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
}
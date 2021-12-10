import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main{

    static int N;

    public static void solve(String s, List<Integer> unused){
        if(unused.isEmpty()){
            System.out.println(s);
            return;
        }

        for(int i = 0 ; i < unused.size() ; i++){
            List<Integer> x = new ArrayList<>(unused);
            x.remove(i);

            String next = s + unused.get(i) + " ";
            solve(next, x);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        List<Integer> unused = new LinkedList<>();
        for(int i = 1 ; i <= N ; i++){
            unused.add(i);
        }

        solve("", unused);
    }
}
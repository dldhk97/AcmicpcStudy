import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] copied = Arrays.copyOf(arr, N);
        Arrays.sort(copied);

        Map<Integer, Integer> map = new HashMap<>();

        int index = 0;
        for(int i : copied){
            if(!map.containsKey(i))
                map.put(i, index++);
        }

        StringBuilder sb = new StringBuilder();
        for(int i : arr){
            sb.append(map.get(i)).append(" ");
        }
        System.out.println(sb.toString());
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());        // 안쓰임

        String userInput = br.readLine();
        StringTokenizer st = new StringTokenizer(userInput);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while(st.hasMoreTokens()){
            String x = st.nextToken();
            int i = Integer.parseInt(x);
            if(i > max){
                max = i;
            }
            if(i < min){
                min = i;
            }
        }
        System.out.println(String.valueOf(min) + " " + String.valueOf(max));
    }
}

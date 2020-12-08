import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < cnt ; i++){
            String userInput = br.readLine();

            int sum = 0;
            int prev = 0;
            for(char x : userInput.toCharArray()){
                if(x == 'O'){
                    prev += 1;
                }
                else{
                    prev = 0;
                }
                sum += prev;
            }
            System.out.println(sum);
        }
    }
}

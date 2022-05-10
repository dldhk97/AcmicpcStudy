import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String userInput = br.readLine();
            if(userInput.compareTo("0") == 0)
                break;

            String result = "yes";

            int totalSize = userInput.length();

            for(int i = 0 ; i < totalSize ; i++){
                int x = userInput.charAt(i);

                int y = userInput.charAt(totalSize - i - 1);

                if(x != y){
                    result = "no";
                    break;
                }
                if(i > totalSize / 2){
                    break;
                }
            }

            System.out.println(result);
        }


    }
}

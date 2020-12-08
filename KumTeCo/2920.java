import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int direction = 0;
        int prev = Integer.parseInt(st.nextToken());
        while(st.hasMoreTokens()){
            int x = Integer.parseInt(st.nextToken());

            if(x > prev){
                if (direction == -1){
                    direction = 0;
                    break;
                }
                direction = 1;
            }
            else{
                if (direction == 1){
                    direction = 0;
                    break;
                }
                direction = -1;
            }

            prev = x;
        }

        switch (direction){
            case -1:
                System.out.println("descending");
                break;
            case 0:
                System.out.println("mixed");
                break;
            case 1:
                System.out.println("ascending");
                break;
        }
    }
}

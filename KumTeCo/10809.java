import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int a = 'a';
        int z = 'z';

        int result[] = new int[z-a+1];

        int index = 0;

        for(int i = a ; i <= z ; i++){
            boolean isExists = false;
            for(int j = 0; j < s.length() ; j++){
                if(i == s.charAt(j)){
                    result[index++] = j;
                    isExists = true;
                    break;
                }
            }
            if(!isExists){
                result[index++] = -1;
            }
        }

        for(int i : result){
            System.out.print(String.valueOf(i) + " ");
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main{
    static int N;
    static int M;
    static int CNT;

    static boolean[] troubled = new boolean[10];

    public static void solve(String current, int charIndex){
        if(current.length() > 0){
            int pressedCnt = current.length();
            int diff = Math.abs(Integer.parseInt(current) - N);
            if(CNT > pressedCnt + diff){
                CNT = pressedCnt + diff;
            }
        }

        if(charIndex > String.valueOf(N).length()){
            return;
        }

        for(int i = 0 ; i < 10 ; i++){
            if(troubled[i]) continue;

            solve(current + i, charIndex + 1);
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nStr = br.readLine();
        N = Integer.parseInt(nStr);

        M = Integer.parseInt(br.readLine());

        if(M > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < M ; i++){
                int value = Integer.parseInt(st.nextToken());
                troubled[value] = true;
            }

            CNT = Math.abs(N - 100);

            solve("", 0);
        }
        else{
            int oneByOne = Math.abs(N - 100);
            int directPress = nStr.length();
            CNT = Math.min(oneByOne, directPress);
        }

        System.out.println(CNT);

    }

}
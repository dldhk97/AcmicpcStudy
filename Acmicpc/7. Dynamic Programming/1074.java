import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int[] dX = {0, 1, 0, 1};
    static int[] dY = {0, 0, 1, 1};

    static int count = 0;

    static int r, c;

    static void solve(int startX, int startY, int N){

        int pow = (int)Math.pow(2, N);

        int endX = startX + pow - 1;
        int endY = startY + pow - 1;

        if(N <= 1){
            for(int i = 0 ; i < 4 ; i++){
                int newX = startX + dX[i]; int newY = startY + dY[i];
                if(newX == c && newY == r){
                    System.out.println(count);
                    return;
                }
                count++;
            }
            return;
        }

        // 범위 안에 있으면 4등분 해서 재귀 호출
        if(((startX <= c && c <= endX) && (startY <= r && r <= endY))){
            int half = pow / 2;

            solve(startX, startY, N - 1);
            solve(startX + half, startY, N - 1);
            solve(startX, startY + half, N - 1);
            solve(startX + half, startY + half, N - 1);
        }
        // 범위 안에 있지는 않은데, 왼쪽이나 위쪽 범위인 경우
        else if(endX < c || endY < r){
            count += pow * pow;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solve(0, 0, N);
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int N, M;
    static int[][] arr;

    static int MAX_SIZE = 1;

    static void solve(int x, int y){

        for(int i = 1 ; ; i++){
            int newX = x + i;
            int newY = y + i;

            if(newX >= M || newY >= N){
                if(newX >= M && newY >= N){
                    break;
                }
                continue;
            }

            // check diagonal
            if(arr[newX][newY] != arr[x][y])
                continue;

            // check row
            if(arr[newX][y] != arr[x][y])
                continue;

            // check column
            if(arr[x][newY] != arr[x][y])
                continue;

            int width = i + 1;
            int size = width * width;
            if(MAX_SIZE < size)
                MAX_SIZE = size;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M][N];

        for(int y = 0 ; y < N ; y++){
            char[] chars = br.readLine().toCharArray();
            for(int x = 0 ; x < M ; x++){
                int value = chars[x] - '0';
                arr[x][y] = value;
            }
        }

        for(int y = 0 ; y < N ; y++){
            for(int x = 0 ; x < M ; x++){
                solve(x, y);
            }
        }



        System.out.println(MAX_SIZE);
    }
}
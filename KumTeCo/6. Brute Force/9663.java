import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main{
    static int N, CNT = 0;

    static int[] map;
    static void solve(int row){
        if(row > N){
            CNT++;
            return;
        }

        for(int column = 1 ; column <= N ; column++){
            if(!isAvailable(row, column)) continue;

            map[row] = column;
            solve(row + 1);
            map[row] = 0;
        }
    }

    static boolean isAvailable(int row, int column){
        if(!checkRow(row))
            return false;

        if(!checkColumn(column))
            return false;

        if(!checkDiagonal(row, column))
            return false;

        return true;
    }

    static boolean checkRow(int row){
        return map[row] <= 0;
    }

    static boolean checkColumn(int column){
        for(int col : map){
            if(col == column)
                return false;
        }
        return true;
    }

    static boolean checkDiagonal(int row, int column){
        for(int r = 0 ; r < N ; r++){
            if(map[r] == 0)
                continue;
            int rowDiff = Math.abs(r - row);
            int columnDiff = Math.abs(map[r] - column);

            if(rowDiff == columnDiff)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N + 1];

        solve(1);

        System.out.println(CNT);
    }

}
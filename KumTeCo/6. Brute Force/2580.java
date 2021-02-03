import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int[][] map = new int[9][9];
    static List<int[]> zeroes = new ArrayList<>();

    static boolean ESCAPE = false;

    static void printArray(){
        StringBuilder sb = new StringBuilder();
        for(int y = 0 ; y < 9 ; y++){
            for(int x = 0 ; x < 9 ; x++){
                sb.append(map[x][y]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static boolean checkHorizontal(int iy, int value){
        for(int x = 0 ; x < 9 ; x++){
            if(value == map[x][iy])
                return false;
        }
        return true;
    }

    static boolean checkVertical(int ix, int value){
        for(int y = 0 ; y < 9 ; y++){
            if(value == map[ix][y])
                return false;
        }
        return true;
    }

    static boolean checkSquare(int ix, int iy, int value){
        int sectionX = ix / 3 * 3;
        int sectionY = iy / 3 * 3;

        for(int y = sectionY ; y < sectionY + 3 ; y++){
            for(int x = sectionX ; x < sectionX + 3 ; x++){
                if(map[x][y] == value)
                    return false;
            }
        }
        return true;
    }

    static void solve(int index){
        if(ESCAPE)
            return;

        if(index >= zeroes.size()){
            printArray();
            ESCAPE = true;
            return;
        }

        int x = zeroes.get(index)[0];
        int y = zeroes.get(index)[1];

        if(map[x][y] != 0)
            return;

        for(int value = 1 ; value <= 9 ; value++){
            boolean horizontalCheck = checkHorizontal(y, value);
            boolean verticalCheck = checkVertical(x, value);
            boolean squareCheck = checkSquare(x, y, value);


            if(horizontalCheck && verticalCheck && squareCheck){
                map[x][y] = value;
                solve(index + 1);
            }
            map[x][y] = 0;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int y = 0 ; y < 9 ; y++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int x = 0 ; x < 9 ; x++){
                int value = Integer.parseInt(st.nextToken());

                map[x][y] = value;

                if(value == 0){
                    int[] point = {x, y};
                    zeroes.add(point);
                }
            }
        }

        solve(0);
    }
}
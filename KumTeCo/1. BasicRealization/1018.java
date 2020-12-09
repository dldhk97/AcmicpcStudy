import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    private static final int BOARD_WIDTH = 8;
    private static final int BOARD_HEIGHT = 8;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int inputHeight = Integer.parseInt(st.nextToken());       // 세로 길이
        int inputWidth = Integer.parseInt(st.nextToken());       // 가로 길이

        char inputBoard[][] = new char[inputHeight][inputWidth];

        // 입력 보드 생성
        for(int i = 0 ; i < inputHeight ; i++){
            char[] line = br.readLine().toCharArray();
            for(int j = 0 ; j < inputWidth ; j++){
                inputBoard[i][j] = line[j];
            }
        }

        int min = Integer.MAX_VALUE;

        // 좌측상단 모서리 기준으로 8x8을 만들 수 있는 모서리만 탐색.
        for(int i = 0 ; i <= inputHeight - BOARD_HEIGHT ; i++){
            for(int j = 0 ; j <= inputWidth - BOARD_WIDTH ; j++){

                // 좌측상단 모서리가 흑인 경우와 백인 경우를 나눠 탐색.
                char leftTopCorner = 'B';
                while(true){

                    char prevBlock = leftTopCorner;
                    int fill = 0;

                    // 8x8을 만들 수 있는 모서리라면, 8x8만큼 탐색
                    for(int k = i ; k < i + BOARD_HEIGHT ; k++){
                        for(int l = j ; l < j + BOARD_WIDTH ; l++){
                            if(prevBlock == 'W'){
                                if(inputBoard[k][l] == 'W'){
                                    fill++;
                                }
                                prevBlock = 'B';
                            }
                            else{
                                if(inputBoard[k][l] == 'B'){
                                    fill++;
                                }
                                prevBlock = 'W';
                            }
                        }
                        prevBlock = prevBlock == 'W' ? 'B' : 'W';   // 한줄 내려간 경우 윗줄 최우측 블럭 = 아랫줄 최좌측 블록
                    }

                    // 최솟값 갱신
                    if(fill < min){
                        min = fill;
                    }

                    if(leftTopCorner == 'W')
                        break;
                    leftTopCorner = 'W';
                }

            }
        }

        System.out.println(min);

    }
}
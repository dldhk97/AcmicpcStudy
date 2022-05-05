import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2116
public class Main {
  // 0-5, 1-3, 2-4
  static final int[] diceOppositeSurfaces = {5, 3, 4, 1, 2, 0};
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int K = Integer.parseInt(br.readLine());

    List<int[]> dices = new ArrayList<>();
    for(int i = 0 ; i < K ; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      int[] dice = new int[6];
      for(int j = 0 ; j < 6 ; j++){
        dice[j] = Integer.parseInt(st.nextToken());
      }
      dices.add(dice);
    }

    // 첫 주사위를 돌려보며 6면 각각 기둥 세워봄
    int max = 0;
    for(int i = 0 ; i < 6 ; i++){
      max = Math.max(max, createPillar(dices, i));
    }

    System.out.println(max);

  }

  private static int createPillar(List<int[]> dices, int firstDiceUpperSurface){
    // 첫 주사위 설정
    int diceUpperSurface = firstDiceUpperSurface;
    int upperNumber = dices.get(0)[diceUpperSurface];
    int sum = getMaxNumber(dices.get(0), diceUpperSurface);

    for(int i = 1 ; i < dices.size() ; i++){
      int[] currentDice = dices.get(i);
      int surface = findSurface(currentDice, upperNumber);  // 이전 주사위의 윗면 숫자가 현재 주사위에서 몇번 면(surface)에 있는지 찾음. 그것이 현재 주사위의 아랫면이 됨

      diceUpperSurface = diceOppositeSurfaces[surface];     // 현재 주사위의 윗면을 설정
      upperNumber = currentDice[diceUpperSurface];          // 다음 반복을 위해 윗면 숫자를 갱신

      int maxValue = getMaxNumber(dices.get(i), diceUpperSurface);  // 윗면, 아랫면을 제외한 최대 숫자 탐색
      sum += maxValue;
    }
    return sum;
  }

  // 주어진 면 및 반대면을 제외한 수 중 최댓값 얻기
  private static int getMaxNumber(int[] dice, int upperSurface){
    int maxNum = 0;
    for(int i = 0 ; i < dice.length ; i++){
      if(i == upperSurface || i == diceOppositeSurfaces[upperSurface])
        continue;
      maxNum = Math.max(maxNum, dice[i]);
    }
    return maxNum;
  }

  // 주사위 면 index 탐색
  private static int findSurface(int[] dice, int number){
    for(int i = 0 ; i < dice.length ; i++){
      if(dice[i] == number)
        return i;
    }
    return -1;
  }

}
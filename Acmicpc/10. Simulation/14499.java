import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14499
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int sY = Integer.parseInt(st.nextToken());
    int sX = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][M];
    for(int y = 0 ; y < N ; y++){
      st = new StringTokenizer(br.readLine());
      for(int x = 0 ; x < M ; x++){
        map[y][x] = Integer.parseInt(st.nextToken());
      }
    }

    Dice dice = new Dice(sX, sY);

    st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    while(K-- > 0){
      int rollDirection = Integer.parseInt(st.nextToken());

      boolean isSucceed = dice.roll(rollDirection, M, N);
      if(!isSucceed)
        continue;

      int mapValue = map[dice.getY()][dice.getX()];
      if(mapValue == 0) {
        map[dice.getY()][dice.getX()] = dice.getFloorFace();
      }
      else{
        dice.updateFloorFace(mapValue);
        map[dice.getY()][dice.getX()] = 0;
      }

      sb.append(dice.getCeilFace()).append("\n");
    }

    System.out.print(sb);
  }
}

class Dice{

  private static final int[] dX = {1, -1, 0, 0};
  private static final int[] dY = {0, 0, -1, 1};

  private final int[] face = new int[7];
  private int x, y;

  public Dice(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean roll(int direction, int xBound, int yBound){
    int nX = x + dX[direction - 1];
    int nY = y + dY[direction - 1];

    if(!isInBoundary(nX, nY, xBound, yBound))
      return false;

    updateFaces(direction);

    this.x = nX;
    this.y = nY;

    return true;
  }

  private boolean isInBoundary(int x, int y, int xBound, int yBound){
    return 0 <= x && x < xBound && 0 <= y && y < yBound;
  }

  private int[] shiftArray(int[] org){
    int[] shifted = new int[org.length];

    for (int i = 0; i < org.length - 1; i++)
      shifted[i + 1] = org[i];

    shifted[0] = org[org.length - 1];
    return shifted;
  }

  private void updateFaces(int direction){
    int[] faceIdx = getShiftTargetIdx(direction);

    int[] shiftedIdx = shiftArray(faceIdx);

    int[] faceBackup = new int[face.length];
    for(int i  = 0 ; i < faceIdx.length ; i++)
      faceBackup[faceIdx[i]] = face[faceIdx[i]];

    for(int i  = 0 ; i < faceIdx.length ; i++)
      face[faceIdx[i]] = faceBackup[shiftedIdx[i]];
  }

  private int[] getShiftTargetIdx(int direction){
    if(direction == 1)
      return new int[]{4, 1, 3, 6};
    else if(direction == 2)
      return new int[]{3, 1, 4, 6};
    else if(direction == 3)
      return new int[]{2, 1, 5, 6};
    else
      return new int[]{6, 5, 1, 2};
  }

  public void updateFloorFace(int value){
    face[6] = value;
  }

  public int getCeilFace(){
    return face[1];
  }

  public int getFloorFace(){
    return face[6];
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
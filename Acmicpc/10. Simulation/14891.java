import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14891
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Gear[] gears = new Gear[4];
    for(int i = 0 ; i < gears.length ; i++){
      gears[i] = new Gear(i, br.readLine());

      if(i - 1 >= 0){
        gears[i].setLeftGear(gears[i - 1]);
        gears[i - 1].setRightGear(gears[i]);
      }
    }

    int K = Integer.parseInt(br.readLine());
    for (int i = 0; i < K; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int gearIdx = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      gears[gearIdx - 1].rotate(direction, new boolean[gears.length]);
    }

    int sum = 0;
    for(Gear gear : gears){
      sum += gear.calcScore();
    }

    System.out.println(sum);
  }
}

class Gear{
  private final int gearIndex;
  private List<Integer> sawTooth = new LinkedList<>();
  private Gear leftGear;
  private Gear rightGear;

  public Gear(int gearIndex, String sawToothLine) {
    this.gearIndex = gearIndex;

    for(char c : sawToothLine.toCharArray()){
      sawTooth.add(c - '0');
    }
  }

  public void rotate(int direction, boolean[] rotated){
    rotated[gearIndex] = true;

    if(leftGear != null && !rotated[leftGear.getGearIndex()]){
      if(this.get9ClockSawTooth() != leftGear.get3ClockSawTooth()){
        leftGear.rotate(-direction, rotated);
      }
    }

    if(rightGear != null && !rotated[rightGear.getGearIndex()]){
      if(this.get3ClockSawTooth() != rightGear.get9ClockSawTooth()){
        rightGear.rotate(-direction, rotated);
      }
    }

    shiftList(direction);
  }

  private void shiftList(int direction){
    if(direction > 0){
      int temp = sawTooth.remove(sawTooth.size() - 1);
      sawTooth.add(0, temp);
    }
    else{
      int temp = sawTooth.remove(0);
      sawTooth.add(sawTooth.size(), temp);
    }
  }

  public int calcScore(){
    if(sawTooth.get(0) == 0)
      return 0;
    return (int)Math.pow(2, gearIndex);
  }

  public void setLeftGear(Gear leftGear) {
    this.leftGear = leftGear;
  }

  public void setRightGear(Gear rightGear) {
    this.rightGear = rightGear;
  }

  public int getGearIndex() {
    return gearIndex;
  }

  public int get9ClockSawTooth(){
    return sawTooth.get(6);
  }

  public int get3ClockSawTooth(){
    return sawTooth.get(2);
  }
}

// https://programmers.co.kr/learn/courses/30/lessons/62048
class Solution {
  private double LINE_ANGLE;
  private int H;

  public long solution(int w, int h) {
    long answer = 0;
    H = h;
    LINE_ANGLE = calcLineAngle(w, 0);

    for(int x = 1 ; x <= w ; x++){
      answer += Math.floor(getY(x));
    }

    return answer * 2;
  }

  private double calcLineAngle(double x, double y){
    return (y - H) / x;
  }

  private double getY(double x){
    return LINE_ANGLE * x + H;
  }
}
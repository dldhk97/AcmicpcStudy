// https://programmers.co.kr/learn/courses/30/lessons/12899
class Solution {
  private final char[] chars = {'4', '1', '2'};

  public String solution(int n) {
    StringBuilder sb = new StringBuilder();
    while(n > 0){
      int left = n % 3;
      sb.append(chars[left]);
      n /= 3;

      if(left == 0)
        n--;
    }

    return sb.reverse().toString();
  }
}
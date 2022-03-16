import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/12985
class Solution {
  List<Integer> aList = new ArrayList<>();
  List<Integer> bList = new ArrayList<>();

  public int solution(int n, int a, int b) {
    get(a - 1, n, aList);
    get( b - 1, n, bList);

    int index = 0;
    for(int i = n ; i >= 1 ; i /= 2){
      if(aList.get(index) / 2 == bList.get(index) / 2)
        break;
      index++;
    }
    return index + 1;
  }

  private void get(int x, int n, List<Integer> list){
    list.add(x);
    if(n == 1)
      return;
    get(x / 2, n / 2, list);
  }

}
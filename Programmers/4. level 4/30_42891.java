import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/42891
class Solution {
  public int solution(int[] food_times, long k) {
    LinkedList<Food> foods = new LinkedList<>();

    int index = 1;
    for(int time : food_times)
      foods.add(new Food(index++, time));

    foods.sort((o1, o2) -> o1.time - o2.time);

    long time = 0;
    int leftFoods = foods.size();
    int idx = 0;

    for(Food food : foods){
      long diff = food.time - time;
      if(diff == 0) {
        leftFoods--;
        idx++;
        continue;
      }

      long spend = diff * leftFoods;

      if(spend <= k){
        k -= spend;
        time = food.time;
      }
      else{
        k %= leftFoods;
        foods.subList(idx, foods.size()).sort(((o1, o2) -> o1.index - o2.index));
        return foods.get(idx + (int)k).index;
      }
      leftFoods--;
      idx++;
    }

    return -1;
  }
}

class Food{
  int index;
  int time;

  public Food(int index, int time) {
    this.index = index;
    this.time = time;
  }
}
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://programmers.co.kr/learn/courses/30/lessons/67257
class Solution {

  public long solution(String expression) {
    List<String> exp = createExpression(expression);

    return mutation(0, new char[3], new boolean[3], 0, exp);
  }

  private static final char[] OPERATOR = {'*', '+', '-'};
  private long mutation(int index, char[] operators, boolean[] used, long sum, List<String> expression){
    if(index >= 3){
      return Math.abs(calc(operators, expression));
    }

    for(int i = 0 ; i < 3 ; i++){
      if(used[i])
        continue;

      used[i] = true;
      operators[index] = OPERATOR[i];
      sum = Math.max(sum, mutation(index + 1, operators, used, sum, expression));
      used[i] = false;
    }

    return sum;
  }

  private long calc(char[] operators, List<String> expression){
    long sum = 0;
    List<String> exp = new ArrayList<>(expression);

    for(char operator : operators){
      for(int i = 0 ; i < exp.size() ; i++){
        if(String.valueOf(operator).equals(exp.get(i))){
          sum = processOperator(operator, Long.parseLong(exp.get(i - 1)), Long.parseLong(exp.get(i + 1)));
          exp.remove(i - 1);
          exp.remove(i - 1);
          exp.remove(i - 1);
          exp.add(i - 1, String.valueOf(sum));
          i = i - 1;
        }
      }
    }
    return sum;
  }

  private long processOperator(char operator, long a, long b){
    if(operator == '*')
      return a * b;
    if(operator == '+')
      return a + b;
    return a - b;
  }

  private List<String> createExpression(String expression){
    List<String> queue = new ArrayList<>();

    Queue<Character> numQueue = new LinkedList<>();
    for(int i = 0 ; i < expression.length() ; i++){
      char c = expression.charAt(i);
      if(isOperator(c)){
        queue.add(flush(numQueue));
        queue.add(String.valueOf(c));
      }
      else{
        numQueue.add(c);
      }
    }

    queue.add(flush(numQueue));

    return queue;
  }

  private String flush(Queue<Character> numQueue){
    StringBuilder sb = new StringBuilder();
    while(!numQueue.isEmpty()){
      sb.append(numQueue.remove());
    }
    return sb.toString();
  }

  private boolean isOperator(char c){
    for(char o : OPERATOR){
      if(c == o)
        return true;
    }

    return false;
  }
}
import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/76502
class Solution {
  public int solution(String s) {
    int available = 0;

    for(int x = 0 ; x < s.length() ; x++){
      if(check(s, x))
        available++;
    }

    return available;
  }

  private boolean check(String s, int startIdx){
    Stack<Character> stack = new Stack<>();

    int index = startIdx;
    boolean turned = false;

    while(true){
      if(turned && index == startIdx)
        break;

      char c = s.charAt(index++);

      if(isOpenChar(c)){
        stack.push(c);
      }
      else{
        if(stack.isEmpty())
          return false;

        if(!isPair(stack.pop(), c))
          return false;
      }

      if(index >= s.length()){
        index = 0;
        turned = true;
      }
    }

    return stack.isEmpty();
  }

  private boolean isOpenChar(char c){
    return c == '[' || c == '{' || c == '(';
  }

  private boolean isPair(char open, char close){
    if(open == '[' && close == ']')
      return true;
    if(open == '{' && close == '}')
      return true;
    if(open == '(' && close == ')')
      return true;
    return false;
  }
}
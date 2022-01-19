import java.util.Stack;

// https://programmers.co.kr/learn/courses/30/lessons/12973
class Solution {
  public int solution(String s) {
    Stack<Character> stack = new Stack<>();

    for(char c : s.toCharArray()){

      if(!stack.isEmpty() && c == stack.peek()){
        stack.pop();
      }
      else{
        stack.push(c);
      }
    }

    if(stack.isEmpty())
      return 1;
    return 0;
  }
}
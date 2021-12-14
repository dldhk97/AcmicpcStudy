import java.util.Stack;

// https://programmers.co.kr/learn/courses/30/lessons/60058
class Solution {
  public String solution(String p) {
    return recursive(p);
  }

  private String recursive(String s){
    if(s.isBlank())
      return "";

    String[] splited = split(s);

    if(isCorrectSequence(splited[0]))
      return splited[0] + recursive(splited[1]);

    return '(' + recursive(splited[1]) + ')' + reverse(splited[0]);
  }

  private String[] split(String s){
    int openCnt = 0;
    int closeCnt = 0;

    String[] result = new String[2];
    for(int i = 0 ; i < s.length() ; i++){
      char c = s.charAt(i);

      if(c == '(')
        openCnt++;
      else
        closeCnt++;

      if(openCnt == closeCnt){
        result[0] = s.substring(0, i + 1);
        result[1] = s.substring(i + 1);
        break;
      }
    }

    return result;
  }

  private boolean isCorrectSequence(String s){
    Stack<Character> stack = new Stack<>();

    for(char c : s.toCharArray()){
      if(c == '('){
        stack.push(c);
        continue;
      }

      if(stack.isEmpty() || stack.peek() == ')')
        return false;

      stack.pop();
    }

    return stack.isEmpty();
  }

  private String reverse(String s){
    StringBuilder sb = new StringBuilder();
    for(int i = 1 ; i < s.length() - 1 ; i++){
      if(s.charAt(i) == '(')
        sb.append(')');
      else
        sb.append('(');
    }
    return sb.toString();
  }
}
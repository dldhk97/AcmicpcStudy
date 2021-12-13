// https://programmers.co.kr/learn/courses/30/lessons/60057
class Solution {
  public int solution(String s) {
    int answer = s.length();

    for(int sequenceSize = 1 ; sequenceSize <= s.length() / 2 ; sequenceSize++){
      int compressed = compress(s, sequenceSize);

      answer = Math.min(answer, compressed);
    }
    return answer;
  }

  private int compress(String s, int sequenceSize){
    StringBuilder sb = new StringBuilder();

    String sequence = "";
    int sequenceCount = 0;

    for(int index = 0 ; index < s.length() ; index += sequenceSize){
      if(index + sequenceSize > s.length()){
        flush(sb, sequenceCount, sequence);

        sequenceCount = 0;
        sb.append(s.substring(index));
        break;
      }

      String current = s.substring(index, index + sequenceSize);

      if(!sequence.equals(current)){
        flush(sb, sequenceCount, sequence);

        sequence = current;
        sequenceCount = 0;
      }
      sequenceCount++;
    }

    flush(sb, sequenceCount, sequence);

    return sb.length();
  }

  private void flush(StringBuilder sb, int sequenceCount, String sequence){
    if(sequenceCount > 0){
      if(sequenceCount > 1)
        sb.append(sequenceCount);
      sb.append(sequence);
    }
  }
}
class Solution {

  private final char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

  public String solution(int n, int t, int m, int p) {
    StringBuilder sequence = new StringBuilder();
    for(int i = 0 ; sequence.length() <= 999999 ; i++)
      sequence.append(createNumber(n, i));

    StringBuilder sb = new StringBuilder();

    for(int i = p - 1 ; sb.length() < t ; i += m){
      sb.append(sequence.charAt(i));
    }

    return sb.toString();
  }

  private StringBuilder createNumber(int n, int number){
    if(number == 0)
      return new StringBuilder("0");

    StringBuilder sb = new StringBuilder();

    for(int i = number ; i > 0 ; ){
      int left = i % n;
      sb.insert(0, NUMBERS[left]);
      i = i / n;
    }

    return sb;
  }
}
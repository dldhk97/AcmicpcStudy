import java.util.*;

class Solution {
  public int solution(String begin, String target, String[] words) {
    boolean[] visited = new boolean[words.length];

    Map<String, Integer> map = new HashMap<>();
    for(int i = 0 ; i < words.length ; i++)
      map.put(words[i], i);

    Queue<Pair> queue = new ArrayDeque<>();
    queue.add(new Pair(begin, 0));

    while(!queue.isEmpty()){
      Pair polled = queue.poll();

      if(map.containsKey(polled.word)){
        int index = map.get(polled.word);

        if(visited[index])
          continue;
        visited[index] = true;
      }

      if(polled.word.equals(target))
        return polled.dist;

      for(String word : words){
        if(visited[map.get(word)] || !canConvert(polled.word, word))
          continue;
        queue.add(new Pair(word, polled.dist + 1));
      }
    }

    return 0;
  }

  private boolean canConvert(String original, String target){
    int diffCnt = 0;
    for(int i = 0 ; i < original.length() ; i++){
      if(original.charAt(i) != target.charAt(i)){
        diffCnt++;
        if(diffCnt > 1)
          return false;
      }
    }
    return true;
  }
}

class Pair{
  String word;
  int dist;

  public Pair(String word, int dist) {
    this.word = word;
    this.dist = dist;
  }
}
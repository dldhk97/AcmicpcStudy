import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/42890
class Solution {
  boolean[] visited;
  List<Set<Integer>> sets;

  public int solution(String[][] relation) {
    visited = new boolean[relation[0].length];
    sets = new ArrayList<>();

    recursion(relation, 0, new HashSet<>());

    return sets.size();
  }


  private void recursion(String[][] relation, int column, Set<Integer> usedColumn){
    if(usedColumn.size() > 0 && validate(relation, usedColumn)){
      addUsedColumn(usedColumn);
      return;
    }

    if(column >= relation[0].length)
      return;

    for(int i = 0 ; i < relation[0].length ; i++){
      if(visited[i])
        continue;

      visited[i] = true;
      usedColumn.add(i);

      recursion(relation, column + 1, usedColumn);

      usedColumn.remove(i);
      visited[i] = false;
    }
  }

  private boolean validate(String[][] relation, Set<Integer> usedColumn){
    Map<String, Boolean> map = new HashMap<>();

    for(int y = 0 ; y < relation.length ; y++){
      StringBuilder sb = new StringBuilder();
      for(int x : usedColumn)
        sb.append(relation[y][x]).append("-");
      String value = sb.toString();

      if(map.containsKey(value))
        return false;
      map.put(value, true);
    }
    return true;
  }

  private void addUsedColumn(Set<Integer> usedColumn){
    boolean isMinimal = true;

    Queue<Set<Integer>> remove = new LinkedList<>();
    for(Set<Integer> set : sets){
      if(usedColumn.containsAll(set)){
        isMinimal = false;
        break;
      }
      if(set.containsAll(usedColumn)){
        remove.add(set);
      }
    }

    if(isMinimal) {
      while(!remove.isEmpty())
        sets.remove(remove.poll());
      sets.add(new HashSet<>(usedColumn));
    }
  }
}
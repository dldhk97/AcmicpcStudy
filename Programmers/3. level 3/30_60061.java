import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/60061
class Solution {
  private static final int PILLAR = 0;
  private static final int BO = 1;

  private List<Part> PARTS = new ArrayList<>();

  public int[][] solution(int n, int[][] build_frame) {
    for(int[] job : build_frame){
      int x = job[0]; int y = job[1];
      int isBo = job[2];
      boolean isCreate = job[3] == 1;

      if(isCreate){
        Part p = new Part(x, y, isBo);
        PARTS.add(p);
        if(!isValid())
          PARTS.remove(p);
      }
      else{
        Part p = find(x, y, isBo);
        PARTS.remove(p);
        if(!isValid())
          PARTS.add(p);
      }
    }

    PARTS.sort(Part::compareTo);

    return PARTS.stream().map(o -> new int[]{o.x, o.y, o.isBo}).toArray(int[][]::new);
  }

  private boolean isValid(){
    for(Part part : PARTS){
      if(part.isBo == BO){
        if(find(part.x, part.y - 1, PILLAR) != null) continue;
        if(find(part.x + 1, part.y - 1, PILLAR) != null) continue;
        if(find(part.x - 1, part.y, BO) != null && find(part.x + 1, part.y, BO) != null) continue;
      }
      else{
        if(part.y == 0) continue;
        if(find(part.x, part.y, BO) != null) continue;
        if(find(part.x - 1, part.y, BO) != null) continue;
        if(find(part.x, part.y - 1, PILLAR) != null) continue;
      }
      return false;
    }

    return true;
  }

  private Part find(int x, int y, int isBo){
    for(Part p : PARTS)
      if(p.x == x && p.y == y && p.isBo == isBo)
        return p;
    return null;
  }
}

class Part implements Comparable<Part>{
  int x, y;
  int isBo;

  public Part(int x, int y, int isBo) {
    this.x = x;
    this.y = y;
    this.isBo = isBo;
  }

  @Override
  public int compareTo(Part o) {
    if(this == o)
      return 0;

    if(this.x == o.x){
      if(this.y == o.y){
        if(this.isBo == 1)
          return 1;
        return -1;
      }
      return this.y - o.y;
    }
    return this.x - o.x;
  }
}
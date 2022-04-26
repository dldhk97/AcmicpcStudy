import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/92343
class Solution {

  Map<Integer, Node> nodeMap = new HashMap<>();
  int MAX_SHEEP = 0;

  public int solution(int[] info, int[][] edges) {
    for(int i = 0 ; i < info.length ; i++)
      nodeMap.put(i, new Node(i, info[i]));

    for(int[] edge : edges){
      int parent = edge[0]; int children = edge[1];
      nodeMap.get(parent).children.add(nodeMap.get(children));
    }

    dfs(nodeMap.get(0), new LinkedList<>(), 0, 0);

    return MAX_SHEEP;
  }

  private void dfs(Node node, List<Node> nextNodes, int sheep, int wolves){
    int curSheep = sheep + (node.isWolf ? 0 : 1);
    int curWolves = wolves + (node.isWolf ? 1 : 0);

    if(curSheep <= curWolves)
      return;

    MAX_SHEEP = Math.max(MAX_SHEEP, curSheep);

    List<Node> copied = new LinkedList<>(nextNodes);
    copied.remove(node);
    copied.addAll(node.children);

    for(Node n : copied)
      dfs(n, copied, curSheep, curWolves);
  }
}

class Node{
  int index;
  boolean isWolf;

  List<Node> children = new LinkedList<>();

  public Node(int index, int isWolf) {
    this.index = index;
    this.isWolf = isWolf == 1;
  }
}
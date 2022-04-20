import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/42892
class Solution {
  public int[][] solution(int[][] nodeinfo) {
    PriorityQueue<Node> pq = new PriorityQueue<>();

    int index = 1;
    for(int[] node : nodeinfo){
      int x = node[0]; int y = node[1];
      pq.add(new Node(index++, x, y));
    }

    Node root = pq.poll();

    Queue<Node> queue = new ArrayDeque<>();
    queue.add(root);

    while(!queue.isEmpty()){
      Node cur = queue.poll();

      if(cur.leftChild != null && cur.rightChild != null)
        continue;

      if(pq.isEmpty())
        break;

      Node polled = pq.peek();
      if(!checkCondition(polled, cur))
        continue;

      if(polled.x < cur.x){
        if(cur.leftChild != null)
          continue;
        cur.leftChild = polled;

        queue.add(cur);
      }
      else{
        if(cur.rightChild != null)
          continue;
        cur.rightChild = polled;
      }

      pq.poll();
      polled.parent = cur;

      queue.add(polled);
      queue.add(polled);
    }

    int[][] result = new int[2][];
    for(int i = 0 ; i < 2 ; i++)
      result[i] = new int[nodeinfo.length];

    preOrder(root, result[0]);
    arrIndex = 0;
    postOrder(root, result[1]);
    return result;
  }

  private boolean checkCondition(Node node, Node parent){
    Node parentParent = parent.parent;

    if(parentParent != null){
      if(parent.x < parentParent.x){
        return node.x < parentParent.x && checkCondition(node, parentParent);
      }
      return node.x > parentParent.x && checkCondition(node, parentParent);
    }
    return true;
  }

  private int arrIndex = 0;
  private void preOrder(Node node, int[] arr){
    arr[arrIndex++] = node.index;

    if(node.leftChild != null)
      preOrder(node.leftChild, arr);
    if(node.rightChild != null)
      preOrder(node.rightChild, arr);
  }


  private void postOrder(Node node, int[] arr){
    if(node.leftChild != null)
      postOrder(node.leftChild, arr);
    if(node.rightChild != null)
      postOrder(node.rightChild, arr);
    arr[arrIndex++] = node.index;
  }
}

class Node implements Comparable<Node>{
  int index;
  int x, y;
  Node parent;
  Node leftChild;
  Node rightChild;

  public Node(int index, int x, int y) {
    this.index = index;
    this.x = x;
    this.y = y;
  }

  @Override
  public int compareTo(Node o) {
    if(this.y == o.y){
      if(this.x == o.x)
        return this.index - o.index;
      return this.x - o.x;
    }
    return o.y - this.y;
  }
}
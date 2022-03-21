import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/81303
class Solution {
  Node root;

  public String solution(int n, int k, String[] cmd) {
    root = createNodes(n);

    Stack<Node> deletedNodes = new Stack<>();

    Node current = findNode(n, k, root);
    for(String c : cmd){
      StringTokenizer st = new StringTokenizer(c);

      String command = st.nextToken();
      if(command.equals("C")){
        current = deleteNode(current, deletedNodes);
        continue;
      }

      if(command.equals("Z")){
        current = undo(current, deletedNodes);
        continue;
      }

      int value = Integer.parseInt(st.nextToken());

      if(command.equals("U")){
        current = moveCursor(current, -value);
        continue;
      }
      current = moveCursor(current, value);
    }

    boolean[] isAlive = aliveCheck(n);

    StringBuilder sb = new StringBuilder();
    for(boolean b : isAlive)
      if(b)
        sb.append("O");
      else
        sb.append("X");

    return sb.toString();
  }

  private Node deleteNode(Node targetNode, Stack<Node> deletedNodes){
    deletedNodes.push(targetNode);
    Node beforeNode = targetNode.before;
    Node afterNode = targetNode.after;

    if(targetNode == root)
      root = targetNode.after;

    if(beforeNode == null && afterNode == null){
      return null;
    }

    if(beforeNode != null && afterNode != null){
      beforeNode.after = afterNode;
      afterNode.before = beforeNode;

      return afterNode;
    }

    if(beforeNode != null){
      beforeNode.after = null;

      return beforeNode;
    }
    afterNode.before = null;
    return afterNode;
  }

  private Node undo(Node current, Stack<Node> deletedNodes){
    Node deleted = deletedNodes.pop();
    Node beforeNode = deleted.before;
    Node afterNode = deleted.after;

    if(root.index > deleted.index)
      root = deleted;

    if(beforeNode == null && afterNode == null){
      return deleted;
    }

    if(beforeNode != null && afterNode != null){
      beforeNode.after = deleted;
      afterNode.before = deleted;

      return current;
    }

    if(beforeNode != null){
      beforeNode.after = deleted;
      return current;
    }

    afterNode.before = deleted;
    return current;
  }

  private Node moveCursor(Node currentNode, int value){
    Node result = currentNode;
    if(value < 0){
      for(int i = 0 ; i < -value ; i++)
        result = result.before;
      return result;
    }
    for(int i = 0 ; i < value ; i++)
      result = result.after;
    return result;
  }

  private Node createNodes(int n){
    Node root = null;
    Node before = null;

    for(int i = 0 ; i < n ; i++){
      Node node = new Node(i);

      if(before != null){
        before.after = node;
        node.before = before;
      }
      before = node;

      if(root == null)
        root = node;
    }

    return root;
  }

  private Node findNode(int n, int k, Node root){
    Node cur = root;
    for(int i = 0 ; i < n ; i++){
      cur = cur.after;
      if(cur.index == k)
        return cur;
    }
    return null;
  }

  private boolean[] aliveCheck(int n){
    boolean[] isAlive = new boolean[n];
    Node cur = root;

    while(true){
      isAlive[cur.index] = true;
      cur = cur.after;
      if(cur == null)
        break;
    }
    return isAlive;
  }

}

class Node{
  int index;
  Node before;
  Node after;

  public Node(int index) {
    this.index = index;
  }
}
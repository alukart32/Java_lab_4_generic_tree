package tree;

import using_interface.*;
import java.util.Comparator;

/**
 * Tree class represents a TreeMap
 * <p>
 * Functions
 *              put     - adding a new node
 *              remove  - deleting a node with certain key
 *              print   - printing a tree
 *
 * @param <K>   type of key
 * @param <V>   type of value
 */
public class Tree<K , V> implements ITree<K, V> {
  /**
   * Node is an element of Tree. It contains pair (key, value)
   */
  class Node implements Pair<K, V>, Comparable<Node> {
    private K key;
    private V value;

    // left node
    private Node left;
    // right node
    private Node right;
    // previous node
    private Node parent;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = this.right = null;
    }

    public Node getLeft() { return left; }

    public Node getRight() {
      return right;
    }

    public Node getParent() {
          return parent;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
      return value;
    }

    public int compareTo( Node o) {
        return compareKey(key, o.key);
    }
  }

  /**
     * root of the tree
     */
  private Node root;
  /**
   * User's possible comparator
   */
  private Comparator<? super K> comparator;

  public Tree() {
    this.root= null;
  }
  /**
   * Constructor, which takes user's comparator
   * @param comparator
   */
  public Tree(Comparator<? super K> comparator) {
    this.root= null;
    this.comparator = comparator;
  }

  /**
   * Adding a new element into Tree.
   *
   * Checking for user's comparator: if it's null then it should be Comparable
   * else throw "ClassCastException"
   *
   * @param key      key of a new element
   * @param value    value of a new element
   */
  public void put(K key, V value){
      if(comparator == null){
          if(!(key instanceof Comparable)) {
              throw new ClassCastException("Comparator is invalid!!!");
          }
      }
      insert(key, value);
  }
  /**
   * Inserting a new Node in Tree by compering key.
   * Current Node will be root and then it goes down on tree
   * searching place where it could insert a node
   *
   * 1) Root is null or not? If so that it creates a new Node
   * 2) Depending on key of current Node and param key it will chose a path
   *    to go: if key > cur.key than go to the right way in a tree else to he left way.
   *    And if that node to which it goes is null than creates a new Node
   *
   * @param key       key of a new Node
   * @param value     value of a new Node
   */
  private void insert(K key, V value) {
     if (root == null)
         root = createNode(key, value, null);
     else {
         Node cur = root;

         while (cur != null){
             if (compareKey(key, cur.key) > 0){
                 if(cur.right != null)
                     cur = cur.right;
                 else {
                     cur.setRight(createNode(key, value, cur));
                     return;
                 }
             } else{
                 if (compareKey(key, cur.key) < 0){
                     if(cur.left != null)
                         cur = cur.left;
                     else {
                         cur.setLeft(createNode(key, value, cur));
                         return;
                     }
                 } else {
                     return;
                 }

             }
         }
     }
  }

  public Node getByKey(K key){
      Node tmp = findNodeByKey(root, key);
      return tmp;
  }

  /**
   * Comparing keys of Tree's nodes
   *
   * if comparator is null then using compareTo
   *
   * @param o1  first key
   * @param o2  second key
   * @return
   */
  private int compareKey(K o1, K o2){
    if(comparator == null){
        return ((Comparable)o1).compareTo(o2);
    }
    return comparator.compare(o1, o2);
  }

  /**
   * Removing from Tree a node with certain key
   *
   * If Tree is empty than send message about it
   * else removing
   *
   * @param key
   */
  public void remove(K key){
      if(!isEmpty()) {
          if (!delete(key))
              System.out.println("Not such element with key: " + key);
      }else {
          System.out.println("Tree is empty!!!");
      }
  }
  /**
   * Deleting function.
   *
   * 1) Finding position of the target node
   * 2) If it is root then delete as root
   *    or delete as node in the Tree
   *
   * @param key
   * @return
   */
  private boolean delete(K key){
      Node target = findNodeByKey(root, key);

      // not found -> no deleted
      if(target == null)
          return false;

      // target == root
      if(target.equals(root)){
          deleteRoot();
     } else {
          // target != root
          deleteNode(target);
      }

      return true;
  }
  private void deleteRoot(){
      Node tmp;
      // if there any right node from root than save in order to do not lose it
      if(root.right != null) {
          // look for the min node in right branch of target == root
          tmp = findMinNode(root.right);

          // in case right branch is simple like:
              /*
                                root
                                    -> el1
                                null <-    -> el2
               */
          if(tmp.equals(root.right)) {
              tmp.left = root.left;
              tmp.parent.right = null;
              root.right = null;
          } else {
              tmp.left = root.left;
              root.left = null;

              // if the min element has right branch than save it by changing references
              if (tmp.right != null)
                  tmp.parent.left = tmp.right;
              else
                  tmp.parent.left = null;
          }
          tmp.parent = null;
          tmp.right = root.right;

          root.right = null;

          root = tmp;
      } else {
          // tree has just left branch

          // look for the max node in left branch of target == root
          tmp = findMaxNode(root.left);


          // in case left branch is simple like:
              /*
                                root
                         el1 <-
                  el2 <-    ->  null
               */
          if(tmp.equals(root.left)) {
              root.left = null;
              tmp.parent.left = null;
          } else {
              // if the max element has left branch than save it by changing references
              if (tmp.left != null)
                  tmp.parent.right = tmp.left;
              else
                  tmp.parent.right = null;

              tmp.left = root.left;
              root.left = null;
          }
          tmp.parent = null;
          root = tmp;
      }
      root = null;
  }
  private void deleteNode(Node target){
      if (target.left != null && target.right != null) {
          Node localMax = findMaxNode(target.left);
          target.value = localMax.value;
          deleteNode(localMax);
      } else if (target.left != null) {
          // if target has a left child then put it's child on target place
          if (target.equals(target.parent.left)) {
              target.parent.left = target.left;
          } else {
              target.parent.right = target.left;
          }
      } else if (target.right != null) {
          // if target has a left child then put it's child on target place
          if (target.equals(target.parent.right)) {
              target.parent.right = target.right;
          } else {
              target.parent.left = target.right;
          }

      } else {
          // which  parents child it's
          // if target was left child then it will be null
          if (target.equals(target.parent.left))
              target.parent.left = null;
          else
              target.parent.right = null;
      }
  }

  /**
   * Creating a new Node and return it
   * @param key         key of the Node
   * @param value       value of the Node
   * @param parent      previous Node
   * @return
   */
  private Node createNode(K key, V value,Node parent){
      Node tmp = new Node(key, value);
      tmp.parent = parent;

      return tmp;
  }

  public void print(){
      if(!isEmpty())
          printTree(root);
      else
          System.out.println("Tree is empty!!!");
  }
  private void printTree(Node cur){
      if (cur != null) {
          System.out.println(cur.getKey() + " : " + cur.getValue());
          printTree(cur.left);
          printTree(cur.right);
      }
  }

  /**
   * Finding a Node by key
   * @param cur     from which Node searching
   * @param key     certain key of target Node
   * @return
   */
  private Node findNodeByKey(Node cur, K key){
      if(cur == null)
          return null;

      if(compareKey(key, cur.key) == 0)
          return cur;

      // left
      if(compareKey(key, cur.key) < 0)
          if(cur.left != null)
              return findNodeByKey(cur.left, key);
          else
              // not found
              return null;
      // right
      else
          if(cur.right != null)
              return findNodeByKey(cur.right, key);
          else
              // not found
              return null;
  }
  private Node findMaxNode(Node cur){
       while (cur.getRight() != null){
           cur = cur.getRight();
       }
       return cur;
  }
  private Node findMinNode(Node cur){
        while (cur.getLeft() != null){
            cur = cur.getLeft();
        }
        return cur;
    }

  public boolean isEmpty(){ return root==null;}
}

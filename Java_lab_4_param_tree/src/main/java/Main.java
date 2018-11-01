import some_classes.ClassA;
import tree.Tree;

public class Main {
  public static void main(String[] args){
    Tree<ClassA, Integer> tree = new Tree<>();

    ClassA a = new ClassA("a", 21);
    ClassA b = new ClassA("b", 23);
    ClassA c = new ClassA("c", 25);
    ClassA e = new ClassA("e", 27);
    ClassA h = new ClassA("h", 29);
    ClassA k = new ClassA("k", 31);
    ClassA i = new ClassA("i", 30);
    ClassA l = new ClassA("l", 33);
    ClassA j = new ClassA("j", 35);

    String as = "a";
    String bs = "b";
    String ds = "d";

    Integer i1 = 1;
    Integer i2 = 5;
    Integer i3 = -2;
    Integer i4 = 3;

    tree.put(a, 1);
    tree.put(b, 3);
    tree.put(c, 5);
    tree.put(e, 7);
    tree.put(h, 9);
    tree.put(k, 11);
    tree.put(i, 13);
    tree.put(l, 15);
    tree.put(j, 17);

    tree.print();

    tree.remove(h);

    tree.print();
  }
}
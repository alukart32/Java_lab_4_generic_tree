package some_classes;

public class ClassA implements Comparable<ClassA> {
  String name = "bbb";
  int b = 3;

  public ClassA(String name, int b) {
    this.name = name;
    this.b = b;
  }

  public String getName() {
    return name;
  }

  public int getB() {
    return b;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setB(int b) {
    this.b = b;
  }

  @Override
  public int compareTo(ClassA classA) {
    int lastCmp = name.compareTo(classA.name);
    // если совпадают name, смотрим b
    return (lastCmp != 0 ? lastCmp : String.valueOf(b).compareTo(String.valueOf(classA.b)));
  }
}



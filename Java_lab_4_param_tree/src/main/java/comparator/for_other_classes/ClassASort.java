package comparator.for_other_classes;

import some_classes.ClassA;

import java.util.Comparator;

public class ClassASort  {
  public final Comparator<ClassA> NAME_ORDER = new Comparator<ClassA>() {
    public int compare(ClassA e1, ClassA e2) {
      int nameCmp = e1.getName().compareTo(e2.getName());
      if (nameCmp != 0)
        return nameCmp;

      return (e2.getB() - e1.getB());
    }
  };
}

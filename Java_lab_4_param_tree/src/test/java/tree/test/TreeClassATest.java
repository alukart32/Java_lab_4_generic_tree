package tree.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import some_classes.ClassA;
import tree.Tree;

public class TreeClassATest {
    private Tree<ClassA, Integer> tree;

    @Before
    public void initTest() {
        Tree<String, Integer> tree = new Tree<>();
    }

    @After
    public void afterTest() {
        tree = null;
    }

    @Test
    public void putClassA() {
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

        tree.put(a, 1);
        tree.put(b, 3);
        tree.put(c, 5);
        tree.put(e, 7);
        tree.put(h, 9);
        tree.put(k, 11);
        tree.put(i, 13);
        tree.put(l, 15);
        tree.put(j, 17);

        Assert.assertEquals(i, tree.getKey(i));
        Assert.assertEquals(c, tree.getKey(c));
        Assert.assertEquals(j, tree.getKey(j));

        Assert.assertEquals(9, tree.getSize());
    }

    @Test
    public void removeClassA() {
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

        tree.put(a, 1);
        tree.put(b, 3);
        tree.put(c, 5);
        tree.put(e, 7);
        tree.put(h, 9);
        tree.put(k, 11);
        tree.put(i, 13);
        tree.put(l, 15);
        tree.put(j, 17);

        tree.remove(h);

        Assert.assertEquals(tree.getKey(h), null);
        Assert.assertNotEquals(h, tree.getKey(h));

        Assert.assertEquals(8, tree.getSize());
    }
}
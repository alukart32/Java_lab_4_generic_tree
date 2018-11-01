package tree.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import some_classes.ClassA;
import tree.Tree;

import static org.junit.Assert.*;

public class TreeStringTest {
    private Tree<String, Integer> tree;

    @Before
    public void initTest() {
        Tree<String, Integer> tree = new Tree<>();
    }

    @After
    public void afterTest() {
        tree = null;
    }

    @Test
    public void putString() {
        Tree<String, Integer> tree = new Tree<>();

        tree.put("aaa", 1);
        tree.put("aba", 3);
        tree.put("aab", 5);
        tree.put("bbb", 7);
    }

    @Test
    public void removeString() {
        Tree<String, Integer> tree = new Tree<>();

        tree.put("aaa", 1);
        tree.put("aba", 3);
        tree.put("aab", 5);
        tree.put("bbb", 7);

        tree.remove("aba");
        tree.remove("ava");
    }
}
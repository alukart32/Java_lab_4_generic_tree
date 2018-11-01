package using_interface;

import tree.Tree;

public interface ITree<K, V> {
    void put(K key, V value);
    void remove (K key);
    boolean isEmpty();
    void print();
}

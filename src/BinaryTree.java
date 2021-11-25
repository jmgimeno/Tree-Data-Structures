import java.util.Collection;
import java.util.Iterator;

public interface BinaryTree<E> extends Collection<E> {
    BinaryTree<E> getLeftCh();
    BinaryTree<E> getRightCh();
    E root();

    void removeLeftCh();
    void removeRightCh();

    int height();

    //Iterator<E> iterator(); //From Collection
    BinaryTreeIterator<E> iteratorPre();
    BinaryTreeIterator<E> iteratorIn();
    BinaryTreeIterator<E> iteratorPost();
    BinaryTreeIterator<E> iteratorLevels();

    //From Collection
    /*
    void clear();
    boolean contains(Object o);
    boolean containsAll(Collection<?> c);
    boolean equals(Object o);
    int size();
    Object[] toArray();
    boolean isEmpty();
    */
}

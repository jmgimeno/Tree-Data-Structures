package trees;

import java.util.Collection;
import java.util.NoSuchElementException;
/**
 * A binary tree is a tree data structure in which each node has at most two children,
 * which are referred to as the left child and the right child.
 *
 * @param <E>
 */
public interface BinaryTree<E> extends Collection<E> {

    /**
     * Returns the left subtree of this binary tree.
     *
     * @return the left subtree of this binary tree.
     */
    BinaryTree<E> left();

    /**
     * Returns the right subtree of this binary tree.
     *
     * @return the right subtree of this binary tree.
     */
    BinaryTree<E> right();

    /**
     * Returns the root element of this binary tree.
     *
     * @return the root element of this binary tree.
     * @throws {@link NoSuchElementException} if this binary tree is empty.
     */
    E root();

    /**
     * Removes the left subtree of this binary tree.
     */
    void removeLeft();

    /**
     * Removes the right subtree of this binary tree.
     */
    void removeRight();


    /**
     * Returns the height of this binary tree.
     *
     * @return the height of this binary tree.
     */
    int height();

    /**
     * Returns an iterator for traversing the binary tree in pre-order.
     *
     * @return an iterator for traversing the binary tree in pre-order.
     */
    BinaryTreeIterator<E> iteratorPre();

    /**
     * Returns an iterator for traversing the binary tree in in-order.
     *
     * @return an iterator for traversing the binary tree in in-order.
     */
    BinaryTreeIterator<E> iteratorIn();

    /**
     * Returns an iterator for traversing the binary tree in post-order.
     *
     * @return an iterator for traversing the binary tree in post-order.
     */
    BinaryTreeIterator<E> iteratorPost();

    /**
     * Returns an iterator for traversing the binary tree in level-order.
     *
     * @return an iterator for traversing the binary tree in level-order.
     */
    BinaryTreeIterator<E> iteratorLevels();
}

package trees;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A BinaryTree is a tree data structure which can be empty or combines an element of type E with two
 * subtrees of type BinaryTree<E> called left and right.
 *
 * @param <E> the type of elements in the binary tree
 * @author Juan Manuel Gimeno Illa
 */
public interface BinaryTree<E> {

    /**
     * Returns the root element of this binary tree.
     *
     * @return the root element of this binary tree.
     * @throws NoSuchElementException if this binary tree is empty.
     */
    E root();

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
     * Returns {@code true} if this binary tree is empty.
     *
     * @return {@code true} if this binary tree is empty.
     * @implNote As de default implementation calls size(), ee need the class to provide an
     * efficient
     * implementation of it (or override this default implementation)
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the size of this binary tree.
     *
     * @return the size of this binary tree.
     */
    int size();

    /**
     * Returns the height of this binary tree.
     *
     * @return the height of this binary tree.
     */
    int height();

    /**
     * Replaces the root element of this binary tree with the given element.
     *
     * @param newElement the new root element
     * @return the old root element
     * @throws NoSuchElementException id the tree is empty
     */
    E replaceRoot(E newElement);

    /**
     * Removes the left subtree of this binary tree.
     *
     * @throws NoSuchElementException if the tree is empty
     */
    void removeLeft();

    /**
     * Removes the right subtree of this binary tree.
     *
     * @throws NoSuchElementException if the tree is empty
     */
    void removeRight();

    /**
     * Returns an iterator for traversing the binary tree in pre-order.
     *
     * @return an iterator for traversing the binary tree in pre-order.
     */
    List<E> preOrder();

    /**
     * Returns an iterator for traversing the binary tree in in-order.
     *
     * @return an iterator for traversing the binary tree in in-order.
     */
    List<E> inOrder();

    /**
     * Returns an iterator for traversing the binary tree in post-order.
     *
     * @return an iterator for traversing the binary tree in post-order.
     */
    List<E> postOrder();

    /**
     * Returns an iterator for traversing the binary tree in level-order.
     *
     * @return an iterator for traversing the binary tree in level-order.
     */
    List<E> levelOrder();
}

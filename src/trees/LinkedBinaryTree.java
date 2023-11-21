package trees;

import java.util.*;

/**
 * A binary tree is a tree data structure in which each node has at most two children.
 * This class implements the {@link BinaryTree} interface by using a structure of linked nodes.
 *
 * @param <E> the type of elements in the binary tree
 */
public class LinkedBinaryTree<E> extends AbstractCollection<E> implements BinaryTree<E> {

    /**
     * The root of the binary tree.
     */
    private final Node<E> root;

    private static class Node<E1> {
        Node<E1> left;
        E1 element;
        Node<E1> right;
        int size;

        Node(Node<E1> left, E1 element, Node<E1> right) {
            this.left = left;
            this.element = element;
            this.right = right;
            this.size = 1 + Node.size(left) + Node.size(right);
        }

        static int size(Node<?> node) {
            return node == null ? 0 : node.size;
        }

        static boolean contains(Node<?> node, Object obj) {
            if (node == null)
                return false;
            else
                return Objects.equals(obj, node.element)
                        || contains(node.left, obj)
                        || contains(node.right, obj);
        }

        static boolean equals(Node<?> node1, Node<?> node2) {
            if (node1 == null || node2 == null)
                return node1 == node2;
            else
                return Objects.equals(node1.element, node2.element)
                        && equals(node1.left, node2.left)
                        && equals(node1.right, node2.right);
        }

        static int height(Node<?> node) {
            if (node == null)
                return 0;
            else
                return 1 + Math.max(height(node.left), height(node.right));
        }

        static <E2> List<Node<E2>> preOrder(Node<E2> node) {
            List<Node<E2>> lis = new ArrayList<>();
            preOrder(node, lis);
            return lis;
        }

        static <E2> void preOrder(Node<E2> node, List<Node<E2>> lis) {
            if (node != null) {
                lis.add(node);
                preOrder(node.left, lis);
                preOrder(node.right, lis);
            }
        }
    }

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        root = null;
    }

    /**
     * Creates a binary tree with the given element as root and the given trees as left and right subtrees.
     *
     * @param elem  the element to be used as root
     * @param left  the left subtree of the new tree. It can be empty of {@code null} if no left subtree is desired.
     * @param right the right subtree of the new tree. It can be empty of {@code null} if no right subtree is desired.
     */
    public LinkedBinaryTree(LinkedBinaryTree<E> left, E elem, LinkedBinaryTree<E> right) {
        Node<E> leftChild = left == null ? null : left.root;
        Node<E> rightChild = right == null ? null : right.root;
        root = new Node<>(leftChild, elem, rightChild);
    }

    private LinkedBinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Returns the number of elements in this binary tree.
     *
     * @return the number of elements in this binary tree.
     */
    @Override
    public int size() {
        return Node.size(root);
    }

    /**
     * Returns {@code true} if this collection contains no elements.
     *
     * @return {@code true} if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns {@code true} if this collection contains the specified element.
     * More formally, returns {@code true} if and only if this collection
     * contains at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this collection is to be tested
     * @return {@code true} if this collection contains the specified
     * element
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        return Node.contains(root, o);
    }

    /**
     * Returns {@code true} if both trees have the same elements and shape.
     *
     * @param o object to be compared for equality with this {@code LinkedBinaryTree}
     * @return {@code true} if the specified object is equal to this {@code LinkedBinaryTree}
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LinkedBinaryTree<?> bt))
            return false;

        return Node.equals(root, bt.root);
    }

    /**
     * Return the height of this binary tree.
     *
     * @return the height of this binary tree.
     */
    @Override
    public int height() {
        // If height was called often, we'd better catch it in a field (as we do with size)
        return Node.height(root);
    }

    /**
     * Returns the left subtree of this binary tree.
     *
     * @return the left subtree of this binary tree.
     */
    @Override
    public LinkedBinaryTree<E> left() {
        if (root == null)
            throw new NoSuchElementException("left child of empty tree");

        return new LinkedBinaryTree<>(root.left);
    }

    /**
     * Returns the right subtree of this binary tree.
     *
     * @return the right subtree of this binary tree.
     */
    @Override
    public LinkedBinaryTree<E> right() {
        if (root == null)
            throw new NoSuchElementException("right child of empty tree");

        return new LinkedBinaryTree<>(root.right);
    }

    /**
     * Returns the root element of this binary tree.
     *
     * @return the root element of this binary tree.
     * @throws NoSuchElementException if this binary tree is empty.
     */
    @Override
    public E root() {
        if (root == null)
            throw new NoSuchElementException("root of empty tree");

        return root.element;
    }

    /**
     * Removes the left subtree of this binary tree.
     */
    @Override
    public void removeLeft() {
        if (root == null)
            throw new NoSuchElementException("Empty tree");
        root.size -= Node.size(root.left);
        root.left = null;
    }

    /**
     * Removes the right subtree of this binary tree.
     */
    @Override
    public void removeRight() {
        if (root == null)
            throw new NoSuchElementException("Empty tree");
        root.size -= Node.size(root.right);
        root.right = null;
    }

    /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * @return an {@code Iterator} over the elements in this collection
     */
    @Override
    public Iterator<E> iterator() {
        return new PreOrderIterator();
    }

    /**
     * Returns an iterator for traversing the binary tree in pre-order.
     *
     * @return an iterator for traversing the binary tree in pre-order.
     */
    @Override
    public BinaryTreeIterator<E> preOrderIterator() {
        return new PreOrderIterator();
    }

    /**
     * Returns an iterator for traversing the binary tree in in-order.
     *
     * @return an iterator for traversing the binary tree in in-order.
     */
    @Override
    public BinaryTreeIterator<E> inOrderIterator() {
        return new InOrderIterator();
    }

    /**
     * Returns an iterator for traversing the binary tree in post-order.
     *
     * @return an iterator for traversing the binary tree in post-order.
     */
    @Override
    public BinaryTreeIterator<E> postOrderIterator() {
        return new PostOrderIterator();
    }

    /**
     * Returns an iterator for traversing the binary tree in level-order.
     *
     * @return an iterator for traversing the binary tree in level-order.
     */
    @Override
    public BinaryTreeIterator<E> levelOrderIterator() {
        return new LevelOrderIterator();
    }

    private class PreOrderIterator implements BinaryTreeIterator<E> {
        List<Node<E>> nodes;
        Iterator<Node<E>> it;
        Node<E> lastReturned;

        PreOrderIterator() {
            lastReturned = null;
            nodes = Node.preOrder(LinkedBinaryTree.this.root);
            it = nodes.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            lastReturned = it.next();
            return lastReturned.element;
        }

        @Override
        public void set(E o) {
            if (lastReturned == null)
                throw new IllegalStateException();

            lastReturned.element = o;
        }
    }

    private class InOrderIterator implements BinaryTreeIterator<E> {

        @Override
        public void set(E o) {
            throw new UnsupportedOperationException("Not Implemented"); // TODO
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not Implemented"); // TODO
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("Not Implemented"); // TODO
        }
    }

    private class PostOrderIterator implements BinaryTreeIterator<E> {
        @Override
        public void set(E o) {
            throw new UnsupportedOperationException("Not Implemented"); // TODO
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not Implemented"); // TODO
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("Not Implemented"); // TODO
        }
    }

    private class LevelOrderIterator implements BinaryTreeIterator<E> {
        @Override
        public void set(E o) {
            throw new UnsupportedOperationException("Not Implemented"); // TODO
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not Implemented"); // TODO
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("Not Implemented"); // TODO
        }
    }
}

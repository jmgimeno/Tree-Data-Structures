package trees;

import java.util.*;

/**
 * A LinkedBinaryTree is an implementation of {@link BinaryTree} that  a structure of linked nodes.
 *
 * @param <E> the type of elements in the binary tree
 * @author Juan Manuel Gimeno Illa
 * @see BinaryTree
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>, Cloneable {

    /**
     * The root of the binary tree.
     */
    private Node<E> root;

    private static class Node<E> implements Cloneable {
        Node<E> left;
        E element;
        Node<E> right;
        int size;

        Node(Node<E> left, E element, Node<E> right) {
            this.left = left;
            this.element = element;
            this.right = right;
            this.size = 1 + Node.size(left) + Node.size(right);
        }

        static int size(Node<?> node) {
            return node == null ? 0 : node.size;
        }

        static int height(Node<?> node) {
            if (node == null)
                return -1;
            else
                return 1 + Math.max(height(node.left), height(node.right));
        }

        void preOrder(List<E> result) {
            result.add(element);
            if (left != null)
                left.preOrder(result);
            if (right != null)
                right.preOrder(result);
        }

        static boolean equals(Node<?> node1, Node<?> node2) {
            if (node1 == null || node2 == null)
                return node1 == node2;
            else
                return node1.size == node2.size
                        && Objects.equals(node1.element, node2.element)
                        && equals(node1.left, node2.left)
                        && equals(node1.right, node2.right);
        }

        @Override
        public String toString() {
            if (left == null && right == null) return "mkLBT(%s)".formatted(element);
            else if (left == null) return "mkLBT(%s, %s)".formatted(element, right);
            else if (right == null) return "mkLBT(%s, %s)".formatted(left, element);
            else return "mkLBT(%s, %s, %s)".formatted(left, element, right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, element, right);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Node<E> clone()  {
            try {
                Node<E> clone = (Node<E>) super.clone();
                if (left != null) clone.left = left.clone();
                if (right != null) clone.right = right.clone();
                return clone;
            } catch (CloneNotSupportedException e) {
                // this shouldn't happen, since we are Cloneable
                throw new InternalError(e);
            }
        }
    }

    // Constructors

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        root = null;
    }

    /**
     * Creates a binary tree with the given element as root and the given trees as left and right
     * subtrees.
     *
     * @param elem  the element to be used as root
     * @param left  the left subtree of the new tree. It can be {@code null} if an empty left
     *              subtree is desired.
     * @param right the right subtree of the new tree. It can {@code null} if an empty right
     *              subtree is desired.
     */
    public LinkedBinaryTree(LinkedBinaryTree<E> left, E elem, LinkedBinaryTree<E> right) {
        Node<E> leftChild = left == null ? null : left.root;
        Node<E> rightChild = right == null ? null : right.root;
        root = new Node<>(leftChild, elem, rightChild);
    }

    private LinkedBinaryTree(Node<E> root) {
        this.root = root;
    }

    // Accessors

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

    // Properties

    /**
     * Returns {@code true} if this binary tree is empty.
     *
     * @return {@code true} if this binary tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
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
     * Return the height of this binary tree.
     *
     * @return the height of this binary tree.
     */
    @Override
    public int height() {
        // If height was called often, we'd better catch it in a field (as we do with size)
        return Node.height(root);
    }

    // Modifiers

    @Override
    public E replaceRoot(E newElement) {
        if (root == null)
            throw new NoSuchElementException("the empty tree has no root to replace");
        E oldElement = root.element;
        root.element = newElement;
        return oldElement;
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

    // Traversals

    /**
     * Returns an iterator for traversing the binary tree in pre-order.
     *
     * @return an iterator for traversing the binary tree in pre-order.
     */
    @Override
    public List<E> preOrder() {
        List<E> result = new ArrayList<>();
        if (root != null)
            root.preOrder(result);
        return result;
    }


    /**
     * Returns an iterator for traversing the binary tree in in-order.
     *
     * @return an iterator for traversing the binary tree in in-order.
     */
    @Override
    public List<E> inOrder() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Returns an iterator for traversing the binary tree in post-order.
     *
     * @return an iterator for traversing the binary tree in post-order.
     */
    @Override
    public List<E> postOrder() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Returns an iterator for traversing the binary tree in level-order.
     *
     * @return an iterator for traversing the binary tree in level-order.
     */
    @Override
    public List<E> levelOrder() {
        throw new UnsupportedOperationException("TODO");

    }

    // Methods overridden from Object

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

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    @Override
    public String toString() {
        return root == null ? "mkLBT()" : root.toString();
    }

    // Cloneable implementation

    @Override
    @SuppressWarnings("unchecked")
    public LinkedBinaryTree<E> clone()  {
        if (root == null)
            // An empty tree is immutable so we can return this
            return this;
        try {
            LinkedBinaryTree<E> clone = (LinkedBinaryTree<E>) super.clone();
            clone.root = root.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    // Smart constructors

    private static final BinaryTree<?> UNTYPED_UNIQUE_EMPTY_TREE = new LinkedBinaryTree<>();

    /**
     * Returns an empty binary tree.
     *
     * @param <E> the type of elements in the binary tree
     * @return an empty binary tree
     */
    @SuppressWarnings("unchecked")
    public static <E> LinkedBinaryTree<E> mkLBT() {
        // Only works because the empty tree is immutable !!!
        return (LinkedBinaryTree<E>) UNTYPED_UNIQUE_EMPTY_TREE;
    }

    /**
     * Returns a binary tree with the given element as root.
     *
     * @param elem the element to be used as root
     * @param <E>  the type of elements in the binary tree
     * @return a binary tree with the given element as root and left and right empty
     */
    public static <E> LinkedBinaryTree<E> mkLBT(E elem) {
        return new LinkedBinaryTree<>(null, elem, null);
    }

    /**
     * Returns a binary tree with the given element as root and the given tree as left
     *
     * @param left the left subtree of the new tree.
     * @param elem the element to be used as root
     * @param <E>  the type of elements in the binary tree
     * @return a binary tree with the given element as root and the given tree as left and right
     * empty
     */
    public static <E> LinkedBinaryTree<E> mkLBT(LinkedBinaryTree<E> left, E elem) {
        return new LinkedBinaryTree<>(left, elem, null);
    }

    /**
     * Returns a binary tree with the given element as root and the given tree as right
     *
     * @param elem  the element to be used as root
     * @param right the left subtree of the new tree.
     * @param <E>   the type of elements in the binary tree
     * @return a binary tree with the given element as root and the given tree as right and left
     * empty
     */
    public static <E> LinkedBinaryTree<E> mkLBT(E elem, LinkedBinaryTree<E> right) {
        return new LinkedBinaryTree<>(null, elem, right);
    }

    /**
     * Returns a binary tree with the given element as root and the given trees as left and right
     *
     * @param <E>   the type of elements in the binary tree
     * @param left  the left subtree of the new tree.
     * @param elem  the element to be used as root
     * @param right the left subtree of the new tree.
     * @return a binary tree with the given element as root and the given trees as left and right
     */
    public static <E> LinkedBinaryTree<E> mkLBT(LinkedBinaryTree<E> left, E elem,
                                                LinkedBinaryTree<E> right) {
        return new LinkedBinaryTree<>(left, elem, right);
    }
}

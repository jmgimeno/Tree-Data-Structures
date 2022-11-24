import java.util.*;

public class LinkedBinaryTree<E> extends AbstractCollection<E> implements BinaryTree<E> {

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

        /*
        An equivalent form would be using an explicit generic instead of the wildcard:

        static <E2> int height(Node<E2> node) {
            if (node == null)
                return 0;
            else
                return 1 + Math.max(height(node.left), height(node.right));
        }

        */

        static <E2> List<Node<E2>> preorder(Node<E2> node) {
            List<Node<E2>> lis = new ArrayList<>();
            preorder(node, lis);
            return lis;
        }

        static <E2> void preorder(Node<E2> node, List<Node<E2>> lis) {
            if (node != null) {
                lis.add(node);
                preorder(node.left, lis);
                preorder(node.right, lis);
            }
        }
    }

    public LinkedBinaryTree() {
        root = null;
    }

    public LinkedBinaryTree(LinkedBinaryTree<E> left, E elem, LinkedBinaryTree<E> right) {
        Node<E> leftChild = left == null ? null : left.root;
        Node<E> rightChild = right == null ? null : right.root;
        root = new Node<>(leftChild, elem, rightChild);
    }

    private LinkedBinaryTree(Node<E> root) {
        this.root = root;
    }

    @Override
    public int size() {
        return Node.size(root);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object o) {
        return Node.contains(root, o);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LinkedBinaryTree<?>))
            return false;

        LinkedBinaryTree<?> bt = (LinkedBinaryTree<?>) o;
        return Node.equals(root, bt.root);
    }

    // If height was called often, we'd better catch it in a field (as we do with size)
    @Override
    public int height() {
        return Node.height(root);
    }

    @Override
    public LinkedBinaryTree<E> left() {
        if (root == null)
            throw new NoSuchElementException("left child of empty tree");

        return new LinkedBinaryTree<>(root.left);
    }

    @Override
    public LinkedBinaryTree<E> right() {
        if (root == null)
            throw new NoSuchElementException("right child of empty tree");

        return new LinkedBinaryTree<>(root.right);
    }

    @Override
    public E root() {
        if (root == null)
            throw new NoSuchElementException("root of empty tree");

        return root.element;
    }

    @Override
    public void removeLeft() {
        if (root == null)
            throw new NoSuchElementException("Empty tree");
        root.size -= Node.size(root.left);
        root.left = null;
    }

    @Override
    public void removeRight() {
        if (root == null)
            throw new NoSuchElementException("Empty tree");
        root.size -= Node.size(root.right);
        root.right = null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Preorder();
    }

    @Override
    public BinaryTreeIterator<E> iteratorPre() {
        return new Preorder();
    }

    @Override
    public BinaryTreeIterator<E> iteratorIn() {
        return new Inorder();
    }

    @Override
    public BinaryTreeIterator<E> iteratorPost() {
        return new Postorder();
    }

    @Override
    public BinaryTreeIterator<E> iteratorLevels() {
        return new Levels();
    }

    private class Preorder implements BinaryTreeIterator<E> {
        List<Node<E>> listTree;
        Iterator<Node<E>> it;
        Node<E> lastReturned;

        Preorder() {
            lastReturned = null;
            listTree = Node.preorder(LinkedBinaryTree.this.root);
            it = listTree.iterator();
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

    private class Inorder implements BinaryTreeIterator<E> {

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

    private class Postorder implements BinaryTreeIterator<E> {
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

    private class Levels implements BinaryTreeIterator<E> {
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

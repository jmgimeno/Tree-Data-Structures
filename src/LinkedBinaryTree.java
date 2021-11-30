import java.util.*;

public class LinkedBinaryTree<E> extends AbstractCollection<E> implements BinaryTree<E> {

    Node<E> root;
    int size;

    private static class Node<E> {
        Node<E> left;
        E element;
        Node<E> right;

        Node(Node<E> left, E element, Node<E> right) {
            this.left = left;
            this.element = element;
            this.right = right;
        }
    }

    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    public LinkedBinaryTree(LinkedBinaryTree<E> left, E elem, LinkedBinaryTree<E> right) {
        Node<E> leftCh = (left == null ? null : left.root);
        Node<E> rightCh = (right == null ? null : right.root);

        int leftS = (left == null ? 0 : left.size);
        int rightS = (right == null ? 0 : right.size);

        root = new Node<>(leftCh, elem, rightCh);
        size = 1 + leftS + rightS;
    }

    private LinkedBinaryTree(Node<E> root) {
        this.root = root;
        this.size = size(root);
    }

    @Override
    public int size() {
        return this.size;
    }

    private int size(Node<E> node) {
        if (node == null)
            return 0;
        else
            return size(node.left) + size(node.right) + 1;
    }

    @Override
    public boolean contains(Object o) {
        return contains(o, root);
    }

    private boolean contains(Object o, Node<E> node) {
        if (node == null)
            return false;
        else
            return Objects.equals(o, node.element)
                    || contains(o, node.left)
                    || contains(o, node.right);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LinkedBinaryTree<?>))
            return false;

        LinkedBinaryTree<?> bt = (LinkedBinaryTree<?>) o;
        return equals(root, bt.root);
    }

    private boolean equals(Node<?> node1, Node<?> node2) {
        if (node1 == null || node2 == null)
            return node1 == node2;

        return Objects.equals(node1.element, node2.element)
                && equals(node1.left, node2.left)
                && equals(node1.right, node2.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null)
            return 0;
        else
            return Math.max(height(node.left), height(node.right)) + 1;
    }

    @Override
    public LinkedBinaryTree<E> getLeftCh() {
        if (root == null)
            throw new NoSuchElementException("left child of empty tree");

        return new LinkedBinaryTree<>(root.left);
    }

    @Override
    public LinkedBinaryTree<E> getRightCh() {
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
    public void removeLeftCh() {
        if (root != null) {
            size = size(root.right) + 1;
            root.left = null;
        }
    }

    @Override
    public void removeRightCh() {
        if (root != null) {
            size = size(root.left) + 1;
            root.right = null;
        }
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
            listTree = preorder(LinkedBinaryTree.this.root);
            it = listTree.iterator();
        }

        private List<Node<E>> preorder(Node<E> node) {
            List<Node<E>> lis = new ArrayList<>();

            if (node != null) {
                lis.add(node);
                listTree.addAll(preorder(node.left));
                listTree.addAll(preorder(node.right));
            }

            return lis;
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

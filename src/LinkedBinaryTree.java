import java.util.*;

public class LinkedBinaryTree<E> extends AbstractCollection<E> implements BinaryTree<E> {

    Node<E> root;
    int size;

    private static class Node<E> {
        E element;
        Node<E> left;
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
        return size(root);
    }

    private int size(Node<E> root) {
        if (root == null)
            return 0;
        else
            return size(root.left) + size(root.right) + 1;
    }

    @Override
    public boolean contains(Object o) {
        return contains(o, root);
    }

    private boolean contains(Object o, Node<E> root) {
        if (root == null)
            return false;
        else
            return Objects.equals(root.element, o) || contains(o, root.left) || contains(o, root.right);
    }

    @Override
    public boolean equals(Object o) {
        if (o==null || !(o instanceof LinkedBinaryTree))
            return false;

        LinkedBinaryTree<E> bt = (LinkedBinaryTree<E>) o;
        return equals(bt.root, root);
    }

    private boolean equals(Node<E> root1, Node<E> root2) {
        if(root1 == null || root2 == null)
            return root1 == root2;

        return root1.element.equals(root2.element) && equals(root1.left, root2.left) && equals(root1.right, root2.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node<E> root) {
        if(root == null)
            return 0;
        else
            return Math.max(height(root.left), height(root.right)) + 1;
    }

    @Override
    public LinkedBinaryTree<E> getLeftCh() {
        if (root == null)
            throw new NoSuchElementException();

        return new LinkedBinaryTree<E>(root.left);
    }

    @Override
    public LinkedBinaryTree<E> getRightCh() {
        if (root == null)
            throw new NoSuchElementException();

        return new LinkedBinaryTree<E>(root.right);
    }

    @Override
    public E root() {
        if (root == null)
            throw new NoSuchElementException();

        return (E) root.element;
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
        return new PreBinTreeItr();
    }

    @Override
    public BinaryTreeIterator<E> iteratorPre() {
        return new PreBinTreeItr();
    }

    @Override
    public BinaryTreeIterator<E> iteratorIn() {
        return new InBinTreeItr();  //To be implemented
    }

    @Override
    public BinaryTreeIterator<E> iteratorPost() {
        return new ProBinTreeItr(); //To be implemented
    }

    @Override
    public BinaryTreeIterator<E> iteratorLevels() {
        return new ProBinTreeItr(); //To be implemented
    }

    private class PreBinTreeItr<E> implements BinaryTreeIterator<E> {
        List<Node<E>> listTree;
        Iterator<Node<E>> it;
        Node<E> lastReturned;

        PreBinTreeItr() {
            lastReturned = null;
            listTree = preorder(LinkedBinaryTree.this.root);
            it = listTree.iterator();
        }

        private List<Node<E>> preorder(Node<E> root) {
            List<Node<E>> lis = new ArrayList<>();

            if (root != null){
                lis.add(root);
                listTree.addAll(preorder(root.left));
                listTree.addAll((preorder(root.right));
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

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

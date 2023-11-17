package trees;

import java.util.Iterator;

public interface BinaryTreeIterator<E> extends Iterator<E> {

    /**
     * Replaces the last element returned by {@link #next}
     * with the specified element (optional operation).
     *
     * @param e the element with which to replace the last element returned by
     *          {@code next}
     * @throws UnsupportedOperationException if the {@code set} operation
     *         is not supported by this list iterator
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws IllegalArgumentException if some aspect of the specified
     *         element prevents it from being added to this list
     * @throws IllegalStateException if  {@code next} have not been called,
     */
    void set(E e);
}

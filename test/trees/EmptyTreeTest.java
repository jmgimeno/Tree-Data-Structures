package trees;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static trees.LinkedBinaryTree.mkLBT;

@DisplayName("An empty tree should")
class EmptyTreeTest {

    private static final BinaryTree<Integer> emptyTree = mkLBT();

    @Test
    @DisplayName("return true when calling isEmpty()")
    void test1() {
        assertTrue(emptyTree.isEmpty());
    }

    @Test
    @DisplayName("return 0 when calling size()")
    void test2() {
        assertEquals(0, emptyTree.size());
    }

    @Test
    @DisplayName("return -1 when calling height()")
    void test3() {
        assertEquals(-1, emptyTree.height());
    }

    @Test
    @DisplayName("be equal to another empty tree")
    void test4() {
        // WE do not rely on the shared instance of the empty tree
        BinaryTree<Integer> anotherEmptyTree = new LinkedBinaryTree<>();
        assertEquals(emptyTree, anotherEmptyTree);
    }

    @Test
    @DisplayName("be different from a non-empty tree")
    void test5() {
        BinaryTree<Integer> nonEmptyTree = mkLBT(1);
        assertNotEquals(emptyTree, nonEmptyTree);
        assertNotEquals(nonEmptyTree, emptyTree);
    }

    @Test
    @DisplayName("throw a NoSuchElementException when calling root()")
    void test6() {
        assertThrows(NoSuchElementException.class, () -> {
            emptyTree.root();
        });
    }

    @Test
    @DisplayName("throw a NoSuchElementException when calling left()")
    void test7() {
        assertThrows(NoSuchElementException.class, () -> {
            emptyTree.left();
        });
    }

    @Test
    @DisplayName("throw a NoSuchElementException when calling right()")
    void test8() {
        assertThrows(NoSuchElementException.class, () -> {
            emptyTree.right();
        });
    }

    @Test
    @DisplayName("throw a NoSuchElementException when calling removeLeft()")
    void test10() {
        assertThrows(NoSuchElementException.class, () -> {
            emptyTree.removeLeft();
        });
    }

    @Test
    @DisplayName("throw a NoSuchElementException when calling removeRight()")
    void test11() {
        assertThrows(NoSuchElementException.class, () -> {
            emptyTree.removeRight();
        });
    }

    @Test
    @DisplayName("throw a NoSuchElementException when calling replaceRoot()")
    void test12() {
        BinaryTree<Integer> emptyIntegerTree = new LinkedBinaryTree<>();
        assertThrows(NoSuchElementException.class, () -> {
            emptyIntegerTree.replaceRoot(42);
        });
    }

    @Test
    @DisplayName("return the empty list when calling preOrder()")
    void test15() {
        assertTrue(emptyTree.preOrder().isEmpty());
    }

    @Disabled("This test is disabled because the inOrder() method is not implemented yet.")
    @Test
    @DisplayName("return the empty list when calling inOrder()")
    void test16() {
        assertTrue(emptyTree.inOrder().isEmpty());
    }

    @Disabled("This test is disabled because the postOrder() method is not implemented yet.")
    @Test
    @DisplayName("return the empty list when calling postOrder()")
    void test17() {
        assertTrue(emptyTree.postOrder().isEmpty());
    }

    @Disabled("This test is disabled because the levelOrder() method is not implemented yet.")
    @Test
    @DisplayName("return the empty list when calling levelOrder()")
    void test18() {
        assertTrue(emptyTree.levelOrder().isEmpty());
    }

    @Test
    @DisplayName("return the corresponding mkLBT call when calling toString()")
    void test19() {
        assertEquals("mkLBT()", emptyTree.toString());
    }
}

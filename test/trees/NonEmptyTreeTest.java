package trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static trees.LinkedBinaryTree.mkLBT;

@DisplayName("A non-empty tree ")
public class NonEmptyTreeTest {


    LinkedBinaryTree<Integer> single, lefty, righty, triad, complex;

    @BeforeEach
    void setUp() {
        // 1
        single = mkLBT(1);
        //     1
        //    /
        //   2
        lefty = mkLBT(mkLBT(2), 1);
        //     1
        //      \
        //       3
        righty = mkLBT(1, mkLBT(3));
        //     1
        //    / \
        //   2  3
        triad = mkLBT(mkLBT(2), 1, mkLBT(3));
        //        4
        //       / \
        //     1    5
        //    / \
        //   2  3
        complex = mkLBT(mkLBT(mkLBT(2), 1, mkLBT(3)), 4, mkLBT(5));
    }

    @Test
    @DisplayName("not be empty")
    void test1() {
        assertFalse(complex.isEmpty());
    }

    @Test
    @DisplayName("have its size computed")
    void test2() {
        assertEquals(5, complex.size());
    }

    @Test
    @DisplayName("have its height computed")
    void test3() {
        assertEquals(2, complex.height());
    }

    @Test
    @DisplayName("be different from another tree")
    void test4() {
        assertNotEquals(complex, mkLBT());
        assertNotEquals(mkLBT(), complex);
        assertNotEquals(complex, triad);
        assertNotEquals(triad, complex);
    }

    @Test
    @DisplayName(("be equal to itself"))
    void test5() {
        assertEquals(single, single);
        assertEquals(lefty, lefty);
        assertEquals(righty, righty);
        assertEquals(triad, triad);
        assertEquals(complex, complex);
    }

    @Test
    @DisplayName("be equal to another version of itself")
    void test6() {
        BinaryTree<Integer> anotherComplex = mkLBT(mkLBT(mkLBT(2), 1, mkLBT(3)), 4, mkLBT(5));
        assertEquals(complex, anotherComplex);
    }

    @Test
    @DisplayName("be able to return its root")
    void test7() {
        assertEquals(4, complex.root());
    }

    @Test
    @DisplayName("be able to return its left child")
    void test8() {
        assertEquals(triad, complex.left());
    }

    @Test
    @DisplayName("be able to return its right child")
    void test9() {
        assertEquals(mkLBT(5), complex.right());
    }

    @Test
    @DisplayName("leave the left subtree empty after calling removeLeft()")
    void test11() {
        complex.removeLeft();
        assertEquals(mkLBT(4, mkLBT(5)), complex);
    }

    @Test
    @DisplayName("leave the right subtree empty after calling removeRight()")
    void test12() {
        complex.removeRight();
        assertEquals(mkLBT(mkLBT(mkLBT(2), 1, mkLBT(3)), 4), complex);
    }

    @Test
    @DisplayName("be able to have its root replaced and the old root returned")
    void test13() {
        var old = triad.replaceRoot(5);
        assertEquals(1, old);
        assertEquals(mkLBT(mkLBT(2), 5, mkLBT(3)), triad);
    }

    @Test
    @DisplayName("be able to return its traversal in preOrder")
    void test16() {
        assertEquals(List.of(4, 1, 2, 3, 5), complex.preOrder());
    }

    @Disabled("This test is disabled because the inOrder() method is not implemented yet.")
    @Test
    @DisplayName("be able to return its traversal in inOrder")
    void test17() {
        fail("not implemented yet");
    }

    @Disabled("This test is disabled because the postOrder() method is not implemented yet.")
    @Test
    @DisplayName("be able to return its traversal in postOrder")
    void test18() {
        fail("not implemented yet");
    }

    @Disabled("This test is disabled because the levelOrder() method is not implemented yet.")
    @Test
    @DisplayName("be able to return its traversal in levelOrder")
    void test19() {
        fail("not implemented yet");
    }

    @Test
    @DisplayName("return the corresponding mkLBT call when calling toString()")
    void test20() {
        assertEquals("mkLBT(1)", single.toString());
        assertEquals("mkLBT(mkLBT(2), 1)", lefty.toString());
        assertEquals("mkLBT(1, mkLBT(3))", righty.toString());
        assertEquals("mkLBT(mkLBT(2), 1, mkLBT(3))", triad.toString());
        assertEquals("mkLBT(mkLBT(mkLBT(2), 1, mkLBT(3)), 4, mkLBT(5))", complex.toString());
    }
}

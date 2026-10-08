
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * basic practice questions for BT
 */
public class BTBasicQuestions {

    // 1. Return the number of nodes in the tree.
    public static int size(BT.Node root) {
        if (root == null)
            return 0;
        return size(root.left) + size(root.right) + 1;
    }

    // 2. Return the sum of all node values.
    public static int sum(BT.Node root) {
        if (root == null)
            return 0;
        return sum(root.left) + sum(root.right) + root.val;
    }

    // 3. Return the maximum value. zero for empty tree.
    public static int max(BT.Node root) {
        if (root == null)
            return 0;
        int left = max(root.left);
        int right = max(root.right);
        int childMax = Math.max(left, right);

        return Math.max(childMax, root.val);
    }

    // 4. Return the number of edges on the longest root-to-leaf path.
    // Use -1 for an empty tree, so a leaf has height 0.
    public static int height(BT.Node root) {
        if (root == null)
            return -1;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // 5. Return true if target occurs in the tree.
    public static boolean contains(BT.Node root, int target) {
        if (root == null)
            return false;
        if (root.val == target)
            return true;

        return contains(root.left, target) || contains(root.right, target);
    }

    // 6. Print preorder, inorder, and postorder traversals (one method each).
    public static void preorder(BT.Node root) {
        if (root == null)
            return;
        System.out.println(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(BT.Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.println(root.val + " ");
        inorder(root.right);
    }

    public static void postorder(BT.Node root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.val + " ");
    }

    // 7. Print values level by level, with a line break between levels.
    public static void levelOrder(BT.Node root) {
        if (root == null)
            return;
        Deque<BT.Node> deq = new ArrayDeque<>();
        deq.offer(root);
        while (!deq.isEmpty()) {
            int size = deq.size();
            for (int i = 0; i < size; i++) {
                BT.Node node = deq.poll();
                System.out.println(node.val + " ");
                if (node.left != null)
                    deq.offer(node.left);
                if (node.right != null)
                    deq.offer(node.right);
            }
            System.out.println("\n");
        }
    }

    // 8. Return the maximum number of nodes present at any one level.
    // The empty tree has width 0.
    public static int width(BT.Node root) {
        if (root == null)
            return 0;
        Deque<BT.Node> deq = new ArrayDeque<>();
        deq.offer(root);
        int res = 0;
        while (!deq.isEmpty()) {
            int size = deq.size();
            res = Math.max(res, size);
            for (int i = 0; i < size; i++) {
                BT.Node node = deq.poll();
                if (node.left != null)
                    deq.offer(node.left);
                if (node.right != null)
                    deq.offer(node.right);
            }
        }
        return res;
    }

    // 9. Return the number of leaf nodes.
    public static int leafCount(BT.Node root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null) {
            return 1;
        }
        return leafCount(root.left) + leafCount(root.right);
    }

    // 10. Return true if the tree is height-balanced.
    public static boolean isBalanced(BT.Node root) {
        return checkH(root) != -1;
    }

    static int checkH(BT.Node root) {
        if (root == null)
            return 0;
        int left = checkH(root.left);
        if (left == -1) {
            return -1;
        }

        int right = checkH(root.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return 1 + Math.max(left, right);
    }

    // 11. Return the diameter in edges: the longest path between any two nodes.
    public static int diameter(BT.Node root) {
        int[] res = new int[1];
        getD(root, res);
        return res[0];
    }

    static int getD(BT.Node root, int[] res) {
        if (root == null)
            return 0;
        int left = getD(root.left, res);
        int right = getD(root.right, res);
        int h = left + right;
        res[0] = Math.max(res[0], h);

        return 1 + Math.max(left, right);
    }

    // 12. Return true if two trees have identical shape and values.
    public static boolean isSameTree(BT.Node a, BT.Node b) {
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;

        if (a.val != b.val)
            return false;

        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    // 13. Return the lowest common ancestor of a and b, assuming both values
    // are present and values are unique. Return null for an empty tree.
    public static BT.Node lowestCommonAncestor(BT.Node root, int a, int b) {
        if (root == null)
            return null;

        if (root.val == a || root.val == b)
            return root;

        BT.Node left = lowestCommonAncestor(root.left, a, b);
        BT.Node right = lowestCommonAncestor(root.right, a, b);

        if (left != null && right != null)
            return root;
        return left == null ? right : left;
    }

    // 14. Invert the tree in place and return its root.
    public static BT.Node invert(BT.Node root) {
        if (root == null)
            return null;

        BT.Node left = invert(root.left);
        BT.Node right = invert(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * Run with: java BTBasicQuestions. These cases are meant to be run as you
     * implement the methods; failures identify the method and case.
     */
    public static void main(String[] args) {
        BT.Node empty = null;
        BT.Node one = node(8);
        BT.Node sample = sampleTree(); // 4
                                       // / \
                                       // 2 6
                                       // / \ \
                                       // 1 3 7

        checkEq(0, size(empty), "size empty");
        checkEq(1, size(one), "size one node");
        checkEq(6, size(sample), "size multi-level");

        checkEq(0, sum(empty), "sum empty");
        checkEq(8, sum(one), "sum one node");
        checkEq(23, sum(sample), "sum multi-level");

        checkEq(8, max(one), "max one node");
        checkEq(7, max(sample), "max with root not maximum");

        checkEq(-1, height(empty), "height empty");
        checkEq(0, height(one), "height one node");
        checkEq(2, height(sample), "height multi-level");

        check(!contains(empty, 5), "contains empty");
        check(contains(sample, 3), "contains present value");
        check(!contains(sample, 9), "contains missing value");

        checkOutput("8", () -> preorder(one), "preorder one node");
        checkOutput("4 2 1 3 6 7", () -> preorder(sample), "preorder sample");
        checkOutput("8", () -> inorder(one), "inorder one node");
        checkOutput("1 2 3 4 6 7", () -> inorder(sample), "inorder sample");
        checkOutput("8", () -> postorder(one), "postorder one node");
        checkOutput("1 3 2 7 6 4", () -> postorder(sample), "postorder sample");

        checkOutput("", () -> levelOrder(empty), "level order empty");
        checkOutput("8", () -> levelOrder(one), "level order one node");
        checkOutput("4 2 6 1 3 7", () -> levelOrder(sample), "level order sample");

        checkEq(0, width(empty), "width empty");
        checkEq(1, width(one), "width one node");
        checkEq(3, width(sample), "width sample");

        checkEq(0, leafCount(empty), "leaf count empty");
        checkEq(1, leafCount(one), "leaf count one node");
        checkEq(3, leafCount(sample), "leaf count sample");

        check(isBalanced(empty), "balanced empty");
        check(isBalanced(sample), "balanced sample");
        check(!isBalanced(node(1, node(2, node(3, null, null), null), null)), "unbalanced chain");

        checkEq(0, diameter(empty), "diameter empty");
        checkEq(0, diameter(one), "diameter one node");
        checkEq(4, diameter(sample), "diameter sample");

        check(isSameTree(empty, null), "same empty trees");
        check(isSameTree(sample, sampleTree()), "same shape and values");
        check(!isSameTree(sample,
                node(4, node(2, node(1, null, null), node(3, null, null)), node(6, null, node(8, null, null)))),
                "different value");

        BT.Node lca = lowestCommonAncestor(sample, 1, 3);
        check(lca != null && lca.val == 2, "LCA in same subtree");
        lca = lowestCommonAncestor(sample, 1, 7);
        check(lca != null && lca.val == 4, "LCA across subtrees");
        check(lowestCommonAncestor(empty, 1, 2) == null, "LCA empty");

        BT.Node inverted = invert(sampleTree());
        check(inverted != null && inverted.left.val == 6 && inverted.right.val == 2, "invert root children");
        check(inverted.left.left.val == 7 && inverted.right.right.val == 1, "invert descendants");
        check(invert(empty) == null, "invert empty");

        System.out.println("All BTBasicQuestions cases passed.");
    }

    private static BT.Node sampleTree() {
        return node(4,
                node(2, node(1), node(3)),
                node(6, null, node(7)));
    }

    private static BT.Node node(int value) {
        return new BT.Node(value);
    }

    private static BT.Node node(int value, BT.Node left, BT.Node right) {
        return new BT.Node(value, left, right);
    }

    private static void check(boolean passed, String name) {
        if (!passed)
            throw new AssertionError(name);
    }

    private static void checkEq(int expected, int actual, String name) {
        if (expected != actual) {
            throw new AssertionError(name + ": expected " + expected + ", got " + actual);
        }
    }

    private static void checkOutput(String expected, Runnable action, String name) {
        PrintStream original = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(captured));
            action.run();
        } finally {
            System.setOut(original);
        }
        String actual = captured.toString().trim().replaceAll("\\s+", " ");
        String normalizedExpected = expected.trim().replaceAll("\\s+", " ");
        if (!normalizedExpected.equals(actual)) {
            throw new AssertionError(name + ": expected [" + normalizedExpected + "], got [" + actual + "]");
        }
    }
}

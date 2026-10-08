/**
 * Challenge: maximum path sum.
 *
 * A path can start and end at any nodes, must follow parent-child edges, and
 * cannot visit a node more than once. Return the largest possible sum. Values
 * may be negative, so the best path is not necessarily the whole tree or a
 * root-to-leaf path. Assume the tree is non-empty.
 */
class BTMaximumPathSum {
    public static int maximumPathSum(BT.Node root) {
        int[] res = new int[] { Integer.MIN_VALUE };
        helper(root, res);
        return res[0];
    }

    private static int helper(BT.Node root, int[] res) {
        if (root == null)
            return 0;

        int left = Math.max(0, helper(root.left, res));
        int right = Math.max(0, helper(root.right, res));

        int sum = left + right + root.val;
        res[0] = Math.max(res[0], sum);

        return Math.max(left, right) + root.val;
    }

    // Example to reason through:
    // -10
    // / \
    // 9 20
    // / \
    // 15 7
    // Expected answer: 42 (15 -> 20 -> 7).
    public static void main(String[] args) {
        BT.Node root = new BT.Node(-10,
                new BT.Node(9),
                new BT.Node(20, new BT.Node(15), new BT.Node(7)));
        int res = maximumPathSum(root);
        System.out.println(res);
    }
}

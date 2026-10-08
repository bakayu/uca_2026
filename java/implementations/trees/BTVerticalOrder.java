import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.TreeMap;

/**
 * Challenge: vertical order traversal.
 *
 * Group nodes by horizontal distance from the root (root is column 0; left
 * decreases the column and right increases it). Return columns from left to
 * right, and nodes within each column from top to bottom. For nodes at the
 * same row and column, preserve left-to-right BFS order.
 */
class BTVerticalOrder {
    class Pair {
        BT.Node node;
        int col;

        public Pair(BT.Node node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalOrder(BT.Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Deque<Pair> deq = new ArrayDeque<>();

        Pair rootPair = new Pair(root, 0);
        deq.offer(rootPair);
        while (!deq.isEmpty()) {
            int size = deq.size();
            for (int i = 0; i < size; i++) {
                Pair pair = deq.poll();
                BT.Node node = pair.node;
                int col = pair.col;

                map.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);

                if (node.left != null)
                    deq.offer(new Pair(node.left, col - 1));
                if (node.right != null)
                    deq.offer(new Pair(node.right, col + 1));
            }
        }

        for (List<Integer> val : map.values()) {
            res.add(val);
        }

        return res;
    }

    // For the tree below, the answer should be:
    // [[4], [2], [1, 5], [3, 6], [7]]
    // 1
    // / \
    // 2 3
    // / \ \
    // 4 5 7
    // \
    // 6
    public static void main(String[] args) {
        BTVerticalOrder solution = new BTVerticalOrder();

        BT.Node example = node(1,
                node(2, node(4), node(5, null, node(6))),
                node(3, null, node(7)));
        checkEquals(columns(java.util.Arrays.asList(
                values(4), values(2), values(1, 5), values(3, 6), values(7))),
                solution.verticalOrder(example), "prompt example");

        checkEquals(new ArrayList<>(), solution.verticalOrder(null), "empty tree");
        checkEquals(columns(java.util.Arrays.asList(values(9))),
                solution.verticalOrder(node(9)), "single node");

        BT.Node rightChain = node(1, null, node(2, null, node(3)));
        checkEquals(columns(java.util.Arrays.asList(values(1), values(2), values(3))),
                solution.verticalOrder(rightChain), "right chain");

        System.out.println("All BTVerticalOrder cases passed.");
    }

    private static BT.Node node(int value) {
        return new BT.Node(value);
    }

    private static BT.Node node(int value, BT.Node left, BT.Node right) {
        return new BT.Node(value, left, right);
    }

    private static List<Integer> values(Integer... values) {
        return new ArrayList<>(java.util.Arrays.asList(values));
    }

    private static List<List<Integer>> columns(List<List<Integer>> columns) {
        return new ArrayList<>(columns);
    }

    private static void checkEquals(List<List<Integer>> expected,
            List<List<Integer>> actual,
            String caseName) {
        if (!expected.equals(actual)) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}

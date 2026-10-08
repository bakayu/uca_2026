import java.util.List;

/**
 * Challenge: serialize and deserialize a binary tree.
 *
 * Use preorder traversal and an explicit null marker (for example, "#") so
 * that different tree shapes cannot produce the same encoding. Define how
 * values and markers are separated. Your decoder should consume the entire
 * encoding and reject malformed input cleanly.
 */
public class BTSerializeDeserialize {
    private static final String NULL = "#";

    // Return a sequence that fully describes the tree, including null children.
    public static List<String> serialize(BT.Node root) {
        // TODO
        return null;
    }

    // Rebuild the same tree from serialize(root)'s output.
    public static BT.Node deserialize(List<String> data) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        BT.Node original = new BT.Node(1,
                new BT.Node(2, new BT.Node(4), null),
                new BT.Node(3, new BT.Node(-5), new BT.Node(6)));

        List<String> encoded = serialize(original);
        BT.Node reconstructed = deserialize(encoded);

        if (!sameTree(original, reconstructed)) {
            throw new AssertionError("Deserialized tree does not match the original");
        }
        System.out.println("Serialization round-trip passed.");
    }

    private static boolean sameTree(BT.Node a, BT.Node b) {
        if (a == null || b == null) return a == b;
        return a.val == b.val
                && sameTree(a.left, b.left)
                && sameTree(a.right, b.right);
    }
}

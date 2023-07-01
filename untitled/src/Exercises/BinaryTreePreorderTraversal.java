package Exercises;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Order of operations:
 * (N) Process n itself.
 * (L) Recursively traverse its left subtree. When this step is finished, we are back at n again.
 * (R) Recursively traverse its right subtree. When this step is finished, we are back at n again.
 */
public class BinaryTreePreorderTraversal {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        var root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        null
                ),
                new  TreeNode(3,
                        new TreeNode(5,
                                new TreeNode(7),
                                new TreeNode(8)
                        ),
                        new TreeNode(6)
                )
        );
        System.out.println(reverseIterative(root));
    }
    public static List<Integer> recursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        traverse(root, result);
        return result;
    }
    private static void traverse(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        traverse(node.left, list);
        traverse(node.right, list);
    }

    public static List<Integer> iterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        // N L R
        // Process N first
        // Then go through all on left until NULL
        // Process N
        // Then go right
        // Process N
        while (!stack.isEmpty() || node != null) {
            while(node != null) {
                result.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

        return result;
    }
    // Reverse traversal means going Right to left
    public static List<Integer> reverseIterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                result.add(node.val); // operation
                stack.push(node); // add to stack
                node = node.right; // traverse next
            }
            node = stack.pop();
            node = node.left;
        }
        return result;
    }
}

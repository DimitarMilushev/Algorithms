package Exercises;

import com.sun.source.tree.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Order of operations:
 * (L) Recursively traverse its left subtree. When this step is finished, we are back at n again.
 * (R) Recursively traverse its right subtree. When this step is finished, we are back at n again.
 * (N) Process n itself.
 */
public class BinaryTreePostorderTraversal {
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
                new TreeNode(3,
                        new TreeNode(5,
                                new TreeNode(7),
                                new TreeNode(8)
                        ),
                        new TreeNode(6)
                )
        );
        System.out.println(iterative(root));
    }
    public static List<Integer> recursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        traverse(root, result);
        return result;
    }
    private static void traverse(TreeNode node, List<Integer> list) {
        if (node == null) return;
        traverse(node.left, list);
        traverse(node.right, list);
        list.add(node.val);
    }

    public static List<Integer> iterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {
            while(node != null) {
                if (!result.contains(node.val)) {
                    stack.push(node);
                }
                node = (node.left != null) ? node.left : node.right;
            }
            node = stack.pop();
            result.add(node.val);
            System.out.println(Arrays.toString(result.toArray()));
        }

        return result;
    }
}

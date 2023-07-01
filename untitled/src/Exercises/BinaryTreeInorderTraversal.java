package Exercises;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Order of operations:
 * (L) Recursively traverse its left subtree. When this step is finished, we are back at n again.
 * (N) Process n itself.
 * (R) Recursively traverse its right subtree. When this step is finished, we are back at n again.
 */
public class BinaryTreeInorderTraversal {
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
                null,
                new TreeNode(2,
                        new TreeNode(3),
                        null));
        System.out.println(iterative(root));
    }
    // Apply DFS approach
    public static List<Integer> recursive(TreeNode root) {
        List<Integer> inorderList = new LinkedList<>();
        /**
         * Algorithm Inorder(tree)
         *
         *     Traverse the left subtree, i.e., call Inorder(left->subtree)
         *     Visit the root.
         *     Traverse the right subtree, i.e., call Inorder(right->subtree
         */
        traverseInOrder(root, inorderList);

        return inorderList.stream().toList();
    }
    public static void traverseInOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        traverseInOrder(node.left, list);
        list.add(node.val);
        traverseInOrder(node.right, list);
    }

    public static List<Integer> iterative(TreeNode root) {
        List<Integer> inorderList = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            // Traverse all left-side nodes
            if (node != null) {
                stack.push(node);
                node = node.left;
                continue;
            }
            // Now pop last one
            node = stack.pop();
            // Add it
            inorderList.add(node.val);
            // Start traversing from the right one
            node = node.right;
        }
        return inorderList.stream().toList();
    }
}


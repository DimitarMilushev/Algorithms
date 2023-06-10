package Exercises;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by
 * continuously following the next pointer. Internally, pos is used to denote the index of the node that
 * tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        final int[] values = new int[] {3, 2, 0, -4, 5, 6, 7, 3, 2, 0, -4, 5, 6, 7, 3, 2, 0, -4, 5, 6, 7};
        final int pos = 1;
        LinkedList<ListNode> list = new LinkedList<>();
        ListNode cycleNode = null;
        ListNode node = new ListNode(values[0]);
        list.add(node);
        for (int i = 1; i < values.length; i++) {
            node.next = new ListNode(values[i], node);
            list.add(node.next);
            if (i == pos) {
                cycleNode = node;
            }
            node = node.next;
        }
        node.next = cycleNode;
        System.out.println(HareTortoiseSolution( list.getFirst()));
    }

    /**
     * Premise: We're utilizing the Java HashSet to handle duplicates. We add each node and when add returns FALSE,
     * that means we've encountered a collision, which proves a cycle exists.
     * Time Complexity: O(n), we traverse the LinkedList only once
     * Space Complexity: O(n), because we add all the LinkedList items.
     */
    private static Boolean HeuristicSolution(ListNode head) {
        ListNode node = head;
        Set<ListNode> items = new HashSet<>();
        for(; node != null; node = node.next) {
            if (!items.add(node)) {
                // Found a cycle
                return true;
            }
        }
        return false;
    }

    /**
     * Floyd's cycle finding algorithm
     * Premise: This is a two pointer algorithm. First (fast) pointer skips 2 nodes, while the second (slow) only 1.
     * If the fast pointer does a cycle, he'll reach the slow pointer, which proves the cycle.
     * Time Complexity: O(n)
     * Space Complexity: O(1), because we're just keeping references to two pointers
     */
    private static Boolean HareTortoiseSolution(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        int count = 0;
        while(fast != null) {
            System.out.println(++count);
            slow = slow.next;
            fast = fast.next;
            if (fast == null) break;
            fast = fast.next;
            if (fast == slow) {
                // Found a cycle
                return true;
            }
        }
        return false;
    }
}

//Definition for singly-linked list.
 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }
  }

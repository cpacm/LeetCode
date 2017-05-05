import java.util.HashMap;

/**
 * Remove Nth Node From End of List
 */
public class RemoveEndNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        System.out.println(removeNthFromEnd(head, 1));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        int len = 0;
        ListNode dummy = new ListNode(-1), pt = dummy;
        dummy.next = head;

        while (pt.next != null) {
            pt = pt.next;
            len++;
        }
        pt = dummy;
        for (int cnt = len - n; cnt > 0; cnt--) pt = pt.next;
        if (pt.next != null) {
            pt.next = pt.next.next;
        }
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

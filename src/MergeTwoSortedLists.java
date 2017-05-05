/**
 * Created by cpacm on 2017/5/4.
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode head = new ListNode(8);
        head.next = new ListNode(12);
        ListNode head2 = new ListNode(6);
        head2.next = new ListNode(10);
        head2.next.next = new ListNode(15);
        System.out.println(mergeTwoLists(head, head2));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                head.next = l2;
                break;
            } else if (l2 == null) {
                head.next = l1;
                break;
            }
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
                head = head.next;
            } else {
                head.next = l2;
                l2 = l2.next;
                head = head.next;
            }
        }
        return temp.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

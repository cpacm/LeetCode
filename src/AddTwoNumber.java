/**
 * Created by cpacm on 2016/12/30.
 */
public class AddTwoNumber {

    public static void main(String[] args) {
        ListNode L1 = new ListNode(5);
        ListNode L2 = new ListNode(5);

        System.out.println(addTwoNumbers(L1, L2));
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            if (next != null) {
                return val + next.toString() + " ";
            } else {
                return val + " ";
            }
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            if (l1 == null && l2 == null)
                return null;
            if (l1 == null)
                l1 = new ListNode(0);
            if (l2 == null)
                l2 = new ListNode(0);
        }
        ListNode result = new ListNode(0);
        result.val = (l1.val + l2.val) % 10;
        int off = (l1.val + l2.val) / 10;
        if (l1.next != null) {
            l1.next.val += off;
        } else if ((l2.next != null)) {
            l2.next.val += off;
        } else if (off > 0) {
            l1.next = new ListNode(off);
        }
        result.next = addTwoNumbers(l1.next, l2.next);
        return result;
    }

}

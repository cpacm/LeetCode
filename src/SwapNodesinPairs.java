/**
 * Created by cpacm on 2017/5/9.
 */
public class SwapNodesinPairs {

    public static void main(String[] args) {
        ListNode head = new ListNode(8);
        head.next = new ListNode(12);
        head.next.next = new ListNode(10);
        head.next.next.next = new ListNode(15);
        head.next.next.next.next = new ListNode(17);
        head.next.next.next.next.next = new ListNode(20);
        System.out.println(swapPairs(head));

    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode v = new ListNode(-1);
        v.next = head;
        ListNode res = v;
        while (v.next != null && v.next.next != null) {
            ListNode temp = v.next;
            ListNode l1 = temp.next;
            ListNode l2 = temp.next.next;
            l1.next = temp;
            temp.next = l2;
            v.next = l1;
            v = v.next.next;
        }
        return res.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }
}

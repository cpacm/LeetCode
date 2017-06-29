/**
 * Created by cpacm on 2017/6/29.
 */
public class RotateList {

    public static void main(String[] args) {
        ListNode head = new ListNode(10);
        head.next = new ListNode(11);
        head.next.next = new ListNode(12);
        head.next.next.next = new ListNode(13);
        head.next.next.next.next = new ListNode(14);
        head.next.next.next.next.next = new ListNode(15);
        head.next.next.next.next.next.next = new ListNode(16);
        System.out.println(rotateRight(head, 4));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int count = 1;
        ListNode temp = head;
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
            count++;
        }
        k = k % count;
        int index = count - k;
        if (index <= 0) {
            return head;
        }
        index--;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        last.next = head;
        head = temp.next;
        temp.next = null;
        return head;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

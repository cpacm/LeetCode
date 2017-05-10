import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpacm on 2017/5/10.
 */
public class ReverseNodesInkGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        System.out.println(reverseKGroup(head, 2));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        List<ListNode> nodeList = new ArrayList<>();
        while (head != null) {
            nodeList.add(head);
            head = head.next;
        }
        int size = nodeList.size();
        int start = 0;
        while (size >= k) {
            swapNodes(nodeList, start, start + k - 1);
            size = size - k;
            start = start + k;
        }
        ListNode v = nodeList.get(0);
        ListNode temp = v;
        for (int i = 1; i < nodeList.size(); i++) {
            temp.next = nodeList.get(i);
            temp = temp.next;
        }
        temp.next = null;
        return v;
    }

    public static void swapNodes(List<ListNode> list, int start, int end) {
        if (end >= list.size()) {
            return;
        }
        while (start < end) {
            ListNode temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
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

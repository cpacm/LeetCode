import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cpacm on 2017/5/5.
 */
public class MergekSortedLists {

    public static void main(String[] args) {
        ListNode head = new ListNode(8);
        head.next = new ListNode(12);
        ListNode head2 = new ListNode(6);
        head2.next = new ListNode(10);
        head2.next.next = new ListNode(15);
        ListNode[] listNodes = new ListNode[]{head, head2};
        System.out.println(mergeKLists(listNodes));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        List<ListNode> list = new ArrayList<>();
        Collections.addAll(list, lists);
        return mergeKLists(list);
    }

    public static ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);

        int length = lists.size();
        int mid = (length - 1) / 2;
        ListNode l1 = mergeKLists(lists.subList(0, mid + 1));
        ListNode l2 = mergeKLists(lists.subList(mid + 1, length));
        return merge2Lists(l1, l2);
    }

    public static ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 != null) {
            temp.next = l1;
        } else if (l2 != null) {
            temp.next = l2;
        }
        return head.next;
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

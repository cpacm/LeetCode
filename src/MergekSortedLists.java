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
        if (lists == null || lists.length == 0) return null;
        List<ListNode> list = new ArrayList<>();
        for (ListNode node : lists) {
            if (node != null) {
                list.add(node);
            }
        }
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        while (list.size() > 0) {
            temp.next = mergeHeadLists(list);
            temp = temp.next;
            System.out.println(temp.val);
        }
        return result.next;
    }

    public static ListNode mergeHeadLists(List<ListNode> list) {
        int index = 0;
        int minValue = list.get(index).val;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).val < minValue) {
                minValue = list.get(i).val;
                index = i;
            }
        }
        ListNode node = list.get(index);
        ListNode temp = new ListNode(node.val);
        node = node.next;
        if (node == null) {
            list.remove(index);
        } else {
            list.set(index, node);
        }
        return temp;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

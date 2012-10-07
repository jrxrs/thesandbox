package questions;

/**
 * Given the below definition for a LinkedList class, implement the static methods:
 *
 * LinkedList reverseIteratively(LinkedList list)
 * LinkedList reverseRecursively(LinkedList list)
 */
public class LinkedList
{
    class Node
    {
        public Object data;
        public Node next;
    }

    public Node head = null;

    public void addNode(Object data) {
        if (head == null) {
            head = new Node();
            head.data = data;
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = new Node();
            n.next.data = data;
        }
    }

    public void printList() {
        int nodeCount = 0;
        Node n = head;
        while (n != null) {
            System.out.println("Node " + nodeCount + " = " + n.data);
            nodeCount++;
            n = n.next;
        }
    }

    public static LinkedList reverseIteratively(LinkedList list) {
        // Reverse list iteratively
        LinkedList rev = new LinkedList();

        return list;
    }

    public static LinkedList reverseRecursively(LinkedList list) {
        // Reverse list recursively
        if (list.head == null || list.head.next == null) {
            return list;
        }

        Node curHead = list.head;
        list.head = curHead.next;
        curHead.next = null;
        list = reverseRecursively(list);
        Node n = list.head;
        while (n.next != null) {
            n = n.next;
        }
        n.next = curHead;

        return list;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        list.printList();
        LinkedList rev = reverseRecursively(list);
        rev.printList();
    }
}

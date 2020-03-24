package com.aduan.study.algorithm;

/**
 * 单项链表逆转
 */
public class SingleListReverse {

    public static void main(String[] args) {
        Node<String> head = new Node<>("A");
        Node<String> node1 = new Node<>("B");
        Node<String> node2 = new Node<>("C");
        Node<String> node3 = new Node<>("D");
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        printNode(head);

        head = reverse(head);

        printNode(head);
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        //逆序链表的头结点
        Node newHead = null;
        Node next;
        while (head != null) {
            //1. 记录下next
            next = head.next;
            //2. head的next赋值为新的newHead
            head.next = newHead;
            //3. 把新的newHead赋值为head
            newHead = head;
            //4. 重新赋值head的值（丢失head前面的数据）
            head = next;
        }
        return newHead;
    }

    private static void printNode(Node<String> head) {
        if (head == null) {
            System.out.println("Null");
            return;
        }
        StringBuilder sb = new StringBuilder(head.value);
        Node node = head.next;
        while (node != null) {
            sb.append(" -> " + node.value);
            node = node.next;
        }
        System.out.println(sb.toString());
    }

    static class Node<T> {
        public T value;
        public Node next;

        public Node(T value) {
            this.value = value;
        }
    }
}

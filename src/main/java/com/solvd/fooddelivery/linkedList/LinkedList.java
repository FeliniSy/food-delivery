package com.solvd.fooddelivery.linkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LinkedList<T> {

    private static final Logger log = LogManager.getLogger(LinkedList.class);

    public static class Node<U> {

        U data;
        Node<U> next;

        public Node(U data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;

    public LinkedList() {
        head = null;
    }

    void append(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
    }

    void display() {
        Node<T> current = head;
        while (current != null) {
            log.info("{} -> ", current.data);
            current = current.next;
        }
        log.info("none");
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        list.display();
    }
}

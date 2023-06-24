package LOGICA;

public class Set<T> {
    private Node<T> head;

    public void add(T element) {
        if (!contains(element)) {
            Node<T> newNode = new Node<>(element);
            newNode.setNext(head);
            head = newNode;
        }
    }

    public void remove(T element) {
        if (head == null) {
            return;
        }

        if (head.getData().equals(element)) {
            head = head.getNext();
            return;
        }

        Node<T> prevNode = head;
        Node<T> currNode = head.getNext();

        while (currNode != null) {
            if (currNode.getData().equals(element)) {
                prevNode.setNext(currNode.getNext());
                return;
            }

            prevNode = currNode;
            currNode = currNode.getNext();
        }
    }

    public boolean contains(T element) {
        Node<T> currNode = head;
        while (currNode != null) {
            if (currNode.getData().equals(element)) {
                return true;
            }
            currNode = currNode.getNext();
        }
        return false;
    }

    public int size() {
        int count = 0;
        Node<T> currNode = head;
        while (currNode != null) {
            count++;
            currNode = currNode.getNext();
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
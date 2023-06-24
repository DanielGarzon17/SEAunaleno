package LOGICA;

public class Map<K, V> {
    private Node<K, V> head;

    public void put(K key, V value) {
        if (head == null) {
            head = new Node<>(key, value);
        } else {
            Node<K, V> currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(new Node<>(key, value));
        }
    }

    public V get(K key) {
        Node<K, V> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public boolean containsKey(K key) {
        Node<K, V> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public boolean containsValue(V value) {
        Node<K, V> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getValue().equals(value)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public void remove(K key) {
        if (head == null) {
            return;
        }

        if (head.getKey().equals(key)) {
            head = head.getNext();
            return;
        }

        Node<K, V> previousNode = head;
        Node<K, V> currentNode = head.getNext();
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                previousNode.setNext(currentNode.getNext());
                return;
            }
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }
    }

    public void clear() {
        head = null;
    }

    public int size() {
        int count = 0;
        Node<K, V> currentNode = head;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.getNext();
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Set<K> keySet(){
        Set<K> llaves = new Set<>();
        Node<K, V> currNode = head;
        while(currNode.next != null){
            llaves.add(currNode.key);
            currNode = currNode.next;
        }
        return llaves;
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }
}
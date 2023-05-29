package LOGICA;

public class Queue<T> {

    private node head;
    private node tail;

    public Queue() {
        head = null;
        tail = null;
    }

    public boolean empty() {
        return head == null;
    }

    public void push(T key) {
        node nodito = new node(key, null);
        if (empty()) {
            head = nodito;
        } else {
            tail.next = nodito;
        }
        tail = nodito;
    }

    public T pop() {
        if (head == null) {
            // La cola está vacía, no se puede extraer ningún elemento
            return null; // O podrías lanzar una excepción apropiada, como NoSuchElementException
        }
        
        T data = head.getData();
        head = head.getNext();
        
        if (head == null) {
            // Si head es null después de extraer el elemento, significa que la cola está vacía
            tail = null;
        }
        
        return data;
    }

    public void set(T dato) {
        if (!empty()) {
            head.key = dato;
        } else {
            System.out.println("La lista esta vacia");
        }
    }

    public T peek() throws Exception {
        if (!empty()) {
            return head.key;
        } else {
            throw new Exception("La lista esta vacia");
        }
    }

    /*
    public T[] getAll() {
        
    }
     */
    public class node {

        T key;
        node next;

        public node(T key, node next) {
            this.key = key;
            this.next = next;
        }

        public node(T key) {
            this(key, null);
        }

        public T getData() {
            return key;
        }
        
        public node getNext() {
            return next;
        }
    }
}

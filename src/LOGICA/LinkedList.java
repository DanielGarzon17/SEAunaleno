package LOGICA;

public class LinkedList<T> {

    private node<T> head;
    private node<T> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean empty() {
        return head == null;
    }

    public void pushBack(T key) {
        node<T> nodito = new node<>(key, null);
        if (empty()) {
            head = nodito;
        } else {
            tail.next = nodito;
        }
        tail = nodito;
        size++;
    }

    public void pushFront(T key) {
        node<T> nodito = new node<>(key, null);
        if (empty()) {
            tail = nodito;
        } else {
            nodito.next = head;
        }
        head = nodito;
        size++;
    }

    public void push(T key, int posicion) {
        if (posicion <= size && posicion >= 0) {
            node<T> nodito = new node<>(key, null);
            if (posicion == 0) {
                pushFront(key);
            } else {
                if (posicion == size) {
                    pushBack(key);
                } else {
                    node<T> iterador = head;
                    for (int i = 1; i < posicion; i++) {
                        iterador = iterador.next;
                    }
                    nodito.next = iterador.next;
                    iterador.next = nodito;
                    size++;
                }
            }
        } else {
            System.out.println("ERROR, posicion invalida");
        }
    }

    public void popFront() {
        if (!empty()) {
            head = head.next;
            if (empty()) {
                tail = null;
            }
            size--;
        } else {
            System.out.println("ERROR, la lista esta vacia");
        }

    }

    public void popBack() {
        if (!empty()) {
            if (head.next != null) {
                node<T> nodito = head;
                while (nodito.next.next != null) {
                    nodito = nodito.next;
                }
                nodito.next = null;
                tail = nodito;
            } else {
                head = null;
                tail = null;
            }
            size--;
        } else {
            System.out.println("ERROR, la lista esta vacia");
        }

    }

    public void pop(int posicion) {
        if (posicion < size && posicion >= 0) {
            if (posicion == 0) {
                popFront();
            } else {
                if (posicion == size - 1) {
                    popBack();
                } else {
                    node<T> iterador = head;
                    for (int i = 1; i < posicion; i++) {
                        iterador = iterador.next;
                    }
                    iterador.next = iterador.next.next;
                    size--;
                }
            }
        } else {
            System.out.println("ERROR, posicion invalida");
        }
    }

    public void set(T dato, int posicion) {
        if (posicion < size && posicion >= 0) {
            node<T> iterador = head;
            for (int i = 0; i < posicion; i++) {
                iterador = iterador.next;
            }
            iterador.key = dato;
        } else {
            System.out.println("ERROR, posicion invalida");
        }
    }

    public T get(int posicion) throws Exception {
        if (posicion < size && posicion >= 0) {
            node<T> iterador = head;
            for (int i = 0; i < posicion; i++) {
                iterador = iterador.next;
            }
            return iterador.key;
        } else {
            throw new Exception("La lista esta vacia");
        }
    }
   
    public T[] getAll() throws Exception {
        if (!empty()) {
            T[] todos = (T[]) new Object[size];
            node<T> iterador = head;
            for (int i = 0; i < size; i++) {
                todos[i] = iterador.key;
                iterador = iterador.next;
            }
            return todos;
        } else {
            throw new Exception("ERROR, la lista esta vacia");
        }
    }

    public void imprimir() {
        node<T> iterador = head;
        while (iterador != null) {
            System.out.print(iterador.key + " ");
            iterador = iterador.next;
        }
        System.out.print("\n");
    }

    public class node<T> {

        T key;
        node next;

        public node(T key, node next) {
            this.key = key;
            this.next = next;
        }

        public node(T key) {
            this(key, null);
        }
    }
    
}

package LOGICA;

public class DoubleLinkedList<T> {

    node head, tail;
    int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean empty() {
        return head == null;
    }

    public void pushBack(T key) {
        node<T> nodito = new node<>(key, null, null);
        if (empty()) {
            head = nodito;
        } else {
            nodito.izquierda = tail;
            tail.derecha = nodito;
        }
        tail = nodito;
        size++;
    }

    public void pushFront(T key) {
        node<T> nodito = new node<>(key, null, null);
        if (empty()) {
            tail = nodito;
        } else {
            head.izquierda = nodito;
            nodito.derecha = head;
        }
        head = nodito;
        size++;
    }

    public void push(T key, int posicion) {
        if (posicion <= size && posicion >= 0) {

            if (posicion == 0) {
                pushFront(key);
            } else {
                if (posicion == size) {
                    pushBack(key);
                } else {
                    node<T> nodito = new node<>(key, null, null);
                    node<T> iterador = head;
                    for (int i = 1; i < posicion; i++) {
                        iterador = iterador.derecha;
                    }
                    nodito.derecha = iterador.derecha;
                    iterador.derecha.izquierda = nodito;
                    nodito.izquierda = iterador;
                    iterador.derecha = nodito;
                    size++;
                }
            }
        } else {
            System.out.println("ERROR, posicion invalida");
        }
    }

    public void popFront() {
        if (!empty()) {
            if (head.derecha != null) {
                head.derecha.izquierda = null;
            }
            head = head.derecha;
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
            if (tail.izquierda != null) {
                tail.izquierda.derecha = null;
            }
            tail = tail.izquierda;
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
                        iterador = iterador.derecha;
                    }
                    iterador.derecha.derecha.izquierda = iterador;
                    iterador.derecha = iterador.derecha.derecha;
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
                iterador = iterador.izquierda;
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
                iterador = iterador.izquierda;
            }
            return iterador.key;
        } else {
            throw new Exception("La lista esta vacia");
        }
    }

    public void imprimirDesdeCabeza() {
        node ejem = head;
        while (ejem != null) {
            System.out.print(ejem.key + " ");
            ejem = ejem.derecha;
        }
        System.out.println("");
    }

    public void imprimirDesdeCola() {
        node ejem = tail;
        while (ejem != null) {
            System.out.print(ejem.key + " ");
            ejem = ejem.izquierda;
        }
        System.out.println("");
    }

    public class node<T> {

        T key;
        node izquierda, derecha;

        public node(T key, node izquierda, node derecha) {
            this.key = key;
            this.izquierda = izquierda;
            this.derecha = derecha;
        }

        public node(T key) {
            this(key, null, null);
        }

    }
}

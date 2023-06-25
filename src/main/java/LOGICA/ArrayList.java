package LOGICA;

public class ArrayList<T> {

    private T[] list;
    public int capacity, size;

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.list = (T[]) new Object[capacity];
    }

    public ArrayList() {
        this(20);
    }

    public boolean vacio() {
        return size == 0;
    }

    public boolean lleno() {
        return size == capacity;
    }

    public void pushBack(T dato) {
        if (!lleno()) {
            list[size] = dato;
            size++;
        } else {
            System.out.println("ERROR, capacidad llena");
        }
    }

    public void push(T dato, int posicion) {
        if (posicion <= size && posicion >= 0 && !lleno()) {
            for (int i = size; i >= posicion; i--) {
                if (i == posicion) {
                    list[i] = dato;
                } else {
                    list[i] = list[i - 1];
                }
            }
            size++;
        } else {
            System.out.println("ERROR, posicion no valida o capacidad llena");
        }
    }

    public void pushFront(T dato) {
        if (!lleno()) {
            for (int i = size; i > 0; i--) {
                list[i] = list[i - 1];
            }
            list[0] = dato;
            size++;
        } else {
            System.out.println("ERROR, capacidad llena");
        }
    }

    public void set(T dato, int posicion) {
        if (posicion < size) {
            list[posicion] = dato;
        } else {
            System.out.println("ERROR, posicion invalida");
        }
    }

    public void pop(int posicion) {
        if (posicion < size && posicion >= 0 && !vacio()) {
            size--;
            for (int i = posicion; i < size; i++) {
                list[i] = list[i + 1];
            }
        } else {
            System.out.println("ERROR, posicion invalida");
        }
    }

    public void popFront() {
        if (!vacio()) {
            size--;
            for (int i = 0; i < size; i++) {
                list[i] = list[i + 1];
            }
        } else {
            System.out.println("ERROR, lista vacia");
        }
    }

    // public void popBack() {
    //     if (!vacio()) {
    //         size--;
    //     } else {
    //         System.out.println("ERROR, lista vacia");
    //     }
    //     System.out.println("popback");
    // }

    // public T popBack() {
    //     if (vacio()) {
    //         return null; // Retorna null si el ArrayList está vacío
    //     }
    //     int lastIndex = size - 1;
    //     try{
    //     T lastElement = get(lastIndex);
    //     pop(lastIndex);
    //     return lastElement;
    // }


    public T get(int posicion) throws Exception {
        if (posicion < size && posicion >= 0) {
            return list[posicion];
        } else {
            throw new Exception("ERROR, posicion invalida");
        }
    }
}
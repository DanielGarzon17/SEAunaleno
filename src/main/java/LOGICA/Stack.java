/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LOGICA;

/**
 *
 * @author delog
 */

public class Stack<T> {
    private Object[] items;
    private int top;
    private int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public void push(T item) {
        if (isFull()) {
            throw new RuntimeException("Stack is full.");
        }
        // Agrega el elemento en la posición top + 1
        items[++top] = item;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        // Elimina el elemento en la posición top y devuelve el valor
        T item = (T) items[top];
        items[top--] = null;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        // Devuelve el elemento en la posición top
        return (T) items[top];
    }
    
    public void set(T item) {//setea ultimo valor, o el top
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        // Establece el valor del elemento en la posición del tope (top)
        items[top] = item;
    }
}

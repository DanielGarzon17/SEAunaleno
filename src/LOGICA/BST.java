package LOGICA;

import DATOS.Usuario;

public class BST {
    node root;

    public BST() {
        this.root = null;
    }

    public node find(String nombre, node nodoActual) {
        if (nodoActual.key.getNombre().compareTo(nombre) == 0) {
            return nodoActual;
        } else if (nodoActual.key.getNombre().compareTo(nombre) > 0) { // nodo actual > nombre
            if (nodoActual.izquierda != null) {
                return find(nombre, nodoActual.izquierda);
            }
            return nodoActual;
        } else if (nodoActual.key.getNombre().compareTo(nombre) < 0) {
            if (nodoActual.derecha != null) {
                return find(nombre, nodoActual.derecha);
            }
            return nodoActual;
        } else {
            return null;
        }
    }

    public node next(node nodoActual) {
        if (nodoActual.derecha != null) {
            return leftDescendant(nodoActual.derecha);
        } else {
            return rightAncestor(nodoActual);
        }
    }

    public node leftDescendant(node nodoActual) {
        if (nodoActual.izquierda == null) {
            return nodoActual;
        } else {
            return leftDescendant(nodoActual.izquierda);
        }
    }

    public node rightAncestor(node nodoActual) {
        if (nodoActual.key.getNombre().compareTo(nodoActual.padre.key.getNombre()) < 0) {
            return nodoActual.padre;
        } else {
            return rightAncestor(nodoActual.padre);
        }
    }

    public LinkedList<node> rangeSearch(String inicio, String fin) {
        LinkedList<node> retorna = new LinkedList<>();
        node nodito = find(inicio, root);
        while (nodito.key.getNombre().compareTo(fin) < 0) {
            if (nodito.key.getNombre().compareTo(inicio) >= 0) {
                retorna.pushBack(nodito);
            }
            nodito = next(nodito);
        }
        return retorna;
    }

    public void insert(Usuario key) {
        if (root != null) {
            node padre = find(key.getNombre(), root);
            if (padre.key.getNombre().compareTo(key.getNombre()) > 0) {
                padre.izquierda = new node(key);
            } else if (padre.key.getNombre().compareTo(key.getNombre()) < 0) {
                padre.derecha = new node(key);
            }
        } else {
            root = new node(key);
        }
    }

    public void delete(String nombre) {
        if (root != null) {
            node nodito = find(nombre, root);
            if (nodito.key.getNombre().compareTo(nombre) == 0) {

            }else{
                System.out.println("El elemento no se encuentra en el arbol");
            }
        } else {
            System.out.println("El arbol esta vacio");
        }
    }

    public Usuario get(String nombre){
        node nodito = find(nombre, root);
        if (nodito.key.getNombre().compareTo(nombre) == 0) {
            return nodito.key;
        }else{
            return null;
        }
    }

    public void printInOrder(node nodo) {
        if (nodo.izquierda != null) {
            printInOrder(nodo.izquierda);
        }
        System.out.print(nodo.key.getNombre() + ", ");
        if (nodo.derecha != null) {
            printInOrder(nodo.derecha);
        }
    }

    public void imprimir() {
        if (root != null) {
            printInOrder(root);
        }
        System.out.println("");
    }

    public class node {
        Usuario key;
        node padre, izquierda, derecha;

        public node(Usuario key, node padre, node izquierda, node derecha) {
            this.key = key;
            this.padre = padre;
            this.izquierda = izquierda;
            this.derecha = derecha;
        }

        public node(Usuario key) {
            this(key, null, null, null);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        for (int i = 0; i < 50; i++) {
            bst.insert(new Usuario(null, null, "" + (int) (Math.random() * 100 + 1)));
        }
        bst.imprimir();
    }
}
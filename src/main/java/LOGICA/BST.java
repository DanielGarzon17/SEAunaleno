package LOGICA;

import DATOS.Usuario;

public class BST {
    public node root;

    public BST() {
        this.root = null;
    }

    public Set<Usuario> findAll(String cualquiera, node nodito){
        Set<Usuario> Encontrados = new Set<>();
        // datos a izquierda
        if(nodito.izquierda != null){
            Set<Usuario> EncontradosIzquierda = findAll(cualquiera, nodito.izquierda);
            for(int i = 0; i< EncontradosIzquierda.size; i++){
                try {
                    Encontrados.add(EncontradosIzquierda.get(i));
                } catch (Exception e) {
                }
            }
        }
        // dato en el nodo
        if (nodito.key.getId().contains(cualquiera) || 
            nodito.key.getNombres().contains(cualquiera) ||
            nodito.key.getApellidos().contains(cualquiera)  ||
            nodito.key.getTelefono().contains(cualquiera) ||
            nodito.key.getEmail().contains(cualquiera) ||
            nodito.key.getPassword().contains(cualquiera)
            
            ) {
                Encontrados.add(nodito.key);
        }
        // datos a derecha
        if(nodito.derecha != null){
            Set<Usuario> EncontradosDerecha = findAll(cualquiera, nodito.derecha);
            for(int i = 0; i< EncontradosDerecha.size; i++){
                try {
                    Encontrados.add(EncontradosDerecha.get(i));
                } catch (Exception e) {
                }
            }
        }

        return Encontrados;
    }

    public node find(String nombre, node nodoActual) {
        if (nodoActual.key.getNombres().compareTo(nombre) == 0) {
            return nodoActual;
        } else if (nodoActual.key.getNombres().compareTo(nombre) > 0) { // nodo actual > nombre
            if (nodoActual.izquierda != null) {
                return find(nombre, nodoActual.izquierda);
            }
            return nodoActual;
        } else if (nodoActual.key.getNombres().compareTo(nombre) < 0) {
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
        if (nodoActual.key.getNombres().compareTo(nodoActual.padre.key.getNombres()) < 0) {
            return nodoActual.padre;
        } else {
            return rightAncestor(nodoActual.padre);
        }
    }

    public LinkedList<node> rangeSearch(String inicio, String fin) {
        LinkedList<node> retorna = new LinkedList<>();
        node nodito = find(inicio, root);
        while (nodito.key.getNombres().compareTo(fin) < 0) {
            if (nodito.key.getNombres().compareTo(inicio) >= 0) {
                retorna.pushBack(nodito);
            }
            nodito = next(nodito);
        }
        return retorna;
    }

    public void insert(Usuario key) {
        if (root != null) {
            node padre = find(key.getNombres(), root);
            if (padre.key.getNombres().compareTo(key.getNombres()) > 0) {
                padre.izquierda = new node(key);
            } else if (padre.key.getNombres().compareTo(key.getNombres()) < 0) {
                padre.derecha = new node(key);
            }
        } else {
            root = new node(key);
        }
    }

    public void delete(String nombre) {
        if (root != null) {
            node nodito = find(nombre, root);
            if (nodito.key.getNombres().compareTo(nombre) == 0) {

            } else {
                System.out.println("El elemento no se encuentra en el arbol");
            }
        } else {
            System.out.println("El arbol esta vacio");
        }
    }

    public Usuario findNombres(String nombre){
        node nodito = find(nombre, root);
        if (nodito.key.getNombres().compareTo(nombre) == 0) {
            return nodito.key;
        }
        return null;
    }
    
    public Usuario findEmail(String email){
        node nodito = find(email, root);
        if (nodito.key.getEmail().compareTo(email) == 0) {
            return nodito.key;
        } else {
            return null;
        }
    }

    public void printInOrder(node nodo) {
        if (nodo.izquierda != null) {
            printInOrder(nodo.izquierda);
        }
        System.out.print(nodo.key.getNombres() + ", ");
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

    // public static void main(String[] args) {
    //     BST bst = new BST();
    //     for (int i = 0; i < 50; i++) {
    //         bst.insert(new Usuario());
    //     }
    //     bst.imprimir();
    // }
}
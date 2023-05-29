package LOGICA;

class AVLNode<T> {
    T data;
    AVLNode<T> left;
    AVLNode<T> right;
    int height;

    AVLNode(T data) {
        this.data = data;
        this.height = 1;
    }
}

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;
    // public static void main(String[] args) {
    //     AVLTree<Integer> avlTree = new AVLTree<>();

    //     // Insertar elementos en el árbol AVL
    //     avlTree.insert(10);
    //     avlTree.insert(20);
    //     avlTree.insert(30);
    //     avlTree.insert(40);
    //     avlTree.insert(50);
    //     avlTree.insert(25);

    //     // Imprimir el árbol AVL
    //     System.out.println("Árbol AVL después de la inserción:");
    //     avlTree.print();

    //     // Eliminar elementos del árbol AVL
    //     avlTree.delete(30);
    //     avlTree.delete(25);

    //     // Imprimir el árbol AVL después de la eliminación
    //     System.out.println("Árbol AVL después de la eliminación:");
    //     avlTree.print();
    // }

    public boolean find(T data) {
        return findNode(root, data);
    }
    
    private boolean findNode(AVLNode<T> node, T data) {
        if (node == null) {
            return false;
        }
    
        if (data.compareTo(node.data) < 0) {
            return findNode(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            return findNode(node.right, data);
        } else {
            return true;
        }
    }

    public void print() {
        if (root == null) {
            System.out.println("El árbol AVL está vacío.");
        } else {
            printNode(root, 0);
        }
    }
    private void printNode(AVLNode<T> node, int level) {
        if (node != null) {
            printNode(node.right, level + 1);
    
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) {
                sb.append("\t");
            }
            sb.append(node.data);
            System.out.println(sb.toString());
    
            printNode(node.left, level + 1);
        }
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    private AVLNode<T> insert(AVLNode<T> node, T data) {
        if (node == null) {
            return new AVLNode<>(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            // Duplicate values are not allowed in AVL tree
            return node;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1 && data.compareTo(node.left.data) < 0) {
            return rotateRight(node);
        }

        if (balance < -1 && data.compareTo(node.right.data) > 0) {
            return rotateLeft(node);
        }

        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    private AVLNode<T> delete(AVLNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                AVLNode<T> temp = null;
                if (node.left == null) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                AVLNode<T> temp = getMinValueNode(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data);
            }
        }

        if (node == null) {
            return null;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> newRoot = node.left;
        AVLNode<T> subtree = newRoot.right;

        newRoot.right = node;
        node.left = subtree;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> newRoot = node.right;
        AVLNode<T> subtree = newRoot.left;

        newRoot.left = node;
        node.right = subtree;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    private int getHeight(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    private int getBalance(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode<T> getMinValueNode(AVLNode<T> node) {
        AVLNode<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    
}

package LOGICA;


// public class Map<K, V> {
//     private ArrayList<K> llaves;
//     private ArrayList<V> valores;

//     public Map() {
//         llaves = new ArrayList<>();
//         valores = new ArrayList<>();
//     }

//     public void agregar(K llave, V valor) {
//         int indice = llaves.indexOf(llave);

//         if (indice != -1) {
//             valores.set(indice, valor);
//         } else {
//             llaves.add(llave);
//             valores.add(valor);
//         }
//     }

//     public V obtener(K llave) {
//         int indice = llaves.indexOf(llave);

//         if (indice != -1) {
//             return valores.get(indice);
//         }

//         return null;
//     }

//     public void eliminar(K llave) {
//         int indice = llaves.indexOf(llave);

//         if (indice != -1) {
//             llaves.remove(indice);
//             valores.remove(indice);
//         }
//     }

//     public List<K> obtenerLlaves() {
//         return new ArrayList<>(llaves);
//     }

//     public List<V> obtenerValores() {
//         return new ArrayList<>(valores);
//     }

//     public boolean contieneLlave(K llave) {
//         return llaves.contains(llave);
//     }

//     public boolean contieneValor(V valor) {
//         return valores.contains(valor);
//     }

//     public int tamaño() {
//         return llaves.size();
//     }
// }
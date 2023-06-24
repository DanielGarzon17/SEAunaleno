package LOGICA;


public class Grafo<T> {
    private Map<T, LinkedList<T>> adyacencia;

    public Grafo() {
        adyacencia = new Map<>();
    }

    public void agregarVertice(T vertice) {
        adyacencia.put(vertice, new LinkedList<T>());
    }

    public void agregarArista(T origen, T destino) {
        if (!adyacencia.containsKey(origen))
            agregarVertice(origen);

        if (!adyacencia.containsKey(destino))
            agregarVertice(destino);

        adyacencia.get(origen).pushBack(destino);
        adyacencia.get(destino).pushBack(origen);
    }

    public Set<T> obtenerVertices() {
        return adyacencia.keySet();
    }

    public LinkedList<T> obtenerVecinos(T vertice) {
        if (adyacencia.containsKey(vertice))
            return adyacencia.get(vertice);

        return new LinkedList<T>();
    }

    public boolean contieneVertice(T vertice) {
        return adyacencia.containsKey(vertice);
    }

    public boolean sonAdyacentes(T vertice1, T vertice2) {
        boolean adyasentes = false;
        if (!adyacencia.containsKey(vertice1) || !adyacencia.containsKey(vertice2))
            return adyasentes;

        try {
            for (T i : adyacencia.get(vertice1).getAll()) {
                if (i == vertice2) {
                    adyasentes = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adyasentes;
    }
}
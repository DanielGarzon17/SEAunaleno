package DATOS;

import java.util.List;

public class Usuario {

    private List<Evaluacion> historial;
    private float[] notas;
    private String nombre;

    public Usuario(List<Evaluacion> historial, float[] notas, String nombre) {
        this.historial = historial;
        this.notas = notas;
        this.nombre = nombre;
    }
    
    public Usuario(List<Evaluacion> historial, float[] notas){
        this(historial, notas, "DANIEL");
    }
    
    public Usuario(List<Evaluacion> historial){
        this(historial, null, "DANIEL");
    }
    public Usuario(){
        this(null, null, "DANIEL");
    }

    public List<Evaluacion> getHistorial() {
        return historial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setHistorial(List<Evaluacion> historial) {
        this.historial = historial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float[] getNotas() {
        return notas;
    }

    public void setNotas(float[] notas) {
        this.notas = notas;
    }
    
}

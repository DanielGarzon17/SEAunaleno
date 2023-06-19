package DATOS;

import java.util.List;

public class Usuario {
    private String id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private List<Evaluacion> historial;
    private float[] notas;
    
    public Usuario(String id, String nombres, String apellidos, String telefono, String email,
            List<Evaluacion> historial, float[] notas) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.historial = historial;
        this.notas = notas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Evaluacion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Evaluacion> historial) {
        this.historial = historial;
    }

    public float[] getNotas() {
        return notas;
    }

    public void setNotas(float[] notas) {
        this.notas = notas;
    }

}

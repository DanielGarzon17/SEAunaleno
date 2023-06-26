package DATOS;

import LOGICA.Queue;
import LOGICA.Stack;

public class Usuario {
    private String id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private Stack<Evaluacion> historial;
    private byte[] Imagen;
    

    private double[] notas;
    private String password;

    public Usuario(String id, String nombres, String apellidos, String telefono, String email,
            Stack<Evaluacion> historial, double[] notas, byte[] Imagenbytes, String password) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.historial = historial;
        this.notas = notas;
        this.Imagen = Imagenbytes;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Stack<Evaluacion> getHistorial() {
        return historial;
    }

    public void setHistorial(Stack<Evaluacion> historial) {
        this.historial = historial;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] imagen) {
        Imagen = imagen;
    }
}

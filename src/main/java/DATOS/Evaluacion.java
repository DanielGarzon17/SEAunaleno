package DATOS;

public class Evaluacion {

    private String link;
    private int numeroDePreguntas;
    private String[] respuestas;
    private int horas, minutos;
    private String tipoEvaluacion;
    private String nombre;

    public Evaluacion(String link, int numeroDePreguntas, String[] respuestas, int horas, int minutos, String tipoEvaluacion, String nombre) {
        this.link = link;
        this.numeroDePreguntas = numeroDePreguntas;
        this.respuestas = respuestas;
        this.horas = horas;
        this.minutos = minutos;
        this.tipoEvaluacion = tipoEvaluacion;
        this.nombre = nombre;
    }
    
    public Evaluacion(String link, int numeroDePreguntas, String[] respuestas, int horas, int minutos, String tipoEvaluacion) {
        this(link, numeroDePreguntas, respuestas, horas, minutos, tipoEvaluacion, "NA");
    }
    
    public Evaluacion(String link, int numeroDePreguntas, String[] respuestas, int horas, int minutos) {
        this(link, numeroDePreguntas, respuestas, horas, minutos, "NA", "NA");
    }
    
    public Evaluacion(String link, int numeroDePreguntas, String[] respuestas, int horas) {
        this(link, numeroDePreguntas, respuestas, horas, 0, "NA", "NA");
    }
    
    public Evaluacion(String link, int numeroDePreguntas, String[] respuestas) {
        this(link, numeroDePreguntas, respuestas, 1, 20, "NA", "NA");
    }
    
    public Evaluacion(String link, int numeroDePreguntas) {
        this(link, numeroDePreguntas, null, 1, 20, "NA", "NA");
    }
    
    public Evaluacion(String link) {
        this(link, 1, null, 1, 20, "NA", "NA");
    }

    public String getLink() {
        return link;
    }

    public int getNumeroDePreguntas() {
        return numeroDePreguntas;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setNumeroDePreguntas(int numeroDePreguntas) {
        this.numeroDePreguntas = numeroDePreguntas;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
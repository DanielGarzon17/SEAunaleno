package LOGICA;

import IU.*;
import java.util.Scanner;

import DATOS.Evaluacion;
import DATOS.Usuario;

import java.io.*;

public class CalificarExamen {
    private int numLC = 0, numMA = 0, numCI = 0, numSO = 0, numIM = 0,
                contLC = 0, contMA = 0, contCI = 0, contSO = 0, contIM = 0,corLC=0,corMA=0,corCI=0,corSO=0,corIM=0;;
    private int aleatorio = 0;
    private final String[] vectorRespuestas = {"respuestas20092", "respuestas20102"};
    private String[] respuestasExamen, caracterExamen, respuestasUsuario;
    private int[] posicionesLC, posicionesMA, posicionesCI, posicionesSO, posicionesIM;
    private final double desv = 3.38;
    Usuario usuarioActivo;
    

    
    public void setCorLC(int corLC) {
        this.corLC = corLC;
    }

    public void setCorMA(int corMA) {
        this.corMA = corMA;
    }

    public void setCorCI(int corCI) {
        this.corCI = corCI;
    }

    public void setCorSO(int corSO) {
        this.corSO = corSO;
    }

    public void setCorIM(int corIM) {
        this.corIM = corIM;
    }

    public CalificarExamen(Usuario usuario){
        setUsuarioActivo(usuario);
    }

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }
    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }
    public int getCorLC() {
        return corLC;
    }

    public int getCorMA() {
        return corMA;
    }

    public int getCorCI() {
        return corCI;
    }

    public int getCorSO() {
        return corSO;
    }

    public int getCorIM() {
        return corIM;
    }

    public void abrirArchivo(String[] respuestasUsuario) {
        System.out.println("aleatorio respuestas: " + aleatorio);
        this.respuestasUsuario = respuestasUsuario;
        File fileRespuestas = new File("EXAMENES/RESPUESTAS/" + vectorRespuestas[aleatorio] + ".txt");
        File filecaracter = new File("EXAMENES/CARACTER/" + vectorRespuestas[aleatorio] + "c.txt");

        Scanner s = null;
        Scanner s1 = null;
        System.out.println(vectorRespuestas[aleatorio]);
        try {
            // Leemos el contenido del fichero
            s = new Scanner(fileRespuestas);
            s1 = new Scanner(filecaracter);
            // Leemos linea a linea el fichero
            while (s.hasNextLine()) {
                String linea = s.nextLine();// Guardamos la linea en un String
                String linea1 = s1.nextLine();
                respuestasExamen = linea.split(";");
                caracterExamen = linea1.split(";");
                dividirRespuestas(caracterExamen);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            // Cerramos el fichero tanto si la lectura ha sido correcta o no
            try {
                if (s != null) {
                    s.close();
                }
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        }
    }
    

    public void dividirRespuestas(String[] caracter) {
        

        //cuenta la catidad de preguntas por componente
        for (int i = 0; i < caracterExamen.length; i++) {
            switch (caracter[i]) {
                case "L":
                    numLC++;
                    break;
                case "M":
                    numMA++;
                    break;
                case "C":
                    numCI++;
                    break;
                case "S":
                    numSO++;
                    break;
                case "I":
                    numIM++;
                    break;
            }
        }
        //asigna tamaño de vectores
        posicionesLC = new int[numLC];
        posicionesMA = new int[numMA];
        posicionesCI = new int[numCI];
        posicionesSO = new int[numSO];
        posicionesIM = new int[numIM];
        //almacena posiciones de componente
        for (int i = 0; i < respuestasExamen.length; i++) {
            switch (caracterExamen[i]) {
                case "L":
                    posicionesLC[contLC] = i;
                    contLC++;
                    break;
                case "M":
                    posicionesMA[contMA] = i;
                    contMA++;
                    break;
                case "C":
                    posicionesCI[contCI] = i;
                    contCI++;
                    break;
                case "S":
                    posicionesSO[contSO] = i;
                    contSO++;
                    break;
                case "I":
                    posicionesIM[contIM] = i;
                    contIM++;
                    break;
            }
        }
        setCorLC(contarCorrectas(posicionesLC));
        setCorMA(contarCorrectas(posicionesMA));
        setCorCI(contarCorrectas(posicionesCI));
        setCorSO(contarCorrectas(posicionesSO));
        setCorIM(contarCorrectas(posicionesIM));

        double puntajeLectura = puntajePorComponente(getCorLC(), 10);
        double puntajeMatematicas = puntajePorComponente(getCorMA(), 10);
        double puntajeCiencias = puntajePorComponente(getCorCI(), 10);
        double puntajeSociales = puntajePorComponente(getCorSO(), 10.5);
        double puntajeImagen = puntajePorComponente(getCorIM(), 7);
        double Global = puntajeGlobal(puntajeLectura, puntajeMatematicas, puntajeCiencias, puntajeSociales, puntajeImagen);

        PanelResultados n = new PanelResultados(getUsuarioActivo());
        MenuInterfaz interfazPrincipal = new MenuInterfaz(getUsuarioActivo());
        n.setResultados(Global, puntajeLectura, puntajeMatematicas, puntajeCiencias, puntajeSociales, puntajeImagen);
        interfazPrincipal.repintarPanel(n);
        interfazPrincipal.setVisible(true);
    }

    public String toString(String Materia) {
        switch(Materia){
            case "LC":
                return getCorLC()+"/"+numLC;
            case "MA":
                return getCorMA()+"/"+numMA;    
            case "CI":
                return getCorCI()+"/"+numCI;    
            case "SO":
                return getCorSO()+"/"+numSO;    
            case "IM":
                return getCorIM()+"/"+numIM;    
            default:
                return "";              
        }
    }
    
    public double puntajeGlobal(double LC, double MA, double CI, double SO, double IM) {
        return (500 + (LC - 10) * 26.7 + (MA - 10) * 33.5 + (CI - 10) * 26.1 + (SO - 10) * 37.4 + (IM - 10) * 22.9);
    }

    public double puntajePorComponente(int aciertosComponente, double media) {
        return ((aciertosComponente - media) / (desv - 1)) + 10;
    }

    public int contarCorrectas(int[] posicionesmateria) {
        int contadorDeAciertos = 0;
        for (int i : posicionesmateria) {
            if (respuestasExamen[i].equals(respuestasUsuario[i])) {
                contadorDeAciertos++;
            }
        }
        return contadorDeAciertos;
    }

    public void setAleatorio(int aleatorio) {
        this.aleatorio = aleatorio;
    }
    
}

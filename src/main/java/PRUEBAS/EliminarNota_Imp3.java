package PRUEBAS;

import LOGICA.*;
import java.util.Random;

public class EliminarNota_Imp3 {

    public EliminarNota_Imp3() {
        
        int x = 100000000;
        ArrayList<Double> examenesCalificados = new ArrayList<Double>(x+1);
        for (int i = 0; i < x; i++) {
            double Rnd = RandomDouble();
            examenesCalificados.pushBack(Rnd);
        }
        long tiempoInicial = System.nanoTime();
        examenesCalificados.popFront();
        System.out.println(x + "=" + (System.nanoTime() - tiempoInicial));
        
    }

    public static void main(String[] args) {
        EliminarNota_Imp3 a = new EliminarNota_Imp3();
    }

    public static double RandomDouble() {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        // Multiplicamos por 999 para tener un rango de 0 a 999
        randomDouble *= 999;
        // Sumamos 1 para tener un rango de 1 a 1000
        randomDouble += 1;
        // Redondeamos al entero más cercano
        long rounded = Math.round(randomDouble * 1000);
        // Dividimos entre 1000 para tener 3 decimales
        double finalDouble = ((double) rounded) / 1000;
        return finalDouble;
    }
}
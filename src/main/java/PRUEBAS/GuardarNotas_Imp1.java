package PRUEBAS;

import LOGICA.*;
import java.util.Random;

public class GuardarNotas_Imp1 {

 //Para Stack
public GuardarNotas_Imp1() {
        int x = 100000;
        Stack<Double> examenesCalificados = new Stack<Double>(x);
        for (int i = 0; i < x; i++) {
            double Rnd = RandomDouble();
            examenesCalificados.push(Rnd);
        }
        long tiempoInicial = System.nanoTime();
        examenesCalificados.push(RandomDouble());
        System.out.println("s"+x + "=" + (System.nanoTime() - tiempoInicial));

    }

    public static void main(String[] args) {
        new GuardarNotas_Imp1();
        
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

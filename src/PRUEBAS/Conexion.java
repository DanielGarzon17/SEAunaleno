package PRUEBAS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.io.FileWriter;
import org.json.simple.JSONArray;

import LOGICA.*;

public class Conexion {
    final static int x=1000;
    public static void main(String[] args) {
        
        double[] data1 = new double[x];

        JFrame frame = new JFrame("Ejemplo de gráfico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Stack<Double> datos = llenarVector();
        for (int i = 0; i < x; i++) {
            data1[i] = (double) datos.pop();
            System.out.print(datos.pop());
        }

        JButton button = new JButton("Generar gráfico");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // generarArchivoJSON(data1, "src\\PRUEBAS\\data.json");

                    ProcessBuilder processBuilder = new ProcessBuilder("python", "src\\PRUEBAS\\graficador.py");

                    // Iniciar el proceso y esperar a que finalice
                    Process process = processBuilder.start();
                    int exitCode = process.waitFor();

                    if (exitCode == 0) {
                        System.out.println("Gráfico generado.");
                    } else {
                        System.out.println("Error al generar el gráfico.");
                    }
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.getContentPane().add(button);
        frame.pack();
        frame.setVisible(true);
    }

    // public static void generarArchivoJSON(double[] datos, String nombreArchivo) {
    //     JSONArray jsonArray = new JSONArray();
    //     for (double dato : datos) {
    //         jsonArray.add(dato);
    //     }

    //     try (FileWriter fileWriter = new FileWriter(nombreArchivo)) {
    //         fileWriter.write(jsonArray.toJSONString());
    //         System.out.println("Archivo JSON generado exitosamente.");
    //     } catch (IOException e) {
    //         System.out.println("Error al generar el archivo JSON: " + e.getMessage());
    //     }
    // }

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

    public static Stack<Double> llenarVector() {
        Stack<Double> examenesCalificados = new Stack<Double>(x+1);
        for (int i = 0; i < x; i++) {
            double Rnd = RandomDouble();
            examenesCalificados.push(Rnd);
        }
        long tiempoInicial = System.nanoTime();
        examenesCalificados.push(RandomDouble());
        System.out.println("s" + x + "=" + (System.nanoTime() - tiempoInicial));
        return examenesCalificados;
    }

}

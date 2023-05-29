package IU;

import javax.swing.*;
import java.awt.*;

public class EstadisticasPanel extends JPanel {
    
    private String[] estadisticas;
    
    public EstadisticasPanel(String[] estadisticas) {
        this.estadisticas = estadisticas;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Configurar fuentes y colores
        Font font = new Font("Arial", Font.BOLD, 14);
        g.setFont(font);
        g.setColor(Color.BLACK);
        
        // Calcular la posición inicial para dibujar las estadísticas
        int x = 50;
        int y = 50;
        
        // Dibujar las estadísticas
        for (String estadistica : estadisticas) {
            g.drawString(estadistica, x, y);
            y += 20; // Incrementar la posición Y para la siguiente estadística
        }
    }
    
    public static void main(String[] args) {
        // Crear un arreglo con algunas estadísticas ficticias
        String[] estadisticas = {
            "Estadística 1: 100",
            "Estadística 2: 200",
            "Estadística 3: 300",
            "Estadística 4: 400"
        };
        
        // Crear un nuevo JFrame y agregar el panel de estadísticas
        JFrame frame = new JFrame("Estadísticas");
        EstadisticasPanel panel = new EstadisticasPanel(estadisticas);
        frame.add(panel);
        
        // Configurar el tamaño del JFrame y hacerlo visible
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
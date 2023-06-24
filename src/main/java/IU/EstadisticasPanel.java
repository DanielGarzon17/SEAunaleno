package IU;
//INVESTIGAR LIBRERIA JFREE CHART
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EstadisticasPanel extends JPanel {
    JLabel Imagen1 = new JLabel();
    private String[] estadisticas;
    
    public EstadisticasPanel(String[] estadisticas) {
        initComponents();
        this.estadisticas = estadisticas;
    }

    private void initComponents(){
        Imagen1.setIcon(resizeIcon("src/main/java/RECURSOS/pareto-graphic.jpg",700,500));
        add(Imagen1);
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
    private ImageIcon resizeIcon(String imagePath, int width, int height) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
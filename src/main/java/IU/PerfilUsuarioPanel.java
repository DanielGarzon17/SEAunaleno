package IU;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PerfilUsuarioPanel extends JPanel {
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String telefono;
    private String pathImagenPerfil;

    public PerfilUsuarioPanel(String nombres, String apellidos, String correoElectronico, String telefono, String pathImagenPerfil) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.pathImagenPerfil = pathImagenPerfil;
        setPreferredSize(new Dimension(300, 200));
        initComponents();
    }

    public void initComponents(){
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 2));

        JLabel lblNombres = new JLabel("Nombres:");
        JLabel lblApellidos = new JLabel("Apellidos:");
        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        JLabel lblTelefono = new JLabel("Teléfono:");
        JLabel imageLabel = new JLabel();

        JLabel lblNombresValor = new JLabel(nombres);
        JLabel lblApellidosValor = new JLabel(apellidos);
        JLabel lblCorreoValor = new JLabel(correoElectronico);
        JLabel lblTelefonoValor = new JLabel(telefono);

        infoPanel.add(lblNombres);
        infoPanel.add(lblNombresValor);
        infoPanel.add(lblApellidos);
        infoPanel.add(lblApellidosValor);
        infoPanel.add(lblCorreo);
        infoPanel.add(lblCorreoValor);
        infoPanel.add(lblTelefono);
        infoPanel.add(lblTelefonoValor);

        add(infoPanel, BorderLayout.CENTER);
        
        try {
            Image originalImage = ImageIO.read(new File(pathImagenPerfil));
            Image scaledImage = originalImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            BufferedImage bufferedImage = new BufferedImage(scaledImage.getWidth(null),
                    scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setClip(new Ellipse2D.Float(0, 0, 250, 250));
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();

            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            imageLabel.setIcon(imageIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        add(imageLabel,BorderLayout.NORTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ejemplo de uso: Crear una ventana y agregar el panel de perfil de usuario
        String nombres = "John";
        String apellidos = "Doe";
        String correoElectronico = "john.doe@example.com";
        String telefono = "1234567890";
        String imagenPath = "src/main/java/RECURSOS/defaultuser1.jpg";

        PerfilUsuarioPanel perfilPanel = new PerfilUsuarioPanel("NOMBRE", "apellido", "email@gmail.com", "3156165797", "src/main/java/RECURSOS/defaultuser1.jpg");
    
        
        JFrame frame = new JFrame("Perfil de Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);

        frame.add(perfilPanel);
        frame.setVisible(true);
    }
}

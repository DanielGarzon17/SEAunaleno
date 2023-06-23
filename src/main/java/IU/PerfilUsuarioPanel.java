package IU;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import PRUEBAS.PanelRound;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PerfilUsuarioPanel extends JPanel {
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String telefono;
    private String pathImagenPerfil;

    public PerfilUsuarioPanel(String nombres, String apellidos, String correoElectronico, String telefono,
            String pathImagenPerfil) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.telefono = "+57" + telefono;
        this.pathImagenPerfil = pathImagenPerfil;
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        PanelRound infoPanel = new PanelRound();
        infoPanel.setLayout(new GridLayout(4, 2));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setRoundBottomLeft(20);
        infoPanel.setRoundBottomRight(20);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(20, 20, 20, 20)));

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
            String imageUrl = pathImagenPerfil;

            // Leer la imagen desde la URL
            BufferedImage originalImage = ImageIO.read(new URL(imageUrl));

            // Crear una imagen redimensionada de 250x250 píxeles
            BufferedImage resizedImage = new BufferedImage(250, 250, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();

            // Crear una forma elíptica para recortar la imagen
            Shape ellipse = new Ellipse2D.Float(0, 0, 250, 250);

            // Recortar la imagen original en la forma elíptica y dibujarla en la imagen
            // redimensionada
            g2d.setClip(ellipse);
            g2d.drawImage(originalImage, 0, 0, 250, 250, null);
            g2d.dispose();

            ImageIcon imageIcon = new ImageIcon(resizedImage);
            imageLabel.setIcon(imageIcon);

            System.out.println("La imagen se ha recortado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo recortar la imagen desde Internet.");
        }
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        PanelRound imagePanel = new PanelRound();
        // imagePanel.setRoundBottomLeft(20);
        // imagePanel.setRoundBottomRight(20);
        imagePanel.setRoundTopLeft(20);
        imagePanel.setRoundTopRight(20);
        imagePanel.setBackground(new Color(76, 175, 80));
        imagePanel.add(imageLabel);

        add(imagePanel, BorderLayout.NORTH);
        imageLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        for (Component element : infoPanel.getComponents()) {
            element.setBackground(Color.WHITE);
            try {
                element.setFont(
                        Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Roboto-Light.ttf"))
                                .deriveFont(12f));
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {

        PerfilUsuarioPanel perfilPanel = new PerfilUsuarioPanel("NOMBRE", "apellido", "email@gmail.com", "3156165797",
                "src/main/java/RECURSOS/defaultuser1.jpg");

        JFrame frame = new JFrame("Perfil de Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);

        frame.add(perfilPanel);
        frame.setVisible(true);
    }
}

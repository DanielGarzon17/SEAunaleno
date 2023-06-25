package IU;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import LOGICA.PanelRound;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.InflaterInputStream;

public class PanelPerfilUsuario extends JPanel {
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String telefono;
    private byte[] ImagenPerfil;

    public PanelPerfilUsuario(String nombres, String apellidos, String correoElectronico, String telefono,
            byte[] ImagenPerfil) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.ImagenPerfil = ImagenPerfil;
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
            BufferedImage originalImage =null;
            if(ImagenPerfil!=null){
                originalImage = ImageIO.read(new ByteArrayInputStream(DescomprimirBytes(ImagenPerfil)));
            }else{
                Path path=new File("src/main/java/RECURSOS/defaultuser1.jpg").toPath();
                originalImage = ImageIO.read(new ByteArrayInputStream(Files.readAllBytes(path)));
            }

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

    private static byte[] DescomprimirBytes(byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        InflaterInputStream iis = new InflaterInputStream(bais);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = iis.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }
        return baos.toByteArray();
    }

    public static void main(String[] args) {

        PanelPerfilUsuario perfilPanel = new PanelPerfilUsuario("NOMBRE", "apellido", "email@gmail.com", "3156165797",
                null);

        JFrame frame = new JFrame("Perfil de Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);

        frame.add(perfilPanel);
        frame.setVisible(true);
    }
}

package IU;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PanelBiblioteca extends JPanel {

    public PanelBiblioteca() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        ImageIcon imagen = resizeIcon("src/main/java/RECURSOS/Google-Drive-logo.png", 250, 150);
        // Crear los botones
        JButton button1 = new JButton("Material Organizado");
        button1.setIcon(imagen); // Reemplaza "ruta/imagen1.png" con la ruta de tu imagen

        JButton button2 = new JButton("Pruebas anteriores en PDF");
        button2.setIcon(imagen); // Reemplaza "ruta/imagen2.png" con la ruta de tu imagen

        JButton button3 = new JButton("Solucionarios");
        button3.setIcon(imagen); // Reemplaza "ruta/imagen3.png" con la ruta de tu imagen


        // Agregar los botones al JPanel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(button1);
        topPanel.add(button2);
        topPanel.setBackground(Color.WHITE);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.add(button3);

        button3.setBackground(Color.WHITE);

        for (Component element : topPanel.getComponents()) {
            element.setBackground(Color.WHITE);
            try {
                element.setFont(
                        Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Roboto-Light.ttf"))
                                .deriveFont(12f));
                button3.setFont(
                        Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Roboto-Light.ttf"))
                                .deriveFont(12f));
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // button1.setBorderPainted(false);
        // button2.setBorderPainted(false);
        // button3.setBorderPainted(false);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbrirLinks("https://drive.google.com/drive/folders/1g4cdM7H9F96k5hvfk6hhfZVCP8jZWNri?usp=share_link");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbrirLinks("https://drive.google.com/drive/folders/1qyUZa2epX3e3pNVD8S7QkHFgg0AgfHZb?usp=share_link");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbrirLinks("https://drive.google.com/drive/folders/19izxlswEJaJZTV-V2mJ0k9Mwh1gYUool?usp=share_link");
            }
        });
    }

    public void AbrirLinks(String str) {
        if (java.awt.Desktop.isDesktopSupported()) {
            Desktop desktop = java.awt.Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    java.net.URI url = new java.net.URI(str);
                    desktop.browse(url);
                } catch (Exception e) {
                }
            }
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
        // Crear el JFrame
        JFrame frame = new JFrame("Ejemplo CustomPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el CustomPanel
        PanelBiblioteca customPanel = new PanelBiblioteca();

        // Agregar el CustomPanel al JFrame
        frame.getContentPane().add(customPanel);

        // Configurar el tamaño del JFrame
        frame.setSize(400, 200);

        // Mostrar el JFrame
        frame.setVisible(true);
    }
}

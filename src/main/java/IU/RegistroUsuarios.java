package IU;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.IOException;

import org.imgscalr.Scalr;

public class RegistroUsuarios extends JFrame {
    private JTextField emailField;
    private JLabel idLabel;
    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField telefonoField;
    private JButton addButton;
    private JLabel imageLabel;
    private JButton uploadButton;

    private ImageIcon defaultImage;

    public RegistroUsuarios() {
        setTitle("Sea unaleño - Registro de Usuarios");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        // setBackground(Color.decode("#94B43B"));

        defaultImage = new ImageIcon("src/main/java/RECURSOS/defaultuser1.jpg");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 0, 5, 10);

        formPanel.add(new JLabel("Correo Electrónico:"), constraints);
        emailField = new JTextField(20);
        formPanel.add(emailField, constraints);

        formPanel.add(new JLabel("ID:"), constraints);
        idLabel = new JLabel();
        idLabel.setForeground(Color.BLUE);
        formPanel.add(idLabel, constraints);

        formPanel.add(new JLabel("Nombres:"), constraints);
        nombresField = new JTextField(20);
        formPanel.add(nombresField, constraints);

        formPanel.add(new JLabel("Apellidos:"), constraints);
        apellidosField = new JTextField(20);
        formPanel.add(apellidosField, constraints);

        formPanel.add(new JLabel("Teléfono:"), constraints);
        telefonoField = new JTextField(20);
        formPanel.add(telefonoField, constraints);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imageLabel = new JLabel();
        imageLabel.setIcon(defaultImage);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        uploadButton = new JButton("Subir imagen");
        imagePanel.add(uploadButton, BorderLayout.SOUTH);

        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        addButton = new JButton("Agregar");
        mainPanel.add(addButton, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idLabel.getText();
                String nombres = nombresField.getText();
                String apellidos = apellidosField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();

                // Aquí puedes agregar la lógica para guardar los datos del nuevo usuario
                // Puedes utilizar las variables id, nombres, apellidos, telefono y email
                // para realizar las operaciones necesarias (guardar en una base de datos, etc.)
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));

                int result = fileChooser.showOpenDialog(RegistroUsuarios.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    // Recortar la imagen de forma circular
                    try {
                        Image originalImage = ImageIO.read(selectedFile);
                        Image scaledImage = originalImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                        BufferedImage bufferedImage = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
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
                }
            }
        });

        emailField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String uniqueID = generateUniqueID(email);
                idLabel.setText(uniqueID);
            }
        });
    }

    private String generateUniqueID(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convertir el hash a una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Truncar el resultado a 8 caracteres
            return hexString.toString().substring(0, 8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RegistroUsuarios registroFrame = new RegistroUsuarios();
                registroFrame.setVisible(true);
            }
        });
    }
}

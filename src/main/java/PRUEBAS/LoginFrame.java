package PRUEBAS;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        ImageIcon originalIcon = new ImageIcon("src/main/java/RECURSOS/SEAUNALENOLOGO.jpg");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(-1, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Panel superior con la imagen redimensionada
        JLabel logoLabel = new JLabel(resizedIcon);
        getContentPane().add(logoLabel, BorderLayout.NORTH);

        // Panel central con los campos y botones
        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel emailLabel = new JLabel("Correo electrónico:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Iniciar Sesión");
        JButton registerButton = new JButton("Registrarse");

        centerPanel.add(emailLabel);
        centerPanel.add(emailField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(loginButton);
        centerPanel.add(registerButton);
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}


package IU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginUsuarios extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registrarseButton;

    public LoginUsuarios() {
        // Configurar la ventana
        setTitle("Sea unaleño - Iniciar sesión");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(48, 63, 159)); // Color de fondo personalizado

        // Crear el panel de encabezado con la imagen recortada circularmente
        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(getWidth(), 300));
        headerPanel.setBackground(Color.WHITE); // Color de encabezado personalizado

        ImageIcon originalIcon = new ImageIcon("src/main/java/RECURSOS/SEAUNALENOLOGO.jpg");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(-1, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Panel superior con la imagen redimensionada
        JLabel logoLabel = new JLabel(resizedIcon);
        headerPanel.add(logoLabel, CENTER_ALIGNMENT);

        // Crear los componentes
        JPanel contentPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);
        JLabel usernameLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Contraseña:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Iniciar sesión");
        loginButton.setBackground(new Color(76, 175, 80)); // Color de fondo del botón personalizado
        loginButton.setForeground(Color.WHITE); // Color de texto del botón personalizado
        registrarseButton = new JButton("Registrarme");
        registrarseButton.setBackground(new Color(9, 36, 51));
        registrarseButton.setForeground(Color.WHITE);

        // borders
        usernameField.setBorder(BorderFactory.createCompoundBorder(usernameLabel.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE)));
        passwordField.setBorder(BorderFactory.createCompoundBorder(passwordLabel.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE)));

        // Personalizar los componentes
        Font labelFont = new Font("Roboto", Font.BOLD, 14);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        usernameField.setFont(labelFont);
        passwordField.setFont(labelFont);
        usernameField.setBackground(Color.WHITE);

        // Agregar los componentes al panel de contenido
        contentPanel.add(usernameLabel);
        contentPanel.add(usernameField);
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);
        contentPanel.add(registrarseButton);
        contentPanel.add(loginButton);

        // Agregar los paneles al marco
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        // Agregar el evento de clic al botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                // Aquí puedes realizar la lógica de autenticación
                // Verificar el usuario y la contraseña, etc.
                // Si la autenticación es exitosa, puedes abrir la ventana principal del
                // programa
                // Si no, mostrar un mensaje de error o tomar alguna otra acción
            }
        });
        // Agregar evento al botón de registrarse
        registrarseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    RegistroUsuarios registroFrame = new RegistroUsuarios();
                    registroFrame.setVisible(true);
                    setVisible(false);
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginUsuarios loginFrame = new LoginUsuarios();
                loginFrame.setVisible(true);
            }
        });
    }
}

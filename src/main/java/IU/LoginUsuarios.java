package IU;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginUsuarios extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginUsuarios() {
        // Configurar la ventana
        setTitle("Sea unaleño - Iniciar sesión");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(48, 63, 159)); // Color de fondo personalizado

        // Crear el panel de encabezado con la imagen recortada circularmente
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int diameter = Math.min(getWidth(), getHeight()) - 20;
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;
                Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
                g2d.setClip(circle);
                g2d.drawImage(getCircularImage(), x, y, diameter, diameter, null);
                g2d.dispose();
            }
        };
        headerPanel.setPreferredSize(new Dimension(getWidth(), 200));
        headerPanel.setBackground(new Color(33, 150, 243)); // Color de encabezado personalizado

        // Crear los componentes
        JPanel contentPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel usernameLabel = new JLabel("Usuario:");
        JLabel passwordLabel = new JLabel("Contraseña:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Iniciar sesión");
        loginButton.setBackground(new Color(76, 175, 80)); // Color de fondo del botón personalizado
        loginButton.setForeground(Color.WHITE); // Color de texto del botón personalizado

        // Personalizar los componentes
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        usernameField.setFont(labelFont);
        passwordField.setFont(labelFont);

        // Agregar los componentes al panel de contenido
        contentPanel.add(usernameLabel);
        contentPanel.add(usernameField);
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);
        contentPanel.add(new JLabel()); // Espacio en blanco
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
                // Si la autenticación es exitosa, puedes abrir la ventana principal del programa
                // Si no, mostrar un mensaje de error o tomar alguna otra acción
            }
        });
    }

    private BufferedImage getCircularImage() {
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File("src\\main\\java\\RECURSOS\\defaultuser1.jpg")); // Ruta de la imagen a cargar
        } catch (IOException e) {
            e.printStackTrace();
        }
        int diameter = Math.min(originalImage.getWidth(), originalImage.getHeight());
        BufferedImage resizedImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2d.drawImage(originalImage, 0, 0, diameter, diameter, null);
        g2d.dispose();
        return resizedImage;
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

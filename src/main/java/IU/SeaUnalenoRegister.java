package IU;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SeaUnalenoRegister extends JFrame {
    private JTextField idField;
    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField telefonoField;
    private JTextField emailField;
    private JButton addButton;
    private JLabel imageLabel;
    private JButton uploadButton;

    public SeaUnalenoRegister() {
        // Configurar la ventana
        setTitle("Sea unaleño - Registro de Usuarios");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear los componentes
        JPanel contentPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel idLabel = new JLabel("ID:");
        JLabel nombresLabel = new JLabel("Nombres:");
        JLabel apellidosLabel = new JLabel("Apellidos:");
        JLabel telefonoLabel = new JLabel("Teléfono:");
        JLabel emailLabel = new JLabel("Correo Electrónico:");
        JLabel imageUploadLabel = new JLabel("Imagen de perfil:");

        idField = new JTextField();
        nombresField = new JTextField();
        apellidosField = new JTextField();
        telefonoField = new JTextField();
        emailField = new JTextField();

        addButton = new JButton("Agregar");
        imageLabel = new JLabel();
        uploadButton = new JButton("Subir imagen");

        // Agregar los componentes al panel de contenido
        contentPanel.add(idLabel);
        contentPanel.add(idField);
        contentPanel.add(nombresLabel);
        contentPanel.add(nombresField);
        contentPanel.add(apellidosLabel);
        contentPanel.add(apellidosField);
        contentPanel.add(telefonoLabel);
        contentPanel.add(telefonoField);
        contentPanel.add(emailLabel);
        contentPanel.add(emailField);
        contentPanel.add(imageUploadLabel);
        contentPanel.add(uploadButton);
        contentPanel.add(new JLabel()); // Espacio en blanco
        contentPanel.add(addButton);

        // Agregar el panel de contenido al marco
        add(contentPanel, BorderLayout.CENTER);

        // Agregar el evento de clic al botón de agregar
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String nombres = nombresField.getText();
                String apellidos = apellidosField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();

                // Aquí puedes agregar la lógica para guardar los datos del nuevo usuario
                // Puedes utilizar las variables id, nombres, apellidos, telefono y email
                // para realizar las operaciones necesarias (guardar en una base de datos, etc.)
            }
        });

        // Agregar el evento de clic al botón de subir imagen
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(SeaUnalenoRegister.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage image = ImageIO.read(selectedFile);
                        if (image.getWidth() <= 250 && image.getHeight() <= 250) {
                            imageLabel.setIcon(new ImageIcon(image));
                        } else {
                            JOptionPane.showMessageDialog(SeaUnalenoRegister.this, "La imagen debe ser de máximo 250x250 píxeles.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SeaUnalenoRegister registroFrame = new SeaUnalenoRegister();
                registroFrame.setVisible(true);
            }
        });
    }
}

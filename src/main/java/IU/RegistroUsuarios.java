package IU;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

import DATOS.Evaluacion;
import DATOS.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.io.*;

public class RegistroUsuarios extends JFrame {
    private JLabel titulo;
    private JTextField emailField;
    private JLabel idLabel;
    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField telefonoField;
    private JPasswordField passwordField;
    private JButton addButton;
    private JLabel imageLabel;
    private JButton uploadButton;
    private JButton showPasswordButton;
    private boolean showPassword = false;
    private MongoClientURI uri = new MongoClientURI("mongodb+srv://Admin:passwordAdmin@cluster0.fe0chr9.mongodb.net");

    public RegistroUsuarios() {
        initComponets();        
    }

    private void initComponets(){
        setTitle("Sea unaleño - Registro de Usuarios");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        getContentPane().setBackground(Color.decode("#94B43B"));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 0, 5, 10);

        formPanel.add(new JLabel("Correo Electrónico:"), constraints);
        emailField = new JTextField(20);
        emailField.setBorder(BorderFactory.createEmptyBorder());
        emailField.setBorder(BorderFactory.createCompoundBorder(emailField.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE)));
        formPanel.add(emailField, constraints);

        formPanel.add(new JLabel("ID:"), constraints);
        idLabel = new JLabel();
        idLabel.setForeground(Color.BLUE);
        formPanel.add(idLabel, constraints);

        formPanel.add(new JLabel("Nombres:"), constraints);
        nombresField = new JTextField(20);
        nombresField.setBorder(BorderFactory.createEmptyBorder());
        nombresField.setBorder(BorderFactory.createCompoundBorder(nombresField.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE)));

        formPanel.add(nombresField, constraints);

        formPanel.add(new JLabel("Apellidos:"), constraints);
        apellidosField = new JTextField(20);
        apellidosField.setBorder(BorderFactory.createEmptyBorder());
        apellidosField.setBorder(BorderFactory.createCompoundBorder(apellidosField.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE)));

        formPanel.add(apellidosField, constraints);

        formPanel.add(new JLabel("Teléfono (presiona enter al ingresarlo):"), constraints);
        telefonoField = new JTextField("+57", 20);
        telefonoField.setBorder(BorderFactory.createEmptyBorder());
        telefonoField.setBorder(BorderFactory.createCompoundBorder(telefonoField.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE)));

        formPanel.add(telefonoField, constraints);

        formPanel.add(new JLabel("Contraseña:"), constraints);
        passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setBorder(BorderFactory.createCompoundBorder(passwordField.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE)));

        formPanel.add(passwordField, constraints);
        showPasswordButton = new JButton("Mostrar");
        formPanel.add(showPasswordButton, constraints);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imageLabel = new JLabel();
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
        try {
            Image originalImage = ImageIO.read(new File("src/main/java/RECURSOS/defaultuser1.jpg"));
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

        imagePanel.add(imageLabel, BorderLayout.CENTER);
        uploadButton = new JButton("Seleccionar imagen");
        imagePanel.add(uploadButton, BorderLayout.SOUTH);

        titulo = new JLabel("Registrarse");
        titulo.setHorizontalAlignment(JLabel.CENTER);

        mainPanel.add(titulo, BorderLayout.NORTH);
        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        addButton = new JButton("REGISTRARSE");
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(76, 175, 80));

        add(mainPanel, BorderLayout.CENTER);

        // COLORES
        mainPanel.setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        formPanel.setBackground(Color.WHITE);
        imagePanel.setBackground(Color.WHITE);
        uploadButton.setBackground(new Color(9, 36, 51));
        uploadButton.setForeground(Color.WHITE);
        showPasswordButton.setBackground(Color.WHITE);

        // FUENTES
        try {
            Font robotoBlackFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/resources/fonts/Roboto-Black.ttf")).deriveFont(25f);
            // Cargar la fuente desde el archivo en el directorio de recursos
            titulo.setFont(robotoBlackFont);

            Font robotoLightFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/resources/fonts/Roboto-Light.ttf")).deriveFont(12f);
            Font robotoBoldFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/resources/fonts/Roboto-Bold.ttf")).deriveFont(12f);
            for (Component component : formPanel.getComponents()) {
                if (component instanceof JButton || component instanceof JLabel) {
                    component.setFont(robotoLightFont);
                }
                if (component instanceof JTextField) {
                    component.setFont(robotoBoldFont);
                }
            }
            addButton.setFont(robotoBoldFont.deriveFont(12f));
            uploadButton.setFont(robotoBoldFont);
            idLabel.setFont(robotoBoldFont);

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idLabel.getText();
                String nombres = nombresField.getText();
                String apellidos = apellidosField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                Usuario usuario = new Usuario(id, nombres, apellidos, telefono, email, null, null,
                        "https://fotografiamejorparavendermas.com/wp-content/uploads/2017/06/La-importancia-de-la-imagen.jpg",
                        password);
                new MenuInterfaz(usuario);
                setVisible(false);
                // CREAR BASE DE DATOS Y ENVIAR INFO DE USUARIOS
                // conexion a la BD:
                // mongodb+srv://Admin:passwordAdmin@cluster0.fe0chr9.mongodb.net/
                BDconection(usuario);

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
                }
            }
        });

        showPasswordButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showPassword = !showPassword;
                if (showPassword) {
                    passwordField.setEchoChar((char) 0);
                    showPasswordButton.setText("Ocultar");
                } else {
                    passwordField.setEchoChar('\u2022');
                    showPasswordButton.setText("Mostrar");
                }
            }
        });

        telefonoField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tel = telefonoField.getText();
                String uniqueID = generateUniqueID(tel);
                idLabel.setText(uniqueID);
                mainPanel.add(addButton, BorderLayout.SOUTH);
            }
        });

    }
    private void BDconection(Usuario usuario) {
        try (MongoClient mongoClient = new MongoClient(uri)) {
            MongoDatabase database = mongoClient.getDatabase("SeaUnalenoDB");
            System.out.println("Conectado a la base de datos: " + database.getName());
            MongoCollection<Document> collection = database.getCollection("usuarios");

            collection.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
            Document userDocument = new Document()
                    .append("id", usuario.getId())
                    .append("nombres", usuario.getNombres())
                    .append("apellidos", usuario.getApellidos())
                    .append("telefono", usuario.getTelefono())
                    .append("email", usuario.getEmail())
                    .append("historial", usuario.getHistorial())
                    .append("notas", usuario.getNotas())
                    .append("pathImagen", usuario.getPathImagen())
                    .append("password", usuario.getPassword());

            collection.insertOne(userDocument);
            // Document command = new Document("createUser", "myUser")
            //         .append("pwd", "myPassword")
            //         .append("roles", Arrays.asList(new Document("role", "readWrite"), new Document("role", "dbAdmin")));

            // database.runCommand(command);
        }
    }

    // Metodo que genera ID's unicos Hashing
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
            RegistroUsuarios registroFrame = new RegistroUsuarios();
            registroFrame.setVisible(true);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}

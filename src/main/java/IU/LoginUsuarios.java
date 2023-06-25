package IU;

import javax.swing.*;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



import static com.mongodb.client.model.Filters.eq;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;

import DATOS.Evaluacion;
import DATOS.Usuario;
import LOGICA.Stack;

import java.awt.*;
import java.awt.event.*;
import java.util.Base64;
// import java.util.List;

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
                String password = new String(passwordField.getPassword());
                // Usuario objeto_de_prueba = new Usuario(username, username, username,
                // username, username, null, null, username, password);
                Usuario usuario = verificarEmail(username, password);
                if (usuario != null) {
                    System.out.println("USUARIO ENCONTRADO:" + username + ":" + password);
                    new MenuInterfaz(usuario);

                } else {
                    System.out.println("USUARIO NO ENCONTRADO");
                }

                // System.out.println(objeto_de_prueba.getPassword());

                /*
                 * Realizar la lógica de autenticación
                 * Verificar el email y la contraseña, etc.
                 * Si la autenticación es exitosa, abrir MnuInterfaz Si no, mostrar un mensaje
                 * de error
                 */
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

    public Usuario verificarEmail(String email, String password) {
        Bson filter = eq("email", email);
        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(
                        "mongodb+srv://Admin:passwordAdmin@cluster0.fe0chr9.mongodb.net/"));
        MongoDatabase database = mongoClient.getDatabase("SeaUnalenoDB");
        MongoCollection<Document> collection = database.getCollection("usuarios");
        FindIterable<Document> result = collection.find(filter);
        
        for (Document usuarioEncontrado : result) {
            System.out.println(usuarioEncontrado.getString("password"));
            if(usuarioEncontrado.getString("password").equals(password)){
            // Obtener los datos del usuario
                System.out.println(usuarioEncontrado.getString("id"));
                String id = usuarioEncontrado.getString("id");
                String nombres = usuarioEncontrado.getString("nombres");
                String apellidos = usuarioEncontrado.getString("apellidos");
                String telefono = usuarioEncontrado.getString("telefono");
                Stack<Evaluacion> historial = null;
                float[] notas = null;
                byte[] imagenBytes = null;

                Binary binData = usuarioEncontrado.get("pathImagen", Binary.class);
                if (binData != null) {
                    // Convertir Binary a byte[]
                    imagenBytes = binData.getData();
                }
                // System.out.println(usuarioEncontrado.getString("Imagenbytes"));
                // parseJsonToByteArray(usuarioEncontrado.getString("Imagenbytes"));
                String usuarioPassword = usuarioEncontrado.getString("password");

                // Crear y retornar el objeto Usuario
                return new Usuario(id, nombres, apellidos, telefono, email, historial, notas, imagenBytes,
                        usuarioPassword);
                }
                else{
                    System.out.println("contraseña incorrecta");
                }
        }
        System.out.println(email);
        // Si el correo electrónico no existe o no se encuentra en la base de datos
        return null;
    }

    // private Stack<Evaluacion> obtenerHistorial(Document usuario) {
    //     Stack<Evaluacion> historial = null;

    //     List<Document> evaluaciones = usuario.getList("historial", Document.class);
    //     for (Document evaluacionDoc : evaluaciones) {
    //         String link = evaluacionDoc.getString("link");
    //         int numeroDePreguntas = evaluacionDoc.getInteger("numeroDePreguntas");
    //         List<String> respuestasList = evaluacionDoc.getList("respuestas", String.class);
    //         String[] respuestas = respuestasList.toArray(new String[0]);
    //         int horas = evaluacionDoc.getInteger("horas");
    //         int minutos = evaluacionDoc.getInteger("minutos");
    //         String tipoEvaluacion = evaluacionDoc.getString("tipoEvaluacion");
    //         String nombre = evaluacionDoc.getString("nombre");

    //         Evaluacion evaluacion = new Evaluacion(link, numeroDePreguntas, respuestas, horas, minutos, tipoEvaluacion,
    //                 nombre);
    //         historial = new Stack<Evaluacion>(evaluaciones.size());
    //         historial.push(evaluacion);
    //     }
    //     return historial;
    // }

    private byte[] parseJsonToByteArray(String jsonString) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

            if (jsonObject.containsKey("pathImagen")) {
                JSONObject pathImagenObject = (JSONObject) jsonObject.get("pathImagen");

                if (pathImagenObject.containsKey("$binary")) {
                    JSONObject binaryObject = (JSONObject) pathImagenObject.get("$binary");

                    if (binaryObject.containsKey("base64")) {
                        String base64 = (String) binaryObject.get("base64");
                        return Base64.getDecoder().decode(base64);
                    }
                }
            }
        } catch (ParseException exe) {
            exe.printStackTrace();
        }

        return null; // Retorna null si el formato JSON no es válido o no contiene los campos
                     // esperados
    }

    // private float[] obtenerNotas(Document usuario) {
    //     List<Double> notasList = usuario.getList("notas", Double.class);
    //     float[] notas = new float[notasList.size()];

    //     for (int i = 0; i < notasList.size(); i++) {
    //         notas[i] = notasList.get(i).floatValue();
    //     }

    //     return notas;
    // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginUsuarios loginFrame = new LoginUsuarios();
                loginFrame.setVisible(true);
            }
        });
    }
}

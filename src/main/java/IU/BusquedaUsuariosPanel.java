package IU;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DATOS.Usuario;
import LOGICA.LinkedList;
import LOGICA.Set;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusquedaUsuariosPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JTable table;

    public BusquedaUsuariosPanel(LinkedList<Usuario> usuarios) {
        // Configurar el diseño del panel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
        setBackground(Color.WHITE);

        // Crear el campo de búsqueda y el botón
        searchField = new JTextField();
        searchButton = new JButton("Buscar");

        // Crear el modelo de tabla con 6 columnas
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombres");
        tableModel.addColumn("Apellidos");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Email");
        tableModel.addColumn("Notas");
        
        // AVLTree<Usuario> usuarios = new AVLTree<Usuario>();
        

        // Crear la tabla con el modelo
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE);
        // Crear el JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        // Agregar el campo de búsqueda y el botón al panel superior
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Agregar el panel de búsqueda y el JScrollPane al panel principal
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Configurar la acción del botón de búsqueda
        searchButton.setBackground(Color.white);
        Set<Usuario> setUsuarios = new Set<Usuario>();
        for (int j = 0; j < usuarios.size; j++) {
            try {
                setUsuarios.add(usuarios.get(j));
            } catch (Exception e) {
                System.out.println("error mkas");
            }
            
        }
        
        
        actualizarTabla(setUsuarios);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                // Aquí puedes realizar la búsqueda en la base de datos con el término de
                // búsqueda proporcionado
                // y actualizar los datos en la tabla
                // Ejemplo:
                
            }
        });
    }

    // Método para actualizar los datos en la tabla
    private void actualizarTabla(Set<Usuario> usuarios) {
        // Borrar filas existentes
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Agregar nuevas filas con los datos de los usuarios
        for (int i = 0; i < usuarios.size; i++) {
            try {
                Usuario usuario = usuarios.get(i);
                model.addRow(new Object[] {
                        usuario.getId(),
                        usuario.getNombres(),
                        usuario.getApellidos(),
                        usuario.getTelefono(),
                        usuario.getEmail(),
                        usuario.getPassword()
                });
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        // Actualizar la interfaz gráfica
        model.fireTableDataChanged();
    }

    // // Método de ejemplo para buscar usuarios en la base de datos
    // private Set<Usuario> traerUsuarios() {

    //     Stack<Usuario> usuarios = new Stack<Usuario>(1000);
    //     // Aquí puedes realizar la búsqueda en la base de datos utilizando el término de
    //     // búsqueda
    //     // y devolver una lista de usuarios encontrados
    //     Bson filter = ne("id", "");
    //     FindIterable<Document> result = collection.find(filter);
        
    //     for (Document document : result) {
    //         String id= document.getString("id");
    //         String nombres=document.getString("nombres");
    //         String apellidos=document.getString("apellidos");
    //         String telefono=document.getString("telefono");
    //         String email=document.getString("email");
    //         String password= document.getString("password");
    //         Usuario aux= new Usuario(id, nombres, apellidos, telefono, email, null, null, null, password);
    //         usuarios.push(aux);
    //     }
    //     return usuarios;
    // }

    // Método main para probar la clase
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // JFrame frame = new JFrame("Búsqueda de Usuarios");
                // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // frame.setSize(800, 600);

                // BusquedaUsuariosPanel panel = new BusquedaUsuariosPanel();
                // frame.add(panel);

                // frame.setVisible(true);
            }
        });
    }
}
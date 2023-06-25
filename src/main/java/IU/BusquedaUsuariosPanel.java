package IU;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DATOS.Usuario;
import LOGICA.BST;
import LOGICA.LinkedList;
import LOGICA.Set;
import LOGICA.conexionBD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        tableModel.addColumn("Contraseña");

        // AVLTree<Usuario> usuarios = new AVLTree<Usuario>();
        try {
            searchButton.setFont(
                    Font.createFont(Font.TRUETYPE_FONT,
                            new File("src/main/resources/fonts/Roboto-Light.ttf"))
                            .deriveFont(12f));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                actualizarTabla(buscarUsuarios(usuarios, searchTerm));

            }
        });
    }

    private Set<Usuario> buscarUsuarios(LinkedList<Usuario> usuarios, String searchTerm) {
        BST arbolUsuarios = new BST();
        for (int i = 0; i < usuarios.size; i++) {
            Usuario n;
            try {
                n = usuarios.get(i);
                arbolUsuarios.insert(n);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Set<Usuario> querySet = arbolUsuarios.findAll(searchTerm, arbolUsuarios.root);
        return querySet;
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

    // Método main para probar la clase
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Búsqueda de Usuarios");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);

                BusquedaUsuariosPanel panel = new BusquedaUsuariosPanel(new conexionBD("usuarios").getUsuarios());
                frame.add(panel);

                frame.setVisible(true);
            }
        });
    }
}
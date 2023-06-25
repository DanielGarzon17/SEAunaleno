package IU;

import LOGICA.RoundedSearchFieldExample;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DATOS.Usuario;
import LOGICA.BST;
import LOGICA.LinkedList;
import LOGICA.Set;
import LOGICA.conexionBD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
        searchField = new RoundedSearchFieldExample(20);
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
        Color azulBonito = new Color(8, 52, 76);
        Color azulChimba = new Color(185, 228, 228);
        Color azulMasChimba = new Color(185, 207, 228);
        
       
       
        
        table.setBackground(azulBonito);
        try {
            Font nunitoSansFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/resources/fonts/NunitoSans-Regular.ttf")).deriveFont(15f);
            // Cargar la fuente desde el archivo en el directorio de recursos
            table.setFont(nunitoSansFont);
            table.setForeground(azulChimba);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Obtener el encabezado de la tabla
        JTableHeader tableHeader = table.getTableHeader();

        // Cargar la fuente personalizada desde una carpeta de recursos
        try {
            InputStream fontStream = BusquedaUsuariosPanel.class.getResourceAsStream("/fonts/NunitoSans-Regular.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            customFont = customFont.deriveFont(Font.BOLD, 12);

            // Establecer la fuente personalizada para los encabezados de las columnas
            tableHeader.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


    


        // Crear el JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(azulChimba);
        // Agregar el campo de búsqueda y el botón al panel superior
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Agregar el panel de búsqueda y el JScrollPane al panel principal
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Configurar la acción del botón de búsqueda
       
        searchButton.setBackground(azulMasChimba);
        try {
            searchButton.setFont(
                    Font.createFont(Font.TRUETYPE_FONT,
                            new File("src/main/resources/fonts/NunitoSans-Regular.ttf"))
                            .deriveFont(17f));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
       searchButton.setBackground(azulMasChimba);
        try {
            searchField.setFont(
                    Font.createFont(Font.TRUETYPE_FONT,
                            new File("src/main/resources/fonts/NunitoSans-Regular.ttf"))
                            .deriveFont(15f));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    


        
        // Crear el borde redondeado
        Border roundedBorder = new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.GRAY);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawRoundRect(x, y, width - 1, height - 1, 20, 20);
                g2.dispose();
            }
        };

        // Aplicar el borde redondeado al searchButton
        searchButton.setBorder(BorderFactory.createCompoundBorder(roundedBorder, new EmptyBorder(5, 15, 5, 15)));


        
  
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